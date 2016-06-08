package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.UserRole;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/8
 */
public interface UserRoleMapper extends GenericDao<UserRole, Integer> {

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
     * 通过主键删除
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return
     */
    UserRole selectByPrimaryKey(Integer id);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<UserRole> selectAllUsers();

    /**
     * 通过用户名查询记录
     * @param userName
     * @return
     */
    List<UserRole> selectByName(String userName);
}
