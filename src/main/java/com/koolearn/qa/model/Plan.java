package com.koolearn.qa.model;

import java.util.Date;

/**
 * @author lihuiyan
 * @description 测试计划模型
 * @date 2016/4/21
 */
public class Plan {
    //主键
    private int id;
    //项目id
    private int projectId;
    //上传文件路径
    private String filePath;
    //描述信息
    private String fileName;
    //状态
    private int status;
    //创建时间
    private Date createTime;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
