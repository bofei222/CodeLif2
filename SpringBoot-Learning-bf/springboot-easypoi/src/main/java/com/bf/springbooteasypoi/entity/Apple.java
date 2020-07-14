package com.bf.springbooteasypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author bofei
 * @Date 2019/6/17 15:34
 * @Description
 */
@Data
public class Apple implements Serializable {
    @Excel(name = "颜色")
    private String color;
}
