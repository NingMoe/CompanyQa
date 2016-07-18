package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Mailer;
/**
 * @author lihuiyan
 * @description
 * @date 2016/7/14
 */
public interface MailerMapper extends GenericDao<Mailer,Integer> {
    /**
     * 插入对象
     *
     * @param mailer 对象
     */
    int insertSelective(Mailer mailer);

    /**
     * 更新对象
     *
     * @param mailer 对象
     */
    int updateByPrimaryKeySelective(Mailer mailer);

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    Mailer selectByPrimaryKey(Integer id);

    /**
     * 按照项目id查询，与项目是一对一
     * @param projectId
     * @return
     */
    Mailer selectByProjectId(Integer projectId);
}
