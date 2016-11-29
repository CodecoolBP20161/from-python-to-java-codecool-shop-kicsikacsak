package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProductCategoryDaoTest {

    private ProductCategory mobile;
    private ProductCategoryDao productCategoryDataStore;

    @Before
    public void setUp() throws Exception {

        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        productCategoryDataStore.add(mobile);
    }

    @Test
    public void find() throws Exception {

        assertEquals(mobile.getId(), productCategoryDataStore.find(1).getId());

    }

    @Test(expected = NullPointerException.class)
    public void invalidFindId() throws Exception {

        assertEquals(mobile.getId(), productCategoryDataStore.find(3).getId());
    }


    @Test
    public void invalidRemoveId() throws Exception {
        productCategoryDataStore.remove(3);
        assertEquals(1, productCategoryDataStore.getAll().size());

    }

    @Test
    public void remove() throws Exception {

        productCategoryDataStore.remove(1);
        assertEquals(0, productCategoryDataStore.getAll().size());

    }

    @Test
    public void getAll() throws Exception {

        assertEquals(1, productCategoryDataStore.getAll().size());

    }

    @After
    public void tearDown() throws Exception {

        mobile = null;
        productCategoryDataStore.remove(1);

    }
}