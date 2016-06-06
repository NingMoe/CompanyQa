package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.BugReport;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface BugReportMapper extends GenericDao<BugReport,Integer> {
    /**
     * 插入对象
     *
     * @param bugReport 对象
     */
    int insertSelective(BugReport bugReport);

    /**
     * 更新对象
     *
     * @param bugReport 对象
     */
    int updateByPrimaryKeySelective(BugReport bugReport);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    BugReport selectByPrimaryKey(Integer id);

    /**
     *按照项目id查询，与项目是一对一
     * @return
     */
    BugReport selectByProjectId(Integer projectId);

    /**
     * 查询某一项目的测试报告记录数（只统计有效记录）
     * @param projectId
     * @return
     */
    int selectCountByProjectId(Integer projectId);
}
