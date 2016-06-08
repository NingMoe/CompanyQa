package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.UserRole;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/8
 */
public interface IUserRoleService extends GenericService<UserRole,Integer> {
    /**
     * 新增用户角色
     * @param userRole
     * @return
     */
    int insertUserRole(UserRole userRole);

    /**
     * 更新用户角色
     * @param userRole
     * @return
     */
    int updateUserRole(UserRole userRole);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<UserRole> selectAllUsers();

    /**
     * 判断是否已存在
     * @param userName
     * @return
     */
    boolean checkIsExist(String userName);
}
