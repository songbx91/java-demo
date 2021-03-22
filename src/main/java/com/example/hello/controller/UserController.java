package com.example.hello.controller;

import com.example.hello.annotation.UserTokenCheck;
import com.example.hello.event.UpdateUserTelEvent;
import com.example.hello.mapper.UserMapper;
import com.example.hello.model.User;
import com.example.hello.repository.UserRepository;
import com.example.hello.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public User create(@RequestParam("name") String name,
                       @RequestParam("tel") String tel,
                       @RequestParam("email") String email,
                       @Nullable @RequestParam("desc") String desc) {
        User user = new User();
        user.setName(name);
        user.setTel(tel);
        user.setEmail(email);
        user.setDescription(desc);
        return userService.create(user);
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable("id") Integer id,
                       @Nullable @RequestParam("tel") String tel,
                       @Nullable @RequestParam("email") String email,
                       @Nullable @RequestParam("desc") String desc) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userModel = user.get();
            if (tel != null) {
                UpdateUserTelEvent event = new UpdateUserTelEvent("", userModel);
                applicationEventPublisher.publishEvent(event);
                logger.info("after event publish");
                userModel.setTel(tel);
            }
            if (email != null) {
                userModel.setEmail(email);
            }
            if (desc != null) {
                userModel.setDescription(desc);
            }
            String userCacheKey = "user_cache:" + id;
            redisTemplate.delete(userCacheKey);
            return userRepository.save(userModel);
        }
        return null;
    }

    @GetMapping("/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @UserTokenCheck
    public Optional<User> findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @DeleteMapping("/user/{id}")
    public boolean removeById(@PathVariable("id") Integer id) {
        return userService.removeById(id);
    }
}
