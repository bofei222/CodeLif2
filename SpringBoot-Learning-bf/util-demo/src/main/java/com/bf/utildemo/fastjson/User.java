package com.bf.utildemo.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(name="name_Info")
    private String nameInfo;

    private String ageInfo;

//    @JSONField(name = "sexInfo")
    private String sex_info;

    public String getSex_info() {
        return sex_info;
    }

    public void setSex_info(String sex_info) {
        this.sex_info = sex_info;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(String ageInfo) {
        this.ageInfo = ageInfo;
    }
}
