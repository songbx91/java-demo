package com.example.hello.service.impl;

import com.example.hello.model.Friendship;
import com.example.hello.repository.FriendshipRepository;
import com.example.hello.service.IFriendshipService;
import com.example.hello.utils.GeneralUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendshipServiceImpl implements IFriendshipService {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public Friendship makeFriends(Integer userId1, Integer userId2) {
        Friendship friendship = new Friendship();
        String token = GeneralUtils.getFriendshipToken(Long.valueOf(userId1), Long.valueOf(userId2));
        friendship.setToken(token);
        if (userId1 <= userId2) {
            friendship.setUid1(userId1);
            friendship.setUid2(userId2);
        } else {
            friendship.setUid1(userId2);
            friendship.setUid2(userId1);
        }
        try {
            friendshipRepository.save(friendship);
            return friendship;
        } catch (Exception e) {
            logger.error("make friend error", e);
            return null;
        }
    }

    @Override
    public void remove(Integer userId, Integer targetUserId) {
        Optional<Friendship> friendshipOptional = exists(userId, targetUserId);
        if (friendshipOptional.isPresent()) {
            friendshipRepository.deleteById(friendshipOptional.get().getId());
        }
    }

    @Override
    public Optional<Friendship> exists(Integer userId1, Integer userId2) {
        String token = GeneralUtils.getFriendshipToken(Long.valueOf(userId1), Long.valueOf(userId2));
        Optional<Friendship> friendshipOptional = friendshipRepository.findByToken(token);
        if (friendshipOptional.isPresent()) {
            return friendshipOptional;
        }
        return Optional.empty();
    }
}
