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
    public static ProductCategoryDao objMem;
    public static ProductCategoryDao objDB;
    public ProductCategory mobile;

    public ProductCategoryDaoTestParam(ProductCategoryDaoMem objMem, ProductCategoryDaoJdbc objDB){
        this.objMem = objMem;
        this.objDB = objDB;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData(){
        ProductCategoryDao[][] data = new ProductCategoryDao[][]{
                {objMem = ProductCategoryDaoMem.getInstance()},
                //{objDB = ProductCategoryDaoJdbc.getInstance()}
        };
        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        objMem.add(mobile);
        objDB.add(mobile);

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void find() throws Exception {
        System.out.println(objDB);
        assertEquals(mobile.getId(),objMem.find(1).getId());
        assertEquals(mobile.getId(), objDB.find(1).getId());
    }

    @Test
    public void remove() throws Exception {
        objMem.remove(1);
        objDB.remove(1);
        assertEquals(0, objMem.getAll().size());
        assertEquals(0, objDB.getAll().size());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(1,objMem.getAll().size());
        assertEquals(1,objDB.getAll().size());
    }

}