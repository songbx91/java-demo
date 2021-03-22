package com.example.hello.service;

public interface IFriendApplyService {
    void save(Long userId, Long targetUserId);
    void reject(Long userId, Long applicantId);
    boolean accept(Long userId, Long applicantId);
    boolean valid(Long userId, Long targetUserId);
    void cleanRecordBetweenUsers(Long userId1, Long userId2);
}
