package com.bf.springboottdenginedemo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

/**
 * @description:
 * @author: bofei
 * @date: 2020-08-20 09:30
 **/
@Service
public class DumpTaosiService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void testCoor() {
        Integer userid = 123;

        String sql1 = "create database if not exists coor";
        jdbcTemplate.execute(sql1);
        String sql2 = "use coor";
        jdbcTemplate.execute(sql2);
        String sql3 = "create table thermometer (ts timestamp, degree float) \n" +
                "tags (location binary(20), type int)";
//        jdbcTemplate.execute(sql3);
        String sql4 = "create table t1 using thermometer tags ('beijing', 10)";

       String sql41 = "CREATE TABLE therm1 USING thermometer TAGS ('beijing', 1)";
       String sql42 = "CREATE TABLE therm2 USING thermometer TAGS ('beijing', 2)";
       String sql43 = "CREATE TABLE therm3 USING thermometer TAGS ('tianjin', 1)";
       String sql44 = "CREATE TABLE therm4 USING thermometer TAGS ('shanghai', 3)";


        jdbcTemplate.execute(sql41);
        jdbcTemplate.execute(sql42);
        jdbcTemplate.execute(sql43);
        jdbcTemplate.execute(sql44);

//        jdbcTemplate.execute(sql4);
////        插入数据：
        String sql5 = "INSERT INTO therm1 VALUES ('2018-01-01 00:00:00.000', 20)";
        String sql6 = "INSERT INTO therm2 VALUES ('2018-01-01 00:00:00.000', 21)";
        String sql7 = "INSERT INTO therm3 VALUES ('2018-01-01 00:00:00.000', 24)";
        String sql8 = "INSERT INTO therm4 VALUES ('2018-01-01 00:00:00.000', 23)";
        jdbcTemplate.execute(sql5);
        jdbcTemplate.execute(sql6);
        jdbcTemplate.execute(sql7);
        jdbcTemplate.execute(sql8);
    }
}
