package com.example.hello.interceptor;

import com.example.hello.request.FriendApply;
import com.example.hello.service.IBlacklistService;

public class BlacklistCheckHandler extends AbstractFriendshipFilter {
    private IBlacklistService blacklistService;

    public BlacklistCheckHandler(IBlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @Override
    protected boolean doHandle(FriendApply friendApply) {
        Long userId = friendApply.getUserId();
        Long targetUserId = friendApply.getTargetUserId();
        if (blacklistService.blocked(targetUserId.intValue(), userId.intValue())) {
            logger.info("You are in the blacklist of target");
            return false;
        }
        return true;
    }
}
