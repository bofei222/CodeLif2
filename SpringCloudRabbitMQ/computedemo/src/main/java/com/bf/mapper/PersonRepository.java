package com.bf.mapper;

import com.bf.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author bofei
 * @date 2018/7/31 9:59
 */
public interface PersonRepository extends CrudRepository<Person, String> {
}
