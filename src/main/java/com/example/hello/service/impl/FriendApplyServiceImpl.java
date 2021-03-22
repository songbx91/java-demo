package com.example.hello.service.impl;

import com.example.hello.service.IFriendApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

@Service
public class FriendApplyServiceImpl implements IFriendApplyService {
    private static final Logger logger = LoggerFactory.getLogger(FriendApplyServiceImpl.class);
    protected static final Long DEFAULT_TTL = 259200L;
    protected static final int BATCH_SIZE = 500;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(Long userId, Long targetUserId) {
        String applySendRecordCacheKey = getApplySendRecordCacheKey(userId);
        String applyReceiveRecordCacheKey = getApplyReceiveRecordCacheKey(targetUserId);

        double score = (double)System.currentTimeMillis();
        List<Object> resultList = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String targetUserIdString = targetUserId.toString();
                String userIdString = userId.toString();
                operations.opsForZSet().add(applySendRecordCacheKey, targetUserIdString, score);
                operations.expireAt(applyReceiveRecordCacheKey, Instant.now().plusSeconds(DEFAULT_TTL));
                operations.opsForZSet().removeRangeByScore(applySendRecordCacheKey, 0, (score - DEFAULT_TTL * 1000));

                operations.opsForZSet().add(applyReceiveRecordCacheKey, userIdString, score);
                operations.opsForZSet().zCard(applyReceiveRecordCacheKey);
                return null;
            }
        });

        Long receivedAppliesCount = (Long)resultList.get(4);
        if (receivedAppliesCount > BATCH_SIZE) {
            redisTemplate.opsForZSet().removeRange(applyReceiveRecordCacheKey, 0, receivedAppliesCount - (BATCH_SIZE + 1));
        }
    }

    @Override
    public void reject(Long userId, Long applicantId) {
        cleanRecordBetweenUsers(userId, applicantId);
    }

    @Override
    public boolean accept(Long userId, Long applicantId) {
        if (valid(applicantId, userId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean valid(Long userId, Long targetUserId) {
        String applySendRecordCacheKey = getApplySendRecordCacheKey(userId);
        Double expiredAt = (double)System.currentTimeMillis() - DEFAULT_TTL * 1000;
        Double validTs = redisTemplate.opsForZSet().score(applySendRecordCacheKey, targetUserId.toString());
        if (validTs == null || Double.compare(validTs, expiredAt) < 1) {
            return false;
        }
        return true;
    }

    public void cleanRecordBetweenUsers(Long userId, Long targetUserId) {
        String applySendRecordByUserCacheKey = getApplySendRecordCacheKey(userId);
        String applyReceiveRecordByTargetUserCacheKey = getApplyReceiveRecordCacheKey(targetUserId);
        String applySendRecordByTargetUserCacheKey = getApplySendRecordCacheKey(targetUserId);
        String applyReceiveRecordByUserCacheKey = getApplyReceiveRecordCacheKey(userId);
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String targetUserIdString = targetUserId.toString();
                String userIdString = userId.toString();
                operations.opsForZSet().remove(applySendRecordByUserCacheKey, targetUserIdString);
                operations.opsForZSet().remove(applyReceiveRecordByTargetUserCacheKey, userIdString);
                operations.opsForZSet().remove(applySendRecordByTargetUserCacheKey, userIdString);
                operations.opsForZSet().remove(applyReceiveRecordByUserCacheKey, targetUserIdString);
                return null;
            }
        });
    }

    private String getApplySendRecordCacheKey(Long userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("friend_apply_record_cache_v2:").append(userId.toString());
        return sb.toString();
    }

    private String getApplyReceiveRecordCacheKey(Long targetUserId) {
        StringBuilder sb = new StringBuilder();
        sb.append("friend_apply_receive_record_cache_v2:").append(targetUserId.toString());
        return sb.toString();
    }
}
