package com.bf.mapper;

import com.bf.entity.Address;
import com.bf.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author bofei
 * @date 2018/7/31 10:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    private Logger loggerr = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonRepository repo;

    @Test
    public void basicCrudOperations() {
        Person rand = new Person("rand", "al'thor");
        rand.setAddress(new Address("emond's field", "andor"));
        repo.save(rand);             //①
        Person one = repo.findOne(rand.getId());//②
        loggerr.info(one.toString());
        long count = repo.count();//③
        loggerr.info("count {}", String.valueOf(count));
//        repo.delete(rand);           //④
    }
}