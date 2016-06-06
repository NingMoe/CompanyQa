package com.koolearn.qa.dao.platform;

import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Progress;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
public interface ProgressMapper extends GenericDao<Progress,Integer> {
    /**
     * 插入对象
     * @param progress 对象
     */
    int insertSelective(Progress progress);

    /**
     * 更新对象
     * @param progress 对象
     */
    int updateByPrimaryKeySelective(Progress progress);

    /**
     * 通过主键, 删除对象
     * @param id 主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过主键, 查询对象
     * @param id 主键
     * @return
     */
    Progress selectByPrimaryKey(Integer id);

    /**
     * 根据项目id查询记录（按照时间正序排序）
     * @param id
     * @return
     */
    List<Progress> selectByProjectId(Integer id);

    /**
     *
     * @return
     */
    int selectCountByDate(Map map);

}
