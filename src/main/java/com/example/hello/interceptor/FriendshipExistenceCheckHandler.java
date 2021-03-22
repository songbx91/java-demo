package com.example.hello.interceptor;

import com.example.hello.model.Friendship;
import com.example.hello.request.FriendApply;
import com.example.hello.service.IFriendshipService;

import java.util.Optional;

public class FriendshipExistenceCheckHandler extends AbstractFriendshipFilter{

    private IFriendshipService friendshipService;

    public FriendshipExistenceCheckHandler (IFriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }
    @Override
    protected boolean doHandle(FriendApply friendApply) {
        Long userId = friendApply.getUserId();
        Long targetUserId = friendApply.getTargetUserId();
        Optional<Friendship> exist = friendshipService.exists(userId.intValue(), targetUserId.intValue());
        if (exist.isPresent()) {
            logger.info("Friendship already exists.");
            return false;
        }
        return true;
    }
}
