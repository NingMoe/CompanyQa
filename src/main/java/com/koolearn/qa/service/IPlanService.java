package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.Plan;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface IPlanService extends GenericService<Plan,Integer> {
    /**
     * 按照项目查询测试计划记录
     * @return
     */
    Plan getPlanByProjectId(Integer projectId);
}
