package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.Group;

import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/18
 */
public interface IGroupService  extends GenericService<Group,Integer> {
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

    int recovery(Integer id);

}
