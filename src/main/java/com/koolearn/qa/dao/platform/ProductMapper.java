package com.koolearn.qa.dao.platform;


import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.model.Product;

import java.util.List;
import java.util.Map;


/**
 * @author lihuiyan
 * @description  系统DAO接口
 * @date 2016/4/21
 */

public interface ProductMapper extends GenericDao<Product, Integer> {

    int insertSelective(Product product);

    int insertProduct(Product product);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(Integer id);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectBySelective(Map<String,Object> map);

    List<Product> selectAll();

    List<Product> selectAllEnabled();


}
