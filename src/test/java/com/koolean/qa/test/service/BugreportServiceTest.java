package com.koolean.qa.test.service;

import com.koolearn.qa.model.BugReport;
import com.koolearn.qa.service.IBugReportService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class BugreportServiceTest {
    @Autowired
    private IBugReportService bugreportService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void insertTest() {
        BugReport bugReport =new BugReport();
        bugReport.setProjectId(1);
        bugReport.setEnvironment("winXP,win7,win8,win10");
        bugReport.setExecutedCases(360);
        bugReport.setFeedback("feedback");
        bugReport.setGeneralBugs(23);
        bugReport.setLayoutBugs(3);
        bugReport.setNewfeatureBugs(1);
        bugReport.setSecondaryBugs(22);
        bugReport.setSeriousBugs(3);
        bugReport.setTestCases(66);
        bugReport.setTestResult("测试结论啊啊啊啊");
        bugReport.setTextBugs(0);
        bugReport.setTotalBugs(45);
        bugReport.setUnresolved("未解决问题啊啊啊啊啊哎哎哎");
        int id = bugreportService.insert(bugReport);
        System.out.println("插入数据库成功，id:"+id);
        BugReport b = bugreportService.selectById(id);
        System.out.println(b.toString());
    }
}
