package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.BugPlatformEnum;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.constant.ProjectStatusEnum;
import com.koolearn.qa.model.*;
import com.koolearn.qa.service.*;
import com.koolearn.qa.util.FileUtil;
import com.koolearn.qa.util.MppUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProductService productService;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IProgressService progressService;

    @Autowired
    private IPlanService planService;

    @Autowired
    private IBugReportService bugReportService;

    @Autowired
    private IMailerService mailerService;

    @RequestMapping(value = "/toProjectManager")
    public String toProjectManager(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        List<Project> list = projectService.getAllValidProjects();
        request.setAttribute("list", list);
        return "/projectManager";
    }

    @RequestMapping(value = "/toAddProject")
    public String toAddProject(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        request.setAttribute("bugPlatform", BugPlatformEnum.values());
        return "/addProject";
    }

    @RequestMapping(value = "/addProject")
    public String addProject(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Project dto = fillProjectParam(request);
        projectService.insert(dto);
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        return toProjectManager(request, response);
    }

    @RequestMapping(value = "/toEditProject")
    public String toEditProject(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            Project project = projectService.selectById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("project", project);
        }
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        request.setAttribute("bugPlatform", BugPlatformEnum.values());
        return "/editProject";
    }

    @RequestMapping(value = "/updateProject")
    public String updateProject(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Project dto = fillProjectParam(request);
        projectService.updateByPrimaryKey(dto);
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        return toProjectManager(request, response);
    }

    @RequestMapping(value = "/getProjectsByProductId")
    public String getProjectsByProductId(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("productId"))) {
            List<Project> list = projectService.getProjectsByProductId(Integer.valueOf(request.getParameter("productId")));
            request.setAttribute("list", list);
        }
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        return "/projectList";
    }

    @RequestMapping("/delProject")
    public String delProject(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            projectService.delete(Integer.valueOf(request.getParameter("id")));
        }
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        return toProjectManager(request, response);
    }

    @RequestMapping("/getProjects")
    public String getProjectsByConditions(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> searchConditions = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(request.getParameter("productId"))) {
            map.put("productId", Integer.valueOf(request.getParameter("productId")));
            searchConditions.put("productId", request.getParameter("productId"));
        }
        if (StringUtils.isNotBlank(request.getParameter("name"))) {
            map.put("name", request.getParameter("name"));
            searchConditions.put("name", request.getParameter("name"));
        }
        if (StringUtils.isNotBlank(request.getParameter("startTimePlan"))) {
            map.put("startTimePlan", formatter.parse(request.getParameter("startTimePlan")));
            searchConditions.put("startTimePlan", request.getParameter("startTimePlan"));
        }
        if (StringUtils.isNotBlank(request.getParameter("endTimePlan"))) {
            map.put("endTimePlan", formatter.parse(request.getParameter("endTimePlan")));
            searchConditions.put("endTimePlan", request.getParameter("endTimePlan"));
        }
        if (StringUtils.isNotBlank(request.getParameter("startTimeActual"))) {
            map.put("startTimeActual", formatter.parse(request.getParameter("startTimeActual")));
            searchConditions.put("startTimeActual", request.getParameter("startTimeActual"));
        }
        if (StringUtils.isNotBlank(request.getParameter("endTimeActual"))) {
            map.put("endTimeActual", formatter.parse(request.getParameter("endTimeActual")));
            searchConditions.put("endTimeActual", request.getParameter("endTimeActual"));
        }
        if (StringUtils.isNotBlank(request.getParameter("projectStatus"))) {
            map.put("projectStatus", Integer.valueOf(request.getParameter("projectStatus")));
            searchConditions.put("projectStatus", request.getParameter("projectStatus"));
        }
        if (StringUtils.isNotBlank(request.getParameter("tester"))) {
            map.put("tester", request.getParameter("tester"));
            searchConditions.put("tester", request.getParameter("tester"));
        }
        List<Project> list = projectService.getProjectsByConditions(map);
        request.setAttribute("searchConditions", searchConditions);
        request.setAttribute("list", list);
        request.setAttribute("productMap", productService.getProductMap());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        request.setAttribute("projectStatus", ProjectStatusEnum.values());
        return "/projectManager";
    }

    @RequestMapping(value = "/projectDetail")
    public String projectDetail(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String projectId = request.getParameter("id");
        if (StringUtils.isNotBlank(projectId)) {
            Project project = projectService.selectById(Integer.valueOf(projectId));
            request.setAttribute("project", project);
            request.setAttribute("productMap", productService.getProductMap());
            request.setAttribute("projectStatus", ProjectStatusEnum.values());
            request.setAttribute("bugPlatform", BugPlatformEnum.values());
            List<Progress> progressList = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            request.setAttribute("progressList", progressList);
            Plan plan = planService.getPlanByProjectId(Integer.valueOf(projectId));
            if (plan != null) {
                request.setAttribute("plan", plan);
                String filePath = FileUtil.getAbsolutePath(plan.getFilePath());
                request.setAttribute("treeJson", MppUtil.dumpJson(filePath));
            }
            BugReport bugReport = bugReportService.getBugReportByProjectId(Integer.valueOf(projectId));
            request.setAttribute("bugReport", bugReport);
        }
        return "/projectDetail";
    }

    @RequestMapping("/getEmails")
    public String getEmails(HttpServletRequest request, HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId",projectId);
            Mailer mailer = mailerService.getMailerByProjectId(Integer.valueOf(projectId));
            if(mailer!=null){
                request.setAttribute("recipients",mailer.getRecipients());
                request.setAttribute("cc",mailer.getCc());
            }else{
                Project project = projectService.selectById(Integer.valueOf(projectId));
                Map<String,String> mailName = mailerService.getDefaultName(project);
                request.setAttribute("recipients",mailName.get("recipients"));
                request.setAttribute("cc",mailName.get("cc"));
            }
        }
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return "/mailManager";
    }

    @RequestMapping("/saveEmails")
    public String saveEmails(HttpServletRequest request, HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            Mailer mailer = new Mailer();
            mailer.setProjectId(Integer.valueOf(projectId));
            if(StringUtils.isNotBlank(request.getParameter("recipients"))){
                mailer.setRecipients(String.join(Constant.COMMA, request.getParameterValues("recipients")));
            }
            if(StringUtils.isNotBlank(request.getParameter("cc"))){
                mailer.setCc(String.join(Constant.COMMA, request.getParameterValues("cc")));
            }
            Mailer m = mailerService.getMailerByProjectId(Integer.valueOf(projectId));
            if(m!=null){
                mailer.setId(m.getId());
                mailerService.update(mailer);
            }else{
                mailerService.insert(mailer);
            }
        }
        return toProjectManager(request,response);
    }

    private Project fillProjectParam(HttpServletRequest request) throws ParseException {
        Project project = new Project();
        if (request.getParameter("id") != null) {
            project.setId(Integer.valueOf(request.getParameter("id")));
        }
        String bugPlatform = request.getParameter("bugPlatform");
        if (bugPlatform != null) {
            project.setBugPlatform(Integer.valueOf(bugPlatform));
            if (bugPlatform.equals(BugPlatformEnum.mantis.getValue())) {
                if (StringUtils.isNotBlank(request.getParameter("projectMantis"))) {
                    project.setProjectMantis(Integer.valueOf(request.getParameter("projectMantis")));
                }
                if (StringUtils.isNotBlank(request.getParameter("categoryMantis"))) {
                    project.setCategoryMantis(request.getParameter("categoryMantis"));
                }

                if (StringUtils.isNotBlank(request.getParameter("versionMantis"))) {
                    project.setVersionMantis(request.getParameter("versionMantis"));
                }
                project.setpKey(null);
            } else if (bugPlatform.equals(BugPlatformEnum.jira.getValue())) {
                if (StringUtils.isNotBlank(request.getParameter("pKey"))) {
                    project.setpKey(request.getParameter("pKey"));
                }
                if (StringUtils.isNotBlank(request.getParameter("issuenum"))) {
                    project.setIssuenum(request.getParameter("issuenum"));
                }
                project.setProjectMantis(null);
                project.setCategoryMantis(null);
                project.setVersionMantis(null);
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("productId"))) {
            project.setProductId(Integer.valueOf(request.getParameter("productId")));
        }
        if (StringUtils.isNotBlank(request.getParameter("name"))) {
            project.setName(request.getParameter("name"));
        }
        if (request.getParameterValues("producter") != null) {
            project.setProducter(String.join(Constant.COMMA, request.getParameterValues("producter")));
        }
        if (request.getParameterValues("developer") != null) {
            project.setDeveloper(String.join(Constant.COMMA, request.getParameterValues("developer")));
        }
        if (request.getParameterValues("tester") != null) {
            project.setTester(String.join(Constant.COMMA, request.getParameterValues("tester")));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startTime2plan = request.getParameter("startTime2plan");
        if (StringUtils.isNotBlank(startTime2plan)) {
            project.setStartTime2plan(formatter.parse(startTime2plan));
        }
        if (StringUtils.isNotBlank(request.getParameter("endTime2plan"))) {
            project.setEndTime2plan(formatter.parse(request.getParameter("endTime2plan")));
        }
        if (StringUtils.isNotBlank(request.getParameter("startTime2actual"))) {
            project.setStartTime2actual(formatter.parse(request.getParameter("startTime2actual")));
        }
        if (StringUtils.isNotBlank(request.getParameter("endTime2actual"))) {
            project.setEndTime2actual(formatter.parse(request.getParameter("endTime2actual")));
        }
        if (StringUtils.isNotBlank(request.getParameter("projectStatus"))) {
            project.setProjectStatus(Integer.valueOf(request.getParameter("projectStatus")));
        }
        if (StringUtils.isNotBlank(request.getParameter("days"))) {
            project.setDays(Double.valueOf(request.getParameter("days")));
        }
        if (StringUtils.isNotBlank(request.getParameter("requirementDays"))) {
            project.setRequirementDays(Double.valueOf(request.getParameter("requirementDays")));
        }
        if (StringUtils.isNotBlank(request.getParameter("developDays"))) {
            project.setDevelopDays(Double.valueOf(request.getParameter("developDays")));
        }
        if (StringUtils.isNotBlank(request.getParameter("testDays"))) {
            project.setTestDays(Double.valueOf(request.getParameter("testDays")));
        }
        if (StringUtils.isNotBlank(request.getParameter("acceptanceDays"))) {
            project.setAcceptanceDays(Double.valueOf(request.getParameter("acceptanceDays")));
        }
        if (StringUtils.isNotBlank(request.getParameter("onlineDays"))) {
            project.setOnlineDays(Double.valueOf(request.getParameter("onlineDays")));
        }
        if (StringUtils.isNotBlank(request.getParameter("description"))) {
            project.setDescription(request.getParameter("description"));
        }
        if (StringUtils.isNotBlank(request.getParameter("status"))) {
            project.setStatus(Integer.valueOf(request.getParameter("status")));
        }
        return project;
    }
}
