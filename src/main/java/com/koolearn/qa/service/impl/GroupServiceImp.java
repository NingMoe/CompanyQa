package com.koolearn.qa.service.impl;

import com.koolearn.qa.dao.platform.GroupMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.Group;
import com.koolearn.qa.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/18
 */
@Service("groupService")
public class GroupServiceImp extends GenericServiceImpl<Group, Integer> implements IGroupService{
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public GenericDao<Group, Integer> getDao() {
        return groupMapper;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupMapper.getAllGroups();
    }

    @Override
    public List<Group> getAllValidGroups() {
        return groupMapper.getAllValidGroups();
    }
}
