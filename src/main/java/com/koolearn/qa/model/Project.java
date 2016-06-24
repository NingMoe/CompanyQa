package com.koolearn.qa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lihuiyan
 * @description 项目模型
 * @date 2016/4/21
 */
public class Project {
    //主键
    private int id;
    //所属系统
    private int productId;
    //项目名称
    private String name;
    //bug统计平台
    private int bugPlatform;

    //mantis项目id
    private int projectMantis;
    //mantis项目分类
    private String categoryMantis;
    //mantis版本号
    private String versionMantis;

    //jira键值
    private String pKey;

    //产品人员
    private String producter;
    //开发人员
    private String developer;
    //测试人员
    private String tester;
    //计划开始时间
    private Date startTime2plan;
    //计划结束时间
    private Date endTime2plan;
    //实际开始时间
    private Date startTime2actual;
    //实际结束时间
    private Date endTime2actual;
    //项目完成状态
    private int projectStatus;
    //提前/延期天数
    private double days;
    //需求阶段天数
    private double requirementDays;
    //代码阶段天数
    private double developDays;
    //测试阶段天数
    private double testDays;
    //业务验收阶段天数
    private double acceptanceDays;
    //上线试运行阶段天数
    private double onlineDays;
    //项目说明
    private String description;
    //项目状态
    private int status;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBugPlatform() {
        return bugPlatform;
    }

    public void setBugPlatform(int bugPlatform) {
        this.bugPlatform = bugPlatform;
    }

    public int getProjectMantis() {
        return projectMantis;
    }

    public void setProjectMantis(int projectMantis) {
        this.projectMantis = projectMantis;
    }

    public String getCategoryMantis() {
        return categoryMantis;
    }

    public void setCategoryMantis(String categoryMantis) {
        this.categoryMantis = categoryMantis;
    }

    public String getVersionMantis() {
        return versionMantis;
    }

    public void setVersionMantis(String versionMantis) {
        this.versionMantis = versionMantis;
    }

    public String getpKey() {
        return pKey;
    }

    public void setpKey(String pKey) {
        this.pKey = pKey;
    }

    public String getProducter() {
        return producter;
    }

    public void setProducter(String producter) {
        this.producter = producter;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getStartTime2plan() {
        if (startTime2plan != null) {
            return format.format(startTime2plan);
        }
        return null;
    }

    public void setStartTime2plan(Date startTime2plan) {
        this.startTime2plan = startTime2plan;
    }

    public String getEndTime2plan() {
        if (endTime2plan != null) {
            return format.format(endTime2plan);
        }
        return null;
    }

    public void setEndTime2plan(Date endTime2plan) {
        this.endTime2plan = endTime2plan;
    }

    public String getStartTime2actual() {
        if (startTime2actual != null) {
            return format.format(startTime2actual);
        }
        return null;
    }

    public void setStartTime2actual(Date startTime2actual) {
        this.startTime2actual = startTime2actual;
    }

    public String getEndTime2actual() {
        if (endTime2actual != null) {
            return format.format(endTime2actual);
        }
        return null;
    }

    public void setEndTime2actual(Date endTime2actual) {
        this.endTime2actual = endTime2actual;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public double getRequirementDays() {
        return requirementDays;
    }

    public void setRequirementDays(double requirementDays) {
        this.requirementDays = requirementDays;
    }

    public double getDevelopDays() {
        return developDays;
    }

    public void setDevelopDays(double developDays) {
        this.developDays = developDays;
    }

    public double getTestDays() {
        return testDays;
    }

    public void setTestDays(double testDays) {
        this.testDays = testDays;
    }

    public double getAcceptanceDays() {
        return acceptanceDays;
    }

    public void setAcceptanceDays(double acceptanceDays) {
        this.acceptanceDays = acceptanceDays;
    }

    public double getOnlineDays() {
        return onlineDays;
    }

    public void setOnlineDays(double onlineDays) {
        this.onlineDays = onlineDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ",bugPlatform='"+bugPlatform+'\'' +
                ", projectMantis='" + projectMantis + '\'' +
                ", categoryMantis='" + categoryMantis + '\'' +
                ", versionMantis='" + versionMantis + '\'' +
                ",pKey='"+pKey+'\''+
                ", producter='" + producter + '\'' +
                ", developer='" + developer + '\'' +
                ", tester='" + tester + '\'' +
                ", startTime2plan=" + getStartTime2plan() +
                ", endTime2plan=" + getEndTime2plan() +
                ", startTime2actual=" + getStartTime2actual() +
                ", endTime2actual=" + getEndTime2actual() +
                ", projectStatus=" + projectStatus +
                ", days=" + days +
                ", requirementDays=" + requirementDays +
                ", developDays=" + developDays +
                ", testDays=" + testDays +
                ", acceptanceDays=" + acceptanceDays +
                ", onlineDays=" + onlineDays +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
