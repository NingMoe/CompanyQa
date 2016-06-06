package java.com.koolean.qa.dao;

import com.koolearn.qa.dao.platform.ProductMapper;
import com.koolearn.qa.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:applicationContext.xml"})
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void selectByPrimaryKeyTest(){
        Product p = productMapper.selectByPrimaryKey(1);
        System.out.println(p.toString());
    }
}
