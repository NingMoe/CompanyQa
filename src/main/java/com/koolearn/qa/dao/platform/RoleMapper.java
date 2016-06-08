package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Role;

import java.util.List;

/**
 * 角色Dao 接口
 * 
 **/
public interface RoleMapper {

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userName
     * @return
     */
    List<Role> selectRolesByUserName(String userName);

    /**
     * 查询所有记录
     * @return
     */
    List<Role> selectAllRoles();
}