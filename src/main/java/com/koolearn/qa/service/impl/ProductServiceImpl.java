package com.koolearn.qa.service.impl;


import com.koolearn.qa.dao.platform.ProductMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.Product;
import com.koolearn.qa.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description   系统service实现类
 * @date 2016/4/21
 */

@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product,Integer> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public GenericDao<Product, Integer> getDao() {
        return productMapper;
    }

    /**
     * 插入记录，包含所有字段
     * @param product
     * @return
     */
    @Override
    public int saveProduct(Product product) {
        if (product == null
                || product.getGroup() == 0 ) {
            return 0;//// TODO:  添加异常处理
        }
        product.setCreatime(new Date());
        return productMapper.insertProduct(product);
    }

    /**
     * 更新系统信息，封装了所有字段
     * @param product
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }

    /**
     * 按照查询条件查询记录，查询条件为空时查询所有记录
     * @param map
     * @return
     */
    @Override
    public List<Product> getProductsByConditions(Map<String, Object> map) {
        if(map==null){
            return productMapper.selectAll();
        }else{
                return productMapper.selectBySelective(map);
            }
    }

    /**
     * 查询所有记录
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectAll();
    }

    /**
     * 查询所有有效记录
     * @return
     */
    public List<Product> getAllEnabledProducts(){
            return productMapper.selectAll();
    }

    @Override
    public Map<Integer, String> getProductMap() {
        List<Product> list = this.getAllEnabledProducts();
        Map<Integer,String> map = new HashMap<>();
        for (Product product:list) {
            map.put(product.getId(),product.getName());
        }
        return map;
    }


}
