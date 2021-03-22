package com.example.hello.controller;

import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/redis/test")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @RequestMapping("/get")
    public String get(@RequestParam("key") String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
}
