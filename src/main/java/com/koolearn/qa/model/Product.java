package com.koolearn.qa.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lihuiyan
 * @description 系统模型
 * @date 2016/4/21
 */
public class Product implements Serializable {

    private int id;

    private String name;

    private int group;

    private String leader;

    private int status;

    private Date creatime;

    private String description;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatime() {
        if(creatime != null){
            return format.format(creatime);
        }
        return null;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", leader=" + leader +
                ", status=" + status +
                ", creatime=" + getCreatime() +
                ", description='" + description + '\'' +
                '}';
    }
}
