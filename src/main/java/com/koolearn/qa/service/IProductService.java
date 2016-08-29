package com.koolearn.qa.service;

import com.koolearn.qa.generic.GenericService;
import com.koolearn.qa.model.Product;

import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description   系统业务接口
 * @date 2016/4/21
 */

public interface IProductService extends GenericService<Product,Integer> {

    /**
     * 插入记录，插入所有字段
     * @param product
     * @return
     */
     int saveProduct(Product product);


    /**
     * 更新记录，更新所有字段
     * @param product
     */
    int updateProduct(Product product);


    /**
     * 按照条件检索
     * @param map
     * @return
     */
     List<Product> getProductsByConditions(Map<String, Object> map);


    /**
     * 查询所有记录
     * @return
     */
    List<Product> getAllProducts();

    /**
     * 查询所有有效记录
     * @return
     */
    List<Product> getAllEnabledProducts();

    /**
     * 获取id和name
     * @return
     */
    Map<Integer,String> getProductMap();
}
