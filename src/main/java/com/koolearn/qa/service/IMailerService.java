package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.Mailer;
import com.koolearn.qa.model.Project;

import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/7/14
 */
public interface IMailerService extends GenericService<Mailer,Integer> {
    /**
     * 按照项目查询测试计划记录
     * @return
     */
    Mailer getMailerByProjectId(Integer projectId);

    /**
     * 判断是否已存在记录
     * @param projectId
     * @return
     */
    Boolean isExist(Integer projectId);

    /**
     * 未设置邮件接收人，获取默认接收人邮箱
     * @param project
     * @return
     */
    Map<String, List<String>> getDefaultMail(Project project);

    /**
     * 未设置邮件接收人，获取默认接收人姓名
     * @param project
     * @return
     */
    Map<String, String> getDefaultName(Project project);

    /**
     * 把人名转换为邮箱
     * @param str
     * @return
     */
    List<String> transEmail(String str);

    }
