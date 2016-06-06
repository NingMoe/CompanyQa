package com.koolearn.qa.model;

/**
 * @author lihuiyan
 * @description 测试报告模型
 * @date 2016/4/21
 */
public class BugReport {
    //主键
    private int id;
    //项目id
    private int projectId;
    //测试环境
    private String environment;
    //未解决问题
    private String unresolved;
    //反馈问题
    private String feedback;
    //测试用例数
    private int testCases;
    //测试用例执行总数
    private int executedCases;
    //测试结论
    private String testResult;
    //严重错误数
    private int seriousBugs;
    //次要错误数
    private int secondaryBugs;
    //一般错误数
    private int generalBugs;
    //布局错误数
    private int layoutBugs;
    //文字错误数
    private int textBugs;
    //新特性错误数
    private int newfeatureBugs;
    //bug总数
    private int totalBugs;
    //状态
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUnresolved() {
        return unresolved;
    }

    public void setUnresolved(String unresolved) {
        this.unresolved = unresolved;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getTestCases() {
        return testCases;
    }

    public void setTestCases(int testCases) {
        this.testCases = testCases;
    }

    public int getExecutedCases() {
        return executedCases;
    }

    public void setExecutedCases(int executedCases) {
        this.executedCases = executedCases;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public int getSeriousBugs() {
        return seriousBugs;
    }

    public void setSeriousBugs(int seriousBugs) {
        this.seriousBugs = seriousBugs;
    }

    public int getSecondaryBugs() {
        return secondaryBugs;
    }

    public void setSecondaryBugs(int secondaryBugs) {
        this.secondaryBugs = secondaryBugs;
    }

    public int getGeneralBugs() {
        return generalBugs;
    }

    public void setGeneralBugs(int generalBugs) {
        this.generalBugs = generalBugs;
    }

    public int getLayoutBugs() {
        return layoutBugs;
    }

    public void setLayoutBugs(int layoutBugs) {
        this.layoutBugs = layoutBugs;
    }

    public int getTextBugs() {
        return textBugs;
    }

    public void setTextBugs(int textBugs) {
        this.textBugs = textBugs;
    }

    public int getNewfeatureBugs() {
        return newfeatureBugs;
    }

    public void setNewfeatureBugs(int newfeatureBugs) {
        this.newfeatureBugs = newfeatureBugs;
    }

    public int getTotalBugs() {
        return totalBugs;
    }

    public void setTotalBugs(int totalBugs) {
        this.totalBugs = totalBugs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BugReport{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", environment='" + environment + '\'' +
                ", unresolved='" + unresolved + '\'' +
                ", feedback='" + feedback + '\'' +
                ", testCases=" + testCases +
                ", executedCases=" + executedCases +
                ", testResult='" + testResult + '\'' +
                ", seriousBugs=" + seriousBugs +
                ", secondaryBugs=" + secondaryBugs +
                ", generalBugs=" + generalBugs +
                ", layoutBugs=" + layoutBugs +
                ", textBugs=" + textBugs +
                ", newfeatureBugs=" + newfeatureBugs +
                ", totalBugs=" + totalBugs +
                ", status=" + status +
                '}';
    }
}
