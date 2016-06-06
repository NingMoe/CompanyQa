package com.koolearn.qa.service;

import com.koolearn.qa.model.Role;

import java.util.List;

/**
 * 角色 业务接口
 * 
 **/
public interface IRoleService {
    /**
     * 通过用户名 查询用户 拥有的角色
     * 
     * @param userName
     * @return
     */
    List<Role> selectRolesByUserName(String userName);
}
