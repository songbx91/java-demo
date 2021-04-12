package com.example.hello.service.impl;

import com.example.hello.model.UserAmount;
import com.example.hello.repository.UserAmountRepository;
import com.example.hello.service.IUserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAmountServiceImpl implements IUserAmountService {
    @Autowired
    private UserAmountRepository userAmountRepository;

    @Override
    public Optional<UserAmount> findById(Long id) {
        return userAmountRepository.findById(id);
    }

    @Override
    public void save(UserAmount userAmount) {
        userAmountRepository.save(userAmount);
    }
}
