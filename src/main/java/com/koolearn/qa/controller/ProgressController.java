package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.Product;
import com.koolearn.qa.model.Progress;
import com.koolearn.qa.model.Project;
import com.koolearn.qa.model.User;
import com.koolearn.qa.service.IProductService;
import com.koolearn.qa.service.IProgressService;
import com.koolearn.qa.service.IProjectService;
import com.koolearn.qa.util.CommonUtil;
import com.koolearn.qa.util.FileUtil;
import com.koolearn.qa.util.PropUtil;
import com.koolearn.qa.util.mail.Mail;
import com.koolearn.qa.util.mail.MailUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Controller
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private IProgressService progressService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/toAddProgress")
    public String toAddProgress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException {
        String projectId = request.getParameter("projectId");
        request.setAttribute("projectId", projectId);
        if (StringUtils.isBlank(projectId))
            return "/404";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date());
        if (!progressService.isExist(Integer.valueOf(projectId), formatter.parse(date))) {
            Project project = projectService.selectById(Integer.valueOf(request.getParameter("projectId")));
            Map<String, Object> map = new HashMap<>();
            map.put("project_id", project.getProjectMantis());
            map.put("date", formatter.parse(date));
            if (StringUtils.isNotBlank(project.getCategoryMantis())) {
                map.put("category", project.getCategoryMantis());
            }
            if (StringUtils.isNotBlank(project.getVersionMantis())) {
                map.put("version", project.getVersionMantis());
            }
            request.setAttribute("mantis", progressService.statisticsBystaticEveryday(map));
            request.setAttribute("date", formatter.format(new Date()));
            request.setAttribute("dateBefore", formatter.format(new Date()));
        }
        return "/addProgress";
    }

    @RequestMapping("/getMantis")
    @ResponseBody
    public Map<String, Object> getMantis(HttpServletRequest request, HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        Map<String, Object> message = new HashMap<>();
        String projectId = request.getParameter("projectId");
        String date = request.getParameter("date");
        if (StringUtils.isBlank(request.getParameter("projectId"))) {
            message.put("errMsg", "项目id为空，请联系开发人员");
            return message;
        }
        request.setAttribute("projectId", projectId);
        if (StringUtils.isBlank(date)) {
            message.put("errMsg", "日期不能为空!");
            return message;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //如果已存在记录，不更新记录
        if (progressService.isExist(Integer.valueOf(projectId), formatter.parse(date))) {
            message.put("errMsg", date + "已存在记录，请更换日期!");
            return message;
        }
        Project project = projectService.selectById(Integer.valueOf(projectId));
        Map<String, Object> map = new HashMap<>();
        map.put("project_id", project.getProjectMantis());
        map.put("date", formatter.parse(date));
        if (StringUtils.isNotBlank(project.getCategoryMantis())) {
            map.put("category", project.getCategoryMantis());
        }
        if (StringUtils.isNotBlank(project.getVersionMantis())) {
            map.put("version", project.getVersionMantis());
        }
        message.put("mantis", progressService.statisticsBystaticEveryday(map));
        return message;
    }

    @RequestMapping(value = "/addProgress")
    public String addProgress(HttpServletRequest request, HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(request.getParameter("date"));
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        //判断是否已存在，如果存在不能添加
        if (progressService.isExist(projectId, date)) {
            return toAddProgress(request, response);
        } else {
            Progress dto = fillProgressParam(request);
            progressService.insert(dto);
            request.setAttribute("projectId", projectId);
            List<Progress> list = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            Project project = projectService.selectById(Integer.valueOf(projectId));
            request.setAttribute("project", project);
            request.setAttribute("list", list);
            return "/progressDetail";
        }
    }

    @RequestMapping(value = "/toEditProgress")
    public String toEditProgress(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Progress progress = progressService.selectById(id);
        request.setAttribute("progress", progress);
        return "/editProgress";
    }

    @RequestMapping(value = "/progressList")
    public String progressList(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            List<Progress> list = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            request.setAttribute("list", list);
            return "/progressList";
        }
        return "/projectDetail";
    }

    @RequestMapping(value = "/editProgress")
    public String updateProgress(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Progress progress = fillProgressParam(request);
        progressService.update(progress);
        return progressList(request, response);
    }

    @RequestMapping("/delProgress")
    public String delProgress(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            progressService.delete(Integer.valueOf(request.getParameter("id")));
        }
        return progressList(request, response);
    }

    @RequestMapping("/progressDetail")
    public String toProgressDetail(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            List<Progress> list = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            Project project = projectService.selectById(Integer.valueOf(projectId));
            request.setAttribute("project", project);
            request.setAttribute("list", list);
        }
        return "/progressDetail";
    }

    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public String sendMail(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String message = "fail";
       String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            List<Progress> list = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            Project project = projectService.selectById(Integer.valueOf(projectId));
            Mail mail = new Mail();
            User user = (User) request.getSession().getAttribute("user");
            mail.setFromAddress(user.getUserInfo().getEmail() + Constant.EMAIL_SUFFIX);
            mail.setUserName(user.getUserInfo().getEmail());
            mail.setPassword(user.getPassword());
            StringBuffer sb = new StringBuffer();
            if(StringUtils.isNotBlank(project.getDeveloper())){
                sb.append(project.getDeveloper()).append(Constant.COMMA);
            }
            if(StringUtils.isNotBlank(project.getTester())){
                sb.append(project.getTester()).append(Constant.COMMA);
            }
            if(StringUtils.isNotBlank(project.getProducter())){
                sb.append(project.getProducter());
            }
            List<String> toAddress = transEmail(sb.toString());
            mail.setToAddress(toAddress);
            //抄送人员配置在SystemGloabals文件中
            String cc = PropUtil.getSystemGlobalsProperties("mail.cc");
            List<String> ccAddress =new ArrayList<>(Arrays.asList(cc.split(Constant.COMMA)));
            //负责人添加到抄送人员列表
            Product product =productService.selectById(Integer.valueOf(projectId));
            ccAddress.addAll(transEmail(product.getLeader()));
            ccAddress = CommonUtil.list_unique(ccAddress);//去重
            ccAddress.removeAll(toAddress);//去除与发送地址相同的数据
            mail.setCcAddress(ccAddress);
            mail.setSubject(project.getName() + "进度报告");
            mail.setContent(progressService.transHtmlContent(project,list));
            String attachFileName = progressService.generateExcelFile(project, list);
            if(attachFileName != null){
                mail.setAttachFileNames(new String[]{attachFileName});
            }
            if (MailUtil.sendHtmlMail(mail)) {
                message ="success";
            }
            //上传附件后删除生成的临时文件
            if(attachFileName != null){
                File file = new File(attachFileName);
                FileUtil.deleteFolder(file.getParent());
            }
        }
        return message;
    }


    private Progress fillProgressParam(HttpServletRequest request) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Progress progress = new Progress();
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            progress.setId(Integer.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(request.getParameter("projectId"))) {
            progress.setProjectId(Integer.valueOf(request.getParameter("projectId")));
        }
        if (StringUtils.isNotBlank(request.getParameter("date"))) {
            progress.setDate(formatter.parse(request.getParameter("date")));
        }
        if (StringUtils.isNotBlank(request.getParameter("progress"))) {
            progress.setProgress(request.getParameter("progress"));
        }
        if (StringUtils.isNotBlank(request.getParameter("problem"))) {
            progress.setProblem(request.getParameter("problem"));
        }
        if (StringUtils.isNotBlank(request.getParameter("testCases"))) {
            progress.setTestCases(Integer.valueOf(request.getParameter("testCases")));
        }
        if (StringUtils.isNotBlank(request.getParameter("newBugs"))) {
            progress.setNewBugs(Integer.valueOf(request.getParameter("newBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("assignedBugs"))) {
            progress.setAssignedBugs(Integer.valueOf(request.getParameter("assignedBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("confirmedBugs"))) {
            progress.setConfirmedBugs(Integer.valueOf(request.getParameter("confirmedBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("resolvedBugs"))) {
            progress.setResolvedBugs(Integer.valueOf(request.getParameter("resolvedBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("feedbackBugs"))) {
            progress.setFeedbackBugs(Integer.valueOf(request.getParameter("feedbackBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("closedBugs"))) {
            progress.setClosedBugs(Integer.valueOf(request.getParameter("closedBugs")));
        }
        if (StringUtils.isNotBlank(request.getParameter("status"))) {
            progress.setStatus(Integer.valueOf(request.getParameter("status")));
        }
        return progress;
    }

    private List<String> transEmail(String str) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isBlank(str)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String[] strArray = str.split(Constant.COMMA);
        strArray = CommonUtil.array_unique(strArray);
        for (int i = 0; i < strArray.length; i++) {
            List<LdapUser> userList = ldapService.queryUser("(name=" + strArray[i] + ")");
            LdapUser userInfo = userList.get(0);
            if(userInfo.getEmail().endsWith(Constant.EMAIL_SUFFIX)){
                list.add(userInfo.getEmail());
            }else{
                list.add(userInfo.getEmail()+ Constant.EMAIL_SUFFIX);
            }
        }
       return list;
    }

    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String projectId = request.getParameter("projectId");
        if (StringUtils.isNotBlank(projectId)) {
            request.setAttribute("projectId", projectId);
            List<Progress> list = progressService.getProgressByProjectId(Integer.valueOf(projectId));
            Project project = projectService.selectById(Integer.valueOf(projectId));
            progressService.exportExcel(response,project,list);
        }
        return toProgressDetail(request, response);
    }
}
