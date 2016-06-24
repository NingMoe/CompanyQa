package com.koolean.qa.test.service;

import com.koolearn.qa.constant.BugPlatformEnum;
import com.koolearn.qa.model.BugStatistics;
import com.koolearn.qa.model.Mantis;
import com.koolearn.qa.model.Progress;
import com.koolearn.qa.service.IProgressService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ProgressServiceTest {
    @Autowired
    private IProgressService progressService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    public void insertTest() {
//        Progress progress = new Progress();
//        progress.setDate(new Date());
//        progress.setProblem("问题问题问题");
//        progress.setProjectId(1);
//        progress.setProgress("进度进度进度");
//        progress.setAssignedBugs(2);
//        progress.setConfirmedBugs(4);
//        progress.setClosedBugs(5);
//        progress.setFeedbackBugs(1);
//        progress.setNewBugs(10);
//        progress.setResolvedBugs(90);
//        int id = progressService.insert(progress);
//        System.out.println("数据库插入成功，id:"+id);
//    }
//
//    @Test
//    public void getProgressByProjectId(){
//        List<Progress> list = progressService.getProgressByProjectId(1);
//        for(Progress progress:list){
//            System.out.println(progress.toString());
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void updateTest(){
//        Progress progress = new Progress();
//        progress.setId(1);
//        progress.setProblem("1111111修改修改问题问题问题");
//        progress.setProgress("11111111111修改修改进度进度进度");
//        progress.setResolvedBugs(1);
//        int id = progressService.update(progress);
//        System.out.println("数据库更新成功，id:"+id);
//    }

   @Test
    public void statisticsBystaticEverydayTest() throws ParseException, UnsupportedEncodingException {
       System.out.println("Default Charset=" + Charset.defaultCharset());
       System.out.println("file.encoding=" + System.getProperty("file.encoding"));
       System.out.println("Default Charset=" + Charset.defaultCharset());
       System.out.println("Default Charset in Use=" + getDefaultCharSet());
        Map<String, Object> map = new HashMap<>();
        map.put("project_id", 125);
        //map.put("category", "测试");
         map.put("version",new String( "一期"));
       // map.put("version", new String("一期".getBytes(), "latin1"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        map.put("date", formatter.parse("2016-05-03"));
        BugStatistics mantis = progressService.statisticsBystaticEveryday(map, Integer.valueOf(BugPlatformEnum.mantis.getValue()));
        Assert.assertNotNull("<<<<<<<<<<<<<<<<<<<<数据库返回信息为空", mantis);
        System.out.println("<<<<<<<<<<<<<<<<<<<<" + mantis.toString());
    }


    @Test
    public void statisticsBySeriousness() throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        map.put("project_id",125);
        map.put("version",new String( "一期"));
       // map.put("version",new String("一期".getBytes(), "latin1"));
        //map.put("category","测试");
      //  Mantis mantis = progressService.statisticsBySeriousness(map);
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
}
