package com.example.hello.service;

import com.example.hello.model.User;

import java.util.Optional;

public interface IUserService {
    User create(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByToken(String token);
    boolean removeById(Integer id);
}
