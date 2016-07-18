package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.BugPlatformEnum;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.*;
import com.koolearn.qa.service.IBugReportService;
import com.koolearn.qa.service.IMailerService;
import com.koolearn.qa.service.IProductService;
import com.koolearn.qa.service.IProjectService;
import com.koolearn.qa.util.CommonUtil;
import com.koolearn.qa.util.FileUtil;
import com.koolearn.qa.util.PropUtil;
import com.koolearn.qa.util.mail.Mail;
import com.koolearn.qa.util.mail.MailUtil;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Controller
@RequestMapping("/bugReport")
public class BugReportController {

    @Autowired
    private IBugReportService bugReportService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IMailerService mailerService;

    @RequestMapping("/toBugReportDetail")
    public String toBugReportDetail(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isBlank(projectId)) {
            return null;//TODO:添加异常处理
        }
        request.setAttribute("projectId", projectId);
        Project project = projectService.selectById(Integer.valueOf(projectId));
        request.setAttribute("project", project);
        BugReport bugReport = bugReportService.getBugReportByProjectId(Integer.valueOf(projectId));
        request.setAttribute("bugReport", bugReport);
        return "/bugReportDetail";
    }

    @RequestMapping(value = "/toAddBugReport")
    public String toAddBugReport(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isBlank(projectId)) {
            return null;//TODO:添加异常处理
        }
        request.setAttribute("projectId", projectId);
        BugReport bugReport = bugReportService.getBugReportByProjectId(Integer.valueOf(projectId));
        if (bugReport == null) {
            Project project = projectService.selectById(Integer.valueOf(request.getParameter("projectId")));
            Map<String, Object> map = new HashMap<>();
            map.put("project_id", project.getProjectMantis());
            if (StringUtils.isNotBlank(project.getCategoryMantis())) {
                map.put("category", project.getCategoryMantis());
            }
            if (StringUtils.isNotBlank(project.getVersionMantis())) {
                map.put("version", project.getVersionMantis());
            }
            map.put("pKey", project.getpKey());
            BugStatistics bugStatistics = bugReportService.statisticsBySeriousness(map, project.getBugPlatform());
            if (bugStatistics == null) {
                System.out.println("出错啦，未查询到记录");
            } else {
                bugReport = tranToBugReport(bugStatistics);
            }
        }
        request.setAttribute("bugReport", bugReport);
        return "/addBugReport";
    }

    @RequestMapping(value = "/saveBugReport")
    public String saveBugReport(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isBlank(projectId)) {
            return null;//TODO:添加异常处理
        }
        request.setAttribute("projectId", projectId);
        BugReport bugReport = fillBugReportParam(request);
        if (bugReportService.isExist(Integer.valueOf(projectId))) {
            bugReportService.update(bugReport);
        } else {
            bugReportService.insert(bugReport);
        }
        return toBugReportDetail(request, response);
    }

    private BugReport fillBugReportParam(HttpServletRequest request) {
        BugReport bugReport = new BugReport();
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            bugReport.setId(Integer.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(request.getParameter("projectId"))) {
            bugReport.setProjectId(Integer.valueOf(request.getParameter("projectId")));
        }
        if (StringUtils.isNotBlank(request.getParameter("environment"))) {
            bugReport.setEnvironment(request.getParameter("environment"));
        }
        if (StringUtils.isNotBlank(request.getParameter("unresolved"))) {
            bugReport.setUnresolved(request.getParameter("unresolved"));
        }
        if (StringUtils.isNotBlank(request.getParameter("feedback"))) {
            bugReport.setFeedback(request.getParameter("feedback"));
        }
        if (StringUtils.isNotBlank(request.getParameter("testCases"))) {
            bugReport.setTestCases(Integer.valueOf(request.getParameter("testCases")));
        }
        if (StringUtils.isNotBlank(request.getParameter("executedCases"))) {
            bugReport.setExecutedCases(Integer.valueOf(request.getParameter("executedCases")));
        }
        if (StringUtils.isNotBlank(request.getParameter("testResult"))) {
            bugReport.setTestResult(request.getParameter("testResult"));
        }
        if (StringUtils.isNotBlank(request.getParameter("seriousBugs"))) {
            bugReport.setSeriousBugs(Integer.valueOf(request.getParameter("seriousBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("secondaryBugs"))) {
            bugReport.setSecondaryBugs(Integer.valueOf(request.getParameter("secondaryBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("generalBugs"))) {
            bugReport.setGeneralBugs(Integer.valueOf(request.getParameter("generalBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("layoutBugs"))) {
            bugReport.setLayoutBugs(Integer.valueOf(request.getParameter("layoutBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("textBugs"))) {
            bugReport.setTextBugs(Integer.valueOf(request.getParameter("textBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("newfeatureBugs"))) {
            bugReport.setNewfeatureBugs(Integer.valueOf(request.getParameter("newfeatureBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("totalBugs"))) {
            bugReport.setTotalBugs(Integer.valueOf(request.getParameter("totalBugs")));
        }
        return bugReport;
    }

    private BugReport tranToBugReport(BugStatistics bugStatistics) {
        BugReport bugReport = new BugReport();
        bugReport.setSeriousBugs(bugStatistics.getSeriousBugs());
        bugReport.setSecondaryBugs(bugStatistics.getSecondaryBugs());
        bugReport.setGeneralBugs(bugStatistics.getGeneralBugs());
        bugReport.setLayoutBugs(bugStatistics.getLayoutBugs());
        bugReport.setTextBugs(bugStatistics.getTextBugs());
        bugReport.setNewfeatureBugs(bugStatistics.getNewfeatureBugs());
        bugReport.setTotalBugs(bugStatistics.getTotalBugs());
        return bugReport;
    }

    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public String sendMail(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String message = "fail";
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            Project project = projectService.selectById(Integer.valueOf(projectId));
            BugReport bugReport = bugReportService.getBugReportByProjectId(Integer.valueOf(projectId));
            if(bugReport == null){
                message = "null";
                return message;
            }
            Mail mail = new Mail();
            User user = (User) request.getSession().getAttribute("user");
            mail.setFromAddress(user.getUserInfo().getEmail() + Constant.EMAIL_SUFFIX);
            mail.setUserName(user.getUserInfo().getEmail());
            mail.setPassword(user.getPassword());
            if (mailerService.isExist(Integer.valueOf(projectId))) {
                Mailer mailer = mailerService.getMailerByProjectId(Integer.valueOf(projectId));
                List<String> recipients = mailerService.transEmail(mailer.getRecipients());
                List<String> cc = mailerService.transEmail(mailer.getCc());
                if(cc!=null){
                    cc.removeAll(recipients);//去除与发送地址相同的数据
                }
                mail.setToAddress(recipients);
                mail.setCcAddress(cc);
            } else {
                Map<String, List<String>> map = mailerService.getDefaultMail(project);
                mail.setToAddress(map.get("recipients"));
                mail.setCcAddress(map.get("cc"));
            }
            mail.setSubject(project.getName() + "缺陷报告");
            mail.setContent(bugReportService.transHtmlContent(project, bugReport));
            String attachFileName = bugReportService.generateExcelFile(project, bugReport);
            if (attachFileName != null) {
                mail.setAttachFileNames(new String[]{attachFileName});
            }
            if (MailUtil.sendHtmlMail(mail)) {
                message = "success";
            }
            //上传附件后删除生成的临时文件
            if (attachFileName != null) {
                File file = new File(attachFileName);
                FileUtil.deleteFolder(file.getParent());
            }
        }
        return message;
    }

    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            Project project = projectService.selectById(Integer.valueOf(projectId));
            BugReport bugReport = bugReportService.getBugReportByProjectId(Integer.valueOf(projectId));
            bugReportService.exportExcel(response, project, bugReport);
        }
        return toBugReportDetail(request, response);
    }

}
