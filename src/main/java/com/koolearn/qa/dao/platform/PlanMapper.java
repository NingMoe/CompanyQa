package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Plan;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface PlanMapper extends GenericDao<Plan,Integer> {

    /**
     * 插入对象
     *
     * @param plan 对象
     */
    int insertSelective(Plan plan);

    /**
     * 更新对象
     *
     * @param plan 对象
     */
    int updateByPrimaryKeySelective(Plan plan);

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
    Plan selectByPrimaryKey(Integer id);

    /**
     * 按照项目id查询，与项目是一对一
     * @param projectId
     * @return
     */
    Plan selectByProjectId(Integer projectId);
}
