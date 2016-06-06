package com.koolearn.qa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/28
 */
public class Mantis {
    //项目id
    private int project_id;
    //项目分类
    private String category;
    //版本号
    private String version;
    //日期
    private Date date;
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
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        if (date != null) {
            return format.format(date);
        }
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Mantis{" +
                "project_id=" + project_id +
                ", category='" + category + '\'' +
                ", version='" + version + '\'' +
                ", date=" + getDate() +
                ", newBugs=" + newBugs +
                ", assignedBugs=" + assignedBugs +
                ", confirmedBugs=" + confirmedBugs +
                ", resolvedBugs=" + resolvedBugs +
                ", feedbackBugs=" + feedbackBugs +
                ", closedBugs=" + closedBugs +
                ", seriousBugs=" + seriousBugs +
                ", secondaryBugs=" + secondaryBugs +
                ", generalBugs=" + generalBugs +
                ", layoutBugs=" + layoutBugs +
                ", textBugs=" + textBugs +
                ", newfeatureBugs=" + newfeatureBugs +
                ", totalBugs=" + totalBugs +
                '}';
    }
}
