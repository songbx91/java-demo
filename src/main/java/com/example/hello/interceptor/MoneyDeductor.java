package com.example.hello.interceptor;

import com.example.hello.model.UserAmount;
import com.example.hello.resource.Bill;
import com.example.hello.service.IUserAmountService;
import org.springframework.beans.factory.annotation.Autowired;

public class MoneyDeductor extends AbstractMoneyDeductor{
    private IUserAmountService userAmountService;
    public MoneyDeductor (IUserAmountService userAmountService) {
        this.userAmountService = userAmountService;
    }
    @Override
    protected boolean doDeduct(UserAmount userAmount, Bill bill) {
        int moneyRemain = userAmount.getMoney() - bill.getAmount();
        if (moneyRemain >= 0) {
            userAmount.setMoney(moneyRemain);
            userAmountService.save(userAmount);
            return true;
        }
        bill.setAmount(moneyRemain);
        return false;
    }
}
