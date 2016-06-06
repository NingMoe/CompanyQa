package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Project;

import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface ProjectMapper extends GenericDao<Project,Integer> {
    /**
     * 插入对象
     * @param project 对象
     */
    int insertSelective(Project project);

    /**
     * 更新对象
     * @param project 对象
     */
    int updateByPrimaryKeySelective(Project project);

    /**
     * 更新所有对象
     * @param project
     * @return
     */
    int updateByPrimaryKey(Project project);

    /**
     * 通过主键, 删除对象
     * @param id 主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过主键, 查询对象
     * @param id 主键
     * @return
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * 通过查询条件检索对象
     * @param map
     * @return
     */
    List<Project> selectByConditions(Map<String,Object> map);

    /**
     * 查询所有记录
     * @return
     */
    List<Project> selectAll();

    /**
     * 查询所有有效记录
     * @return
     */
    List<Project> selectAllValid();

    /**
     * 按照系统id查询
     * @param productId
     * @return
     */
    List<Project> selectByProductId(Integer productId);
}
