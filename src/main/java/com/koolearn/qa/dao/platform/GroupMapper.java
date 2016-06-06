package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Group;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/18
 */
public interface GroupMapper extends GenericDao<Group,Integer> {
    /**
     * 插入对象
     *
     * @param bugReport 对象
     */
    int insertSelective(Group bugReport);

    /**
     * 更新对象
     *
     * @param bugReport 对象
     */
    int updateByPrimaryKeySelective(Group bugReport);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    Group selectByPrimaryKey(Integer id);

    /**
     * 查询所有分组
     * @return
     */
    List<Group> getAllGroups();

    /**
     * 查询所有有效分组
     * @return
     */
    List<Group> getAllValidGroups();
}
