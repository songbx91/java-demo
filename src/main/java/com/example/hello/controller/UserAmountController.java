package com.example.hello.controller;

import com.example.hello.helper.UserAmountHelper;
import com.example.hello.model.UserAmount;
import com.example.hello.repository.UserAmountRepository;
import com.example.hello.resource.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/user_amount")
public class UserAmountController {
    @Autowired
    private UserAmountRepository userAmountRepository;
    @Autowired
    private UserAmountHelper userAmountHelper;

    @PostMapping("/register")
    public void register(@RequestParam("user_id") Integer userId,
                         @RequestParam("money") Integer money,
                         @RequestParam("money_subscribe") Integer moneySubscribe,
                         @RequestParam("money_free") Integer moneyFree) {
        UserAmount userAmount = new UserAmount();
        userAmount.setId(userId);
        userAmount.setMoney(money);
        userAmount.setMoneySubscribe(moneySubscribe);
        userAmount.setMoneyFree(moneyFree);
        userAmountRepository.save(userAmount);
    }

    @PostMapping("/pay")
    public void pay(@RequestParam("user_id") Long userId, @RequestParam("amount") Integer amount) {
        System.out.println(Instant.EPOCH);
        Bill bill = new Bill();
        bill.setAmount(amount);
        userAmountHelper.pay(bill, userId);
        Optional<UserAmount> userAmountOptional = userAmountRepository.findById(userId);
        if (userAmountOptional.isPresent()) {
            UserAmount userAmount = userAmountOptional.get();
            int moneyRemain = userAmount.getMoney() - amount;
            if (moneyRemain >= 0) {
                userAmount.setMoney(moneyRemain);
                userAmountRepository.save(userAmount);
            } else {
                moneyRemain = userAmount.getMoneySubscribe() + moneyRemain;
                if (moneyRemain >= 0) {
                    userAmount.setMoney(0);
                    userAmount.setMoneySubscribe(moneyRemain);
                    userAmountRepository.save(userAmount);
                } else {
                    moneyRemain = userAmount.getMoneyFree() + moneyRemain;
                    if (moneyRemain >= 0) {
                        userAmount.setMoney(0);
                        userAmount.setMoneySubscribe(0);
                        userAmount.setMoneyFree(moneyRemain);
                        userAmountRepository.save(userAmount);
                    } else {
                        System.out.println("余额不足");
                    }
                }
            }
        }
    }
}
