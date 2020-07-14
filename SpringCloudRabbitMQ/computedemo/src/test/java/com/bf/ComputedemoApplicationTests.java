package com.bf;

import com.alibaba.fastjson.JSON;
import com.bf.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComputedemoApplicationTests {

//	@Test
//	public void contextLoads() {

    private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

	}

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


//	@Test
//	public void test2() throws Exception {
//
//		// 保存对象
//		User user = new User(1l, "超人", 20);
//		redisTemplate.opsForValue().set(user.getName(), user);
//
//		user = new User(2l, "蝙蝠侠", 30);
//		redisTemplate.opsForValue().set(user.getName(), user);
//
//		user = new User(3l, "蜘蛛侠", 40);
//		redisTemplate.opsForValue().set(user.getName(), user);
//
//		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
//
//
//	}

	@Test
	public void test3() throws Exception {


        Map<String,Object> testMap = new HashMap();
        testMap.put("name","jack");
        testMap.put("age",27);
        testMap.put("class","1");
        redisTemplate.opsForHash().putAll("redisHash1",testMap);
        log.info("test3 {}", redisTemplate.opsForHash().entries("redisHash1"));

    }

}
