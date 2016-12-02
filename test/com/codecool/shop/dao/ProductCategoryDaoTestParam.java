package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Created by mz on 2016.12.01..
 */

@RunWith(Parameterized.class)
public class ProductCategoryDaoTestParam {

    private ProductCategoryDao productCategoryDao;
    public ProductCategory mobile;
    private static Integer n = 0;

    public ProductCategoryDaoTestParam(ProductCategoryDao productCategoryDao){
        this.productCategoryDao = productCategoryDao;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData(){
        return Arrays.asList(
                new Object[]{ProductCategoryDaoMem.getInstance()},
                new Object[]{ProductCategoryDaoJdbc.getInstance()});
        };


    @Before
    public void setUp() throws Exception {
        n++;
        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        productCategoryDao.add(mobile);

    }

    @After
    public void tearDown() throws Exception {
        productCategoryDao.getAll().clear();
    }


    @Test
    public void find() throws Exception {
        assertEquals(mobile.getId(),productCategoryDao.find(1).getId());
    }

    @Test
    public void remove() throws Exception {
        assertEquals(0, productCategoryDao.getAll().size());;
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(1,productCategoryDao.getAll().size());

    }

}