package com.example.hello.service;

import com.example.hello.model.UserAmount;

import java.util.Optional;

public interface IUserAmountService {
    Optional<UserAmount> findById(Long id);
    void save(UserAmount userAmount);
}
