package com.bf.springbootbadrequest;

import com.bf.springbootbadrequest.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootBadrequestApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        User user = new User();
//        user.setId(null);
        System.out.println(user);
        user.setId(null);
        user.setUsername(null);
        System.out.println(user);
    }
}
