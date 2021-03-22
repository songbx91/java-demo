package com.example.hello.interceptor;

import com.example.hello.request.FriendApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFriendshipFilter {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractFriendshipFilter.class);
    protected AbstractFriendshipFilter successor = null;

    public void setSuccessor(AbstractFriendshipFilter successor) {
        this.successor = successor;
    }

    public final boolean handle(FriendApply friendApply) {
        boolean handled = doHandle(friendApply);
        if (successor != null && handled) {
            return successor.handle(friendApply);
        }
        return handled;
    }

    protected abstract boolean doHandle(FriendApply friendApply);
}
