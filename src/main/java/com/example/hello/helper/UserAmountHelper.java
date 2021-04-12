package com.example.hello.helper;

import com.example.hello.interceptor.AbstractMoneyDeductor;
import com.example.hello.interceptor.MoneyDeductor;
import com.example.hello.interceptor.MoneyFreeDeductor;
import com.example.hello.interceptor.MoneySubscribeDeductor;
import com.example.hello.model.UserAmount;
import com.example.hello.repository.UserAmountRepository;
import com.example.hello.resource.Bill;
import com.example.hello.service.IUserAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountHelper {
    private final UserAmountRepository userAmountRepository;
    private final IUserAmountService userAmountService;

    public void pay(Bill bill, Long userId) {
        Optional<UserAmount> userAmountOptional = userAmountRepository.findById(userId);
        if (userAmountOptional.isPresent()) {
            UserAmount userAmount = userAmountOptional.get();
            MoneyDeductChain moneyDeductChain = new MoneyDeductChain();
            moneyDeductChain.addMoneyDeductor(new MoneyDeductor(userAmountService));
            moneyDeductChain.addMoneyDeductor(new MoneySubscribeDeductor(userAmountService));
            moneyDeductChain.addMoneyDeductor(new MoneyFreeDeductor(userAmountService));
            moneyDeductChain.deduct(userAmount, bill);
        }
    }

    private class MoneyDeductChain {
        private AbstractMoneyDeductor head = null;
        private AbstractMoneyDeductor tail = null;

        public void addMoneyDeductor(AbstractMoneyDeductor moneyDeductor) {
            moneyDeductor.setSuccessor(null);
            if (head == null) {
                head = moneyDeductor;
                tail = moneyDeductor;
            }
            tail.setSuccessor(moneyDeductor);
            tail = moneyDeductor;
        }

        public void deduct(UserAmount userAmount, Bill bill) {
            if (head != null) {
                head.deduct(userAmount, bill);
            }
        }
    }
}
