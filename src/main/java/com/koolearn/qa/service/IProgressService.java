package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.BugStatistics;
import com.koolearn.qa.model.Mantis;
import com.koolearn.qa.model.Progress;
import com.koolearn.qa.model.Project;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface IProgressService extends GenericService<Progress,Integer> {

    /**
     * 按照项目查询进度报告记录
     * @return
     */
    List<Progress> getProgressByProjectId(Integer projectId);

    /**
     * 获取最近一次的进度
     * @return
     */
    Progress getLastProgress(Integer projectId);

    /**
     * 判断某个项目某一天的进度报告是否已存在
     * @param projectId
     * @param date
     * @return
     */
    Boolean isExist(Integer projectId,Date date);

    /**
     * 进度报告bug统计数
     * @param map
     * @return
     */
    BugStatistics statisticsBystaticEveryday(Map<String,Object> map, int platform) throws UnsupportedEncodingException;

    /**
     * html邮件内容
     * @param project
     * @param list
     * @return
     */
    String transHtmlContent(Project project,List<Progress> list);

    /**
     * 导出进入报告Excel到页面下载
     * @param response
     * @param project
     * @param list
     */
    void exportExcel(HttpServletResponse response, Project project, List<Progress>list);

    /**
     * 生成进度报告Excel文件
     * @param project
     * @param list
     * @return
     */
    String generateExcelFile(Project project, List<Progress>list);

}
