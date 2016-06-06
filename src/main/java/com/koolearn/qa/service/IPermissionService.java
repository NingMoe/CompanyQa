package com.koolearn.qa.service;


import com.koolearn.qa.model.Permission;

import java.util.List;

/**
 * 权限 业务接口
 * 
 **/
public interface IPermissionService {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roleId);

    /**
     * 通过角色标识 查询角色 拥有的权限
     *
     * @param sign
     * @return
     */
    List<Permission> selectPermissionsByRoleSign(String sign);

}
