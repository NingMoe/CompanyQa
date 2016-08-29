package com.koolearn.qa.model;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/28
 */
public class Mantis extends BugStatistics{
    //项目id
    private int project_id;
    //项目分类
    private String category;
    //版本号
    private String version;


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
