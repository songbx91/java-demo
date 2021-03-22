package com.example.hello.service;

import com.example.hello.model.Friendship;

import java.util.Optional;

public interface IFriendshipService {
    Friendship makeFriends(Integer userId1, Integer userId2);
    void remove(Integer userId, Integer targetUserId);
    Optional<Friendship> exists(Integer userId1, Integer userId2);
}
