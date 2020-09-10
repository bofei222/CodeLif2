package com.bf.springboottdenginedemo.controller;

import com.bf.springboottdenginedemo.service.DumpTaosiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: bofei
 * @date: 2020-08-20 09:43
 **/
@RestController
public class DumpTaosiController {

    @Resource
    private DumpTaosiService dumpTaosiService;

    @RequestMapping("/testCoor")
    public String testCoor() {
        dumpTaosiService.testCoor();
        return "123";
    }
}
