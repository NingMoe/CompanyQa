package com.koolearn.qa.service.impl;

import com.koolearn.qa.dao.platform.PlanMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.Plan;
import com.koolearn.qa.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Service("planService")
public class PlanServiceImpl extends GenericServiceImpl<Plan, Integer> implements IPlanService {
    @Autowired
    private PlanMapper planMapper;

    @Override
    public GenericDao<Plan, Integer> getDao() {
        return planMapper;
    }

    @Override
    public Plan getPlanByProjectId(Integer projectId) {
        return planMapper.selectByProjectId(projectId);
    }
}
