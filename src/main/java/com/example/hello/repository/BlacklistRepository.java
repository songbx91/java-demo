package com.example.hello.repository;

import com.example.hello.model.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
    List<Blacklist> findAllByUserId(Integer userId);
    @Transactional
    void deleteAllByUserIdAndBlockedUserId(Integer userId, Integer blockedUserId);
    Optional<Blacklist> findByUserIdAndBlockedUserId(Integer userId, Integer blockedUserId);
}