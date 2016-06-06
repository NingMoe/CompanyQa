package com.koolean.qa.test.dao;

import com.koolearn.qa.dao.platform.ProjectMapper;
import com.koolearn.qa.model.Project;
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
public class ProjectMapperTest {
    @Autowired
    private ProjectMapper projectMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void insertSelectiveTest() {
        Project project = new Project();
        project.setName("测试测试");
        project.setStatus(1);
        project.setVersionMantis("测试平台一期");
        project.setProjectMantis(125);
        project.setProducter("111111");
        project.setDeveloper("22222222");
        project.setTester("3333,4444");
        projectMapper.insertSelective(project);
    }
}
