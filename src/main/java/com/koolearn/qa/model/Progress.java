package com.koolearn.qa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lihuiyan
 * @description 项目进度模型
 * @date 2016/4/21
 */
public class Progress {
    //主键
    private int id;
    //项目id
    private int projectId;
    //进度日期
    private Date date;
    //进度描述
    private String progress;
    //问题描述
    private String problem;
    //已测试用例数
    private int testCases;
    //新提交bug数
    private int newBugs;
    //已指派bug数
    private int assignedBugs;
    //已确认bug数
    private int confirmedBugs;
    //已解决bug数
    private int resolvedBugs;
    //反馈bug数
    private int feedbackBugs;
    //已关闭bug数
    private int closedBugs;
    //状态
    private int status;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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

    public String getDate() {
        if(date!=null){
            return format.format(date);
        }
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getTestCases() {
        return testCases;
    }

    public void setTestCases(int testCases) {
        this.testCases = testCases;
    }

    public int getNewBugs() {
        return newBugs;
    }

    public void setNewBugs(int newBugs) {
        this.newBugs = newBugs;
    }

    public int getAssignedBugs() {
        return assignedBugs;
    }

    public void setAssignedBugs(int assignedBugs) {
        this.assignedBugs = assignedBugs;
    }

    public int getConfirmedBugs() {
        return confirmedBugs;
    }

    public void setConfirmedBugs(int confirmedBugs) {
        this.confirmedBugs = confirmedBugs;
    }

    public int getResolvedBugs() {
        return resolvedBugs;
    }

    public void setResolvedBugs(int resolvedBugs) {
        this.resolvedBugs = resolvedBugs;
    }

    public int getFeedbackBugs() {
        return feedbackBugs;
    }

    public void setFeedbackBugs(int feedbackBugs) {
        this.feedbackBugs = feedbackBugs;
    }

    public int getClosedBugs() {
        return closedBugs;
    }

    public void setClosedBugs(int closedBugs) {
        this.closedBugs = closedBugs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", date=" + getDate() +
                ", progress='" + progress + '\'' +
                ", problem='" + problem + '\'' +
                ", testCases=" + testCases +
                ", newBugs=" + newBugs +
                ", assignedBugs=" + assignedBugs +
                ", confirmedBugs=" + confirmedBugs +
                ", resolvedBugs=" + resolvedBugs +
                ", feedbackBugs=" + feedbackBugs +
                ", closedBugs=" + closedBugs +
                ", status=" + status +
                '}';
    }
}
