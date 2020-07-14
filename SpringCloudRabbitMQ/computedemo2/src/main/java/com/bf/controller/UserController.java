package com.bf.controller;

import com.bf.entity.User;
import com.bf.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bofei
 * @date 2018/7/12 13:02
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/findByName")
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @GetMapping("/insertUser")
    public String insertUser(String name, Integer age) {
        userMapper.insert(name, age);
        return "insert";
    }
}
