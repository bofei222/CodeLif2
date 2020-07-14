package com.bf.utildemo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.junit.Assert;

import java.util.regex.Matcher;

public class Test {
    @org.junit.Test
    public void test1() {
        // 生产环境中，config要做singleton处理，要不然会存在性能问题
        User user = new User();
        user.setNameInfo("coder");
        user.setAgeInfo("28");
        user.setSex_info("女");


        // 默认驼峰风格 （错了， 默认和属性名一致）
        System.out.println(JSON.toJSONString(user));

        // 序列化配置对象
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;

        // 序列化对象
        // JSONField配置 大于 全局配置
        String json = JSON.toJSONString(user, config);
        System.out.println(json);

//        Assert.assertEquals("{\"age_info\":\"28\",\"name_info\":\"coder\"}", json);


        // 反序列化配置对象  反序列化配置 有啥用
        ParserConfig parserConfig = new ParserConfig();
        parserConfig.propertyNamingStrategy = PropertyNamingStrategy.KebabCase;  // 好像没用啊


        User user2 = JSON.parseObject(json, User.class, parserConfig);


        String s = JSONObject.toJSONString(user2);
        System.out.println(s);

        JSONObject jo = new JSONObject();
        jo.put("customer_name", "bf");

//        String str = JSON.toJSONString(jo, new NameFilter() {
//
//            @Override
//            public String process(Object object, String name, Object value) {
//                Matcher matcher = humpPattern.matcher(name);
//                StringBuffer sb = new StringBuffer();
//                while (matcher.find()) {
//                    matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
//                }
//                matcher.appendTail(sb);
//                return sb.toString();
//            }
//
//        });
//        System.out.println(jsonStr);

    }
}
