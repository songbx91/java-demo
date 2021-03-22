package com.example.hello.interceptor;

import com.example.hello.request.FriendApply;
import com.example.hello.service.IFriendApplyService;

public class FriendApplyRepeatableHandler extends AbstractFriendshipFilter {
    protected IFriendApplyService friendApplyService;

    public FriendApplyRepeatableHandler(IFriendApplyService friendApplyService) {
        this.friendApplyService = friendApplyService;
    }

    @Override
    protected boolean doHandle(FriendApply friendApply) {
        if (friendApplyService.valid(friendApply.getUserId(), friendApply.getTargetUserId())) {
            logger.info("friend apply already exists");
            return false;
        }
        return true;
    }
}
