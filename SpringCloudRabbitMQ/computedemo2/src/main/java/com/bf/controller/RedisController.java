package com.bf.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bofei
 * @date 2018/7/12 11:03
 */
@RestController
public class RedisController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/get")
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
