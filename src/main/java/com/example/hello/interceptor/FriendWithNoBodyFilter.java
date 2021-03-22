package com.example.hello.interceptor;

import com.example.hello.request.FriendApply;

public class FriendWithNoBodyFilter extends AbstractFriendshipFilter {
    @Override
    protected boolean doHandle(FriendApply friendApply) {
        if (friendApply.getUserId() == friendApply.getTargetUserId()) {
            logger.info("Can not make friends with your self");
            return false;
        }
        return true;
    }
}
