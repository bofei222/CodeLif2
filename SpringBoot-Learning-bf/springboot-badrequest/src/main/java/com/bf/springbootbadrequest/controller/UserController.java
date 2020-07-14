package com.bf.springbootbadrequest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: bofei
 * @date: 2019-08-16 09:39
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/bad")
    public String bad(String username, Integer sex) {
        System.out.println(username);
        System.out.println(sex);
        return "ok";
    }


}
