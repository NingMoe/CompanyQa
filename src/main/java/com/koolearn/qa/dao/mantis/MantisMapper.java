package com.koolearn.qa.dao.mantis;

import com.koolearn.qa.model.Mantis;

import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/28
 */
public interface MantisMapper {
    //通过状态统计，适用于进度报告
    Mantis statisticsEveryday(Map<String,Object> map);

    //按照严重性统计，适用于缺陷报告
    Mantis statisticsBySeriousness(Map<String,Object> map);
}
