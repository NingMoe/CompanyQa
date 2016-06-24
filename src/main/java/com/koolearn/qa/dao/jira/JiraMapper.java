package com.koolearn.qa.dao.jira;


import com.koolearn.qa.model.Jira;

import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/21
 */
public interface JiraMapper {
    //通过状态统计，适用于进度报告
    Jira statisticsEveryday(Map<String,Object> map);

    //按照严重性统计，适用于缺陷报告
    Jira statisticsBySeriousness(Map<String,Object> map);
}
