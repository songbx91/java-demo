package com.example.hello.service.impl;

import com.example.hello.controller.UserController;
import com.example.hello.model.User;
import com.example.hello.repository.UserRepository;
import com.example.hello.service.IUserService;
import com.example.hello.utils.JsonUtils;
import com.example.hello.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public User create(User user) {
        String baseStr = user.getName() + ":" + user.getTel() + ":" + user.getEmail();
        String token = MD5Utils.encrypt(baseStr);
        user.setToken(token);
        userRepository.save(user);
        String userCacheKey = getUserCacheKey(user.getId());
        if (user != null) {
            redisTemplate.opsForValue().set(userCacheKey, JsonUtils.ObjectToJson(user), 120L, TimeUnit.SECONDS);
        }
        return user;
    }

    public Optional<User> findById(Integer id) {
        String userCacheKey = getUserCacheKey(id);
        String json = (String)redisTemplate.opsForValue().get(userCacheKey);
        if ("$N".equals(json)) {
            return Optional.empty();
        } else if (json != null) {
            return JsonUtils.jsonToObject(json, User.class);
        } else {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                redisTemplate.opsForValue().set(userCacheKey, JsonUtils.ObjectToJson(user.get()), 120L, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(userCacheKey, "$N", 120L, TimeUnit.SECONDS);
            }
            return user;
        }
    }

    public Optional<User> findByToken(String token) {
        String userTokenCacheKey = getUserTokenCacheKey(token);
        Integer userId = (Integer)redisTemplate.opsForValue().get(userTokenCacheKey);
        if (userId == null) {
            Optional<User> user = userRepository.findByToken(token);
            if (user.isPresent()) {
                userId = user.get().getId();
                redisTemplate.opsForValue().setIfAbsent(userTokenCacheKey, userId, 120L, TimeUnit.SECONDS);
                return user;
            }
            redisTemplate.opsForValue().setIfAbsent(userTokenCacheKey, 0, 120L, TimeUnit.SECONDS);
            return Optional.empty();
        }
        redisTemplate.opsForValue().setIfAbsent(userTokenCacheKey, userId, 120L, TimeUnit.SECONDS);
        return findById(userId);
    }

    public boolean removeById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        redisTemplate.delete(getUserCacheKey(id));
        return true;
    }

    private String getUserCacheKey(Integer id) {
        return "user_cache:" + id;
    }

    private String getUserTokenCacheKey(String token) {
        return "user_token_cache:" + token;
    }
}
