package com.example.hello.interceptor;

import com.example.hello.model.UserAmount;
import com.example.hello.resource.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMoneyDeductor {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractMoneyDeductor.class);
    protected AbstractMoneyDeductor successor = null;

    public void setSuccessor(AbstractMoneyDeductor successor) {
        this.successor = successor;
    }

    public final boolean deduct(UserAmount userAmount, Bill bill) {
        boolean deducted = doDeduct(userAmount, bill);
        if (!deducted && successor != null) {
            return successor.deduct(userAmount, bill);
        }
        return deducted;
    }

    protected abstract boolean doDeduct(UserAmount userAmount, Bill bill);
}
