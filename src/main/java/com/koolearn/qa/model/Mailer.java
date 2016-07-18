package com.koolearn.qa.model;

/**
 * @author lihuiyan
 * @description
 * @date 2016/7/14
 */
public class Mailer {
    /**主键*/
    private Integer id;
    /**项目id*/
    private Integer projectId;
    /**收件人*/
    private String recipients;
    /**抄送人员*/
    private String cc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Mailer{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", recipients='" + recipients + '\'' +
                ", cc='" + cc + '\'' +
                '}';
    }
}
