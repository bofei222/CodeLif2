package com.bf.controller;

import com.bf.client.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bofei
 * @date 2018/7/12 11:25
 */
@RestController
public class RedisController {
    @Resource
    private RedisClient redisClient;

    @GetMapping("/set")
    public void set(String key, String value) {
        redisClient.set(key, value);
    }

    @GetMapping("/get")
    public String get(String key) {
        return redisClient.get(key);
    }
}
