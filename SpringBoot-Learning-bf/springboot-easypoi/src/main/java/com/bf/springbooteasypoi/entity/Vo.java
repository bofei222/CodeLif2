package com.bf.springbooteasypoi.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;

/**
 * @Author bofei
 * @Date 2019/6/17 15:32
 * @Description
 */
@Data
public class Vo {

    @ExcelEntity()
    private Student student;

    @ExcelEntity()
    private Apple apple;
}
