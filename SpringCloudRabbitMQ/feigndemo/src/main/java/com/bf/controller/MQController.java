package com.bf.controller;

import com.bf.service.Sender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bofei
 * @date 2018/7/12 10:38
 */
@RestController
public class MQController {
    @Resource
    private Sender sender;


    @GetMapping("/send")
    public String send(Integer count) {
        sender.send(count);
        return "发送消息SUCCESS";
    }

    @GetMapping("/newTask")
    public String newTask() {
        sender.newTask();
        return "发送消息SUCCESS";
    }



}
