package com.bf.springbootelasticsearch;

import com.bf.springbootelasticsearch.repository.DayPowerRepository;
import com.bf.springbootelasticsearch.template.DayPower;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

    @Autowired
    private DayPowerRepository dayPowerRepository;

    @Test
    public void test5() {
        Pageable pageable = PageRequest.of(0, 10);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("idx_code", "I0310901"))
                .must(QueryBuilders.matchQuery("org_code", "4120"))
                .must(QueryBuilders.matchQuery("dim_code", "*"))
                .must(QueryBuilders.rangeQuery("issue").from("20190403").to("20190503"));
//                .must(QueryBuilders.matchQuery("issue", "20190503"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        Iterable<DayPower> result = dayPowerRepository.search(searchQuery);


        for (DayPower dayPower : result) {
            System.out.println(dayPower.getIssue() + " " + dayPower.getOrg_name() + " " + dayPower.getVal());
        }
    }
    @Test
    public void test3() {
        String s = LocalDate.parse("20190503", DateTimeFormatter.ofPattern("yyyyMMdd")).plusDays(-30).toString();
        System.out.println(s);
    }


//
//    @Test
//    public void test2() {
//        NativeSearchQueryBuilder i0310901 = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("I0310901").field("I0310901"))
//                .withPageable(new PageRequest(1, 10));
//        NativeSearchQuery build = i0310901.build();
//
//
//        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
//        Date parse = null;
//        try {
//            parse = dt1.parse("20190503");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        MatchQueryBuilder issue = QueryBuilders.matchQuery("issue", String.valueOf(parse.getTime()));
//        NativeSearchQuery build1 = new NativeSearchQueryBuilder().withQuery(issue).build();
//
//        QueryStringQueryBuilder a = QueryBuilders.queryStringQuery("I0310901");
//        QueryStringQueryBuilder idx_code = a.field("idx_code");
//    }
//
//
//    @Test
//    public void test1() {
//
//        QueryStringQueryBuilder q = new QueryStringQueryBuilder("I0310901");
//        QueryStringQueryBuilder q2 = new QueryStringQueryBuilder("4120");
//        QueryStringQueryBuilder q3 = new QueryStringQueryBuilder("20190503");
//        QueryStringQueryBuilder q4 = new QueryStringQueryBuilder("*");
//
//        q.field("idx_code");
//        q2.field("org_code");
//        q3.field("issue");
//        q4.field("dim_code");
//
//        QueryBuilder qb3 = QueryBuilders.boolQuery().must(q).must(q2).must(q3).must(q4);
//        Iterable<DayPower> result = dayPowerRepository.search(qb3);
//
//        for (DayPower day : result) {
//            System.out.println(day.getIssue() + " " + day.getIdx_name() + " " + day.getOrg_name() + " " + day.getVal());
//        }
//
//    }

}
