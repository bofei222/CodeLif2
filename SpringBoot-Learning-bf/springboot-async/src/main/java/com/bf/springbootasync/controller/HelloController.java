package com.bf.springbootasync.controller;

import com.bf.springbootasync.task.MyTask;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAsync
public class HelloController {

    @Resource
    private MyTask myTask;

    @GetMapping("/helloAsync")
    public String helloAsync() {
        long start = System.currentTimeMillis();
        try {
            myTask.doTaskOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return String.valueOf(end - start);
    }

    @GetMapping("/helloSync")
    public String helloSync() {
        long start = System.currentTimeMillis();
        try {
            myTask.doTaskTwo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return String.valueOf(end - start);
    }
}
