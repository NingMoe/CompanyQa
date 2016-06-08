package com.koolearn.qa.service.impl;

import com.koolearn.qa.dao.platform.UserRoleMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.UserRole;
import com.koolearn.qa.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/8
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole, Integer> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public GenericDao<UserRole, Integer> getDao() {
        return userRoleMapper;
    }

    @Override
    public int insertUserRole(UserRole userRole) {
        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public int updateUserRole(UserRole userRole) {
        return userRoleMapper.updateUserRole(userRole);
    }

    @Override
    public List<UserRole> selectAllUsers() {
        return userRoleMapper.selectAllUsers();
    }

    @Override
    public boolean checkIsExist(String userName) {
        List<UserRole> list = userRoleMapper.selectByName(userName);
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }
}
