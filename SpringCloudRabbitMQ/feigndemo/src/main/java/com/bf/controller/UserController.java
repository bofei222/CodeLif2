package com.bf.controller;

import com.alibaba.fastjson.JSONObject;
import com.bf.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bofei
 * @date 2018/7/12 13:16
 */
@RestController
public class UserController {
    @Resource
    private UserClient userClient;

    @GetMapping("/insertUser")
    public String insertUser(String name, Integer age) {
        userClient.insertUser(name, age);
        return "insert success";
    }

    @GetMapping("/findByName")
    public JSONObject findByName(String name) {
        return userClient.findByName(name);
    }

}
