package com.bf.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author bofei
 * @date 2018/7/12 13:10
 */
@FeignClient("eureka-client")
public interface UserClient {

    @GetMapping("/findByName")
    JSONObject findByName(@RequestParam("name") String name);

    @GetMapping("/insertUser")
    String insertUser(@RequestParam("name") String name, @RequestParam("age") Integer age);

}
