package com.koolearn.qa.service.impl;

import java.util.List;
import com.koolearn.qa.dao.platform.RoleMapper;
import com.koolearn.qa.model.Role;
import com.koolearn.qa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色Service实现类
 *
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectRolesByUserName(String userName) {
        return roleMapper.selectRolesByUserName(userName);
    }

}
