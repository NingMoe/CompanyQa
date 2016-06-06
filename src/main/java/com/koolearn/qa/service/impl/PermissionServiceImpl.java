package com.koolearn.qa.service.impl;


import com.koolearn.qa.dao.platform.PermissionMapper;
import com.koolearn.qa.model.Permission;
import com.koolearn.qa.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限Service实现类
 *
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> selectPermissionsByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }

    @Override
    public List<Permission> selectPermissionsByRoleSign(String sign) {
        return permissionMapper.selectPermissionsBySign(sign);
    }
}
