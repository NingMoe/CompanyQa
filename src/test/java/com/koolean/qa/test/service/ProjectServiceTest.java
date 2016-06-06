package com.koolean.qa.test.service;

import com.koolearn.qa.model.Project;
import com.koolearn.qa.service.IProjectService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ProjectServiceTest {
    @Autowired
    private IProjectService projectService;

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
        Project project = new Project();
        project.setName("测试测试");
        project.setStatus(1);
        project.setVersionMantis("测试平台一期");
        project.setProjectMantis(125);
        project.setProducter("111111");
        project.setDeveloper("22222222");
        project.setTester("3333,4444");
        projectService.insert(project);
    }

    @Test
    @Transactional
    public void getProjectsByConditionsTest() throws ParseException {
        Map<String,Object> map = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        map.put("startTimeActual",df.parse("2016-4-1 00:51:07 "));
        map.put("endTimeActual",df.parse("2016-4-21 00:51:07 "));
        List<Project> list = projectService.getProjectsByConditions(map);
        for (Project project:list
             ) {
            System.out.println(project.toString());
        }
    }
}
