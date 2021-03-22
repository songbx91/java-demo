package com.example.hello.service.impl;

import com.example.hello.model.Blacklist;
import com.example.hello.repository.BlacklistRepository;
import com.example.hello.service.IBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlacklistServiceImpl implements IBlacklistService {
    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public List<Blacklist> getAllBlockedUsersByUserId(Integer userId) {
        return blacklistRepository.findAllByUserId(userId);
    }

    public List<Integer> getAllBlockedUserIdsByUserId(Integer userId) {
        return getAllBlockedUsersByUserId(userId).stream().map(Blacklist::getBlockedUserId).collect(Collectors.toList());
    }

    @Override
    public Blacklist newBlockedUser(Integer userId, Integer blockedUserId) {
        Blacklist blacklist = new Blacklist();
        blacklist.setUserId(userId);
        blacklist.setBlockedUserId(blockedUserId);
        blacklistRepository.save(blacklist);
        return blacklist;
    }

    @Override
    public void removeBlockedUser(Integer userId, Integer blockedUserId) {
        blacklistRepository.deleteAllByUserIdAndBlockedUserId(userId, blockedUserId);
    }

    @Override
    public boolean blocked(Integer userId, Integer blockedUserId) {
        Optional<Blacklist> blacklistOptional = blacklistRepository.findByUserIdAndBlockedUserId(userId, blockedUserId);
        if (blacklistOptional.isPresent()) {
            return true;
        }
        return false;
    }
}
