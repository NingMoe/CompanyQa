package com.koolean.qa.test.service;

import com.koolearn.qa.model.Product;
import com.koolearn.qa.service.IProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ProductServiceTest {
    @Autowired
    private IProductService productService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getProductByIdTest() {
        Product product = productService.selectById(1);
        System.out.println(product.toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getProductsByConditions(){
        Map<String,Object> map = new HashMap<>();
        map.put("group",1);
        map.put("status","1");
        map.put("leader",1);
        List<Product> list = productService.getProductsByConditions(map);
        for (Product product:list) {
            System.out.println(product.toString());
        }
    }
}
