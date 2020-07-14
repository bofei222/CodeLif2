package com.bf.springbooteasypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.bf.springbooteasypoi.entity.Vo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEasypoiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test2() {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<Vo> list = ExcelImportUtil.importExcel(
                new File("D:\\excel\\a.xlsx"),
                Vo.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list);
//        Assert.assertEquals(100,list.size());
//        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
    }
}
