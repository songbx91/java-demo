package com.example.hello.service;

import com.example.hello.model.Blacklist;

import java.util.List;

public interface IBlacklistService {
    List<Blacklist> getAllBlockedUsersByUserId(Integer userId);
    List<Integer> getAllBlockedUserIdsByUserId(Integer userId);
    Blacklist newBlockedUser(Integer userId, Integer blockedUserId);
    void removeBlockedUser(Integer userId, Integer blockedUserId);
    boolean blocked(Integer userId, Integer blockedUserId);
}
