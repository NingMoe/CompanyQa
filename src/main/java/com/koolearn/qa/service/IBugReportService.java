package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.BugReport;
import com.koolearn.qa.model.BugStatistics;
import com.koolearn.qa.model.Mantis;
import com.koolearn.qa.model.Project;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface IBugReportService extends GenericService<BugReport,Integer> {

    /**
     * 按照项目查询测试报告记录
     * @param projectId
     * @return
     */
    BugReport getBugReportByProjectId(Integer projectId);

    /**
     * 判断某个项目测试报告是否已存在
     * @param projectId
     * @return
     */
    Boolean isExist(Integer projectId);

    /**
     * 缺陷报告bug统计数
     * @param map
     * @return
     */
    BugStatistics statisticsBySeriousness(Map<String,Object> map,int platform) throws UnsupportedEncodingException;

    /**
     * 缺陷报告表格（用于邮件发送）
     * @param project
     * @param bugReport
     * @return
     */
    String transHtmlContent(Project project, BugReport bugReport);

    /**
     * 导出测试报告到页面下载
     * @param response
     * @param project
     * @param bugReport
     */
    void exportExcel(HttpServletResponse response, Project project, BugReport bugReport);

    /**
     * 生成测试报告Excel文件
     * @param project
     * @param bugReport
     * @return
     */
    String generateExcelFile(Project project, BugReport bugReport);
}
