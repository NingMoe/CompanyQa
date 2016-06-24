package com.koolearn.qa.model;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/21
 */
public class Jira extends BugStatistics{
    //项目id
    private int project_id;
    //项目键值
    private String pKey;
    //版本名称
    private String version;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getpKey() {
        return pKey;
    }

    public void setpKey(String pKey) {
        this.pKey = pKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Jira{" +
                "project_id=" + project_id +
                ", pKey='" + pKey + '\'' +
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
