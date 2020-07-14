package com.bf.springbootelasticsearch.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author bofei
 * @Date 2019/5/23 9:31
 * @Description
 */
@Data
@Document(indexName = "dm_mkt_idx_day", type = "default")
public class DayPower implements Serializable {

    @Id
    private String id;
    private String issue;

//    @JsonProperty("org_code")
    private String org_code;

    private String org_name;

//    @JsonProperty("idx_code")
    private String idx_code;

    private String idx_name;
    private String dim_type_ode;
    private String dim_code;
    private String dim_name;
    private Double val;
    private Date upd_time;
}



