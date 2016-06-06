package com.koolean.qa.test.service;

import com.koolearn.qa.model.Plan;
import com.koolearn.qa.service.IPlanService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:applicationContext.xml"})
public class PlanServiceTest {
    @Autowired
    private IPlanService planService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(true)
    public void insertTest(){
        Plan plan = new Plan();
        plan.setFilePath("c:\\a.txt");
        plan.setProjectId(1);
        plan.setFileName("a.txt");
        plan.setCreateTime(new Date());
        int id = planService.insert(plan);
        System.out.println("插入数据库成功："+id);
    }
}
