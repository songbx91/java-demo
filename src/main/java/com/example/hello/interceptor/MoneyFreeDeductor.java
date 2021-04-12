package com.example.hello.interceptor;

import com.example.hello.model.UserAmount;
import com.example.hello.resource.Bill;
import com.example.hello.service.IUserAmountService;

public class MoneyFreeDeductor extends AbstractMoneyDeductor{
    private IUserAmountService userAmountService;

    public MoneyFreeDeductor(IUserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }

    @Override
    protected boolean doDeduct(UserAmount userAmount, Bill bill) {
        int moneyRemain = userAmount.getMoneyFree() + bill.getAmount();
        if (moneyRemain >= 0) {
            userAmount.setMoney(0);
            userAmount.setMoneySubscribe(0);
            userAmount.setMoneyFree(moneyRemain);
            userAmountService.save(userAmount);
            return true;
        }
        logger.info("余额不足");
        return false;
    }
}
