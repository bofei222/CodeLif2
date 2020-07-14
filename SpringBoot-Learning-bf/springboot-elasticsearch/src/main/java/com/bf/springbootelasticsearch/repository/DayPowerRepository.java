package com.bf.springbootelasticsearch.repository;

import com.bf.springbootelasticsearch.template.DayPower;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

/**
 * @Author bofei
 * @Date 2019/5/23 9:40
 * @Description
 */
public interface DayPowerRepository extends ElasticsearchRepository<DayPower,String> {
//    DayPower findByIdxCodeAndOrgCodeAndIssue(String idx_code, String org_code, String issue);
//    List<DayPower> findByIdxCodeAndOrgCode(String idxCode, String orgCode);
}
