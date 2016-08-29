package com.koolearn.qa.dao.platform;

import com.koolearn.qa.model.Permission;

import java.util.List;

/**
 * 权限 Dao 接口
 *
 **/
public interface PermissionMapper {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roleId);

    /**
     * 通过角色标识查询角色拥有的权限
     * @param sign
     * @return
     */
    List<Permission>  selectPermissionsBySign(String sign);
}