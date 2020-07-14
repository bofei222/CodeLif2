package com.bf.springbooteasypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author bofei
 * @Date 2019/6/20 19:24
 * @Description
 */
@Data
public class Student implements Serializable {
    @Excel(name = "名字")
    private String name;

    @Excel(name = "性别")
    private String sex;
}
