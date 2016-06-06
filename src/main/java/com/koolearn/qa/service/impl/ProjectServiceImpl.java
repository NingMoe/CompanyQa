package com.koolearn.qa.service.impl;

import com.koolearn.qa.dao.platform.ProjectMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.Project;
import com.koolearn.qa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Service("projectService")
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public GenericDao<Project, Integer> getDao() {
        return projectMapper;
    }

    /**
     * 根据条件检索，条件为空则查询所有
     * @param map
     * @return
     */
    @Override
    public List<Project> getProjectsByConditions(Map<String, Object> map) {
        if(map == null || map.size() == 0){
            return projectMapper.selectAllValid();
        }else{
            return projectMapper.selectByConditions(map);
        }
    }

    @Override
    public List<Project> getProjectsByProductId(Integer productId) {
        if(productId == null){
            return  null;
        }
        return projectMapper.selectByProductId(productId);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectMapper.selectAll();
    }

    @Override
    public List<Project> getAllValidProjects() {
        return projectMapper.selectAllValid();
    }

    public int updateByPrimaryKey(Project project){
        return projectMapper.updateByPrimaryKey(project);
    }
}
