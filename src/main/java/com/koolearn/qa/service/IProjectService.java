package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.Project;

import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface IProjectService extends GenericService<Project, Integer> {
    /**
     * 根据检索条件查询
     *
     * @param map
     * @return
     */
    List<Project> getProjectsByConditions(Map<String, Object> map);

    /**
     * 根据系统id查询
     *
     * @param productId
     * @return
     */
    List<Project> getProjectsByProductId(Integer productId);

    /**
     * 查询所有
     *
     * @return
     */
    List<Project> getAllProjects();

    /**
     * 查询所有有效记录
     *
     * @return
     */
    List<Project> getAllValidProjects();

    /**
     * 更新所有字段
     *
     * @param project
     * @return
     */
    int updateByPrimaryKey(Project project);

}
