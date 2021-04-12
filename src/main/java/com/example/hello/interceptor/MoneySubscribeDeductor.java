package com.example.hello.interceptor;

import com.example.hello.model.UserAmount;
import com.example.hello.resource.Bill;
import com.example.hello.service.IUserAmountService;

public class MoneySubscribeDeductor extends AbstractMoneyDeductor {
    private IUserAmountService userAmountService;

    public MoneySubscribeDeductor(IUserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }

    @Override
    protected boolean doDeduct(UserAmount userAmount, Bill bill) {
        int moneyRemain = userAmount.getMoneySubscribe() + bill.getAmount();
        if (moneyRemain >= 0) {
            userAmount.setMoney(0);
            userAmount.setMoneySubscribe(moneyRemain);
            userAmountService.save(userAmount);
            return true;
        }
        bill.setAmount(moneyRemain);
        return false;
    }
}
