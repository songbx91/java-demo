package com.example.hello.helper;

import com.example.hello.interceptor.*;
import com.example.hello.model.Blacklist;
import com.example.hello.request.FriendApply;
import com.example.hello.service.IBlacklistService;
import com.example.hello.service.IFriendApplyService;
import com.example.hello.service.IFriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FriendshipHelper {
    private final IFriendApplyService friendApplyService;
    private final IFriendshipService friendshipService;
    private final IBlacklistService blacklistService;

    public boolean apply(FriendApply friendApply) {
        FriendApplyCheckChain friendApplyCheckChain = new FriendApplyCheckChain();
        friendApplyCheckChain.addFilter(new FriendWithNoBodyFilter());
        friendApplyCheckChain.addFilter(new FriendApplyRepeatableHandler(friendApplyService));
        friendApplyCheckChain.addFilter(new BlacklistCheckHandler(blacklistService));
        friendApplyCheckChain.addFilter(new FriendshipExistenceCheckHandler(friendshipService));

        boolean filterResult = friendApplyCheckChain.filter(friendApply);
        if (!filterResult) {
            return false;
        }

        Long userId = friendApply.getUserId();
        Long targetUserId = friendApply.getTargetUserId();
        friendApplyService.save(userId, targetUserId);
        if (friendApplyService.valid(targetUserId, userId)) {
            friendshipService.makeFriends(userId.intValue(), targetUserId.intValue());
            friendApplyService.cleanRecordBetweenUsers(userId, targetUserId);
        }
        removeBlock(userId, targetUserId);
        return true;
    }

    public boolean accept(Long userId, Long applicantId) {
        if (!friendApplyService.valid(applicantId, userId)) {   //对方好友请求不存在或者过期
            return false;
        }
        if (friendshipService.exists(userId.intValue(), applicantId.intValue()).isPresent()) {    //已经是好友
            return false;
        }
        friendshipService.makeFriends(userId.intValue(), applicantId.intValue());
        friendApplyService.cleanRecordBetweenUsers(userId, applicantId);
        return true;
    }

    public void reject(Long userId, Long applicantId) {
        friendApplyService.reject(userId, applicantId);
    }

    public Blacklist blockUser(Long userId, Long blockUserId) {
        friendApplyService.reject(userId, blockUserId);
        return blacklistService.newBlockedUser(userId.intValue(), blockUserId.intValue());
    }

    public void removeBlock(Long userId, Long blockUserId) {
        blacklistService.removeBlockedUser(userId.intValue(), blockUserId.intValue());
    }

    public List<Integer> blockList(Integer userId) {
        return blacklistService.getAllBlockedUserIdsByUserId(userId);
    }

    private class FriendApplyCheckChain {
        private AbstractFriendshipFilter head = null;
        private AbstractFriendshipFilter tail = null;
        public void addFilter(AbstractFriendshipFilter filter) {
            filter.setSuccessor(null);
            if (head == null) {
                head = filter;
                tail = filter;
                return ;
            }
            tail.setSuccessor(filter);
            tail = filter;
        }

        public boolean filter(FriendApply friendApply) {
            if (head != null) {
                return head.handle(friendApply);
            }
            return true;
        }
    }
}
