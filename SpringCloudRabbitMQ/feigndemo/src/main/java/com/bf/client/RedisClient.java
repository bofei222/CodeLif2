package com.bf.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author bofei
 * @date 2018/7/12 11:23
 */
@FeignClient("eureka-client")
public interface RedisClient {

    @GetMapping("/set")
    void set(@RequestParam("key") String key, @RequestParam("value") String value);

    @GetMapping("/get")
    String get(@RequestParam("key") String key);

}
