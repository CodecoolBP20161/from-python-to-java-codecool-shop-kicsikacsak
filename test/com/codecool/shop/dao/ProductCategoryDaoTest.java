package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProductCategoryDaoTest {

    private ProductCategory mobile;
    private ProductCategoryDao productCategoryDataStorem;
    private ProductCategoryDao productCategoryDataStoreDB;

    @Before
    public void setUp() throws Exception {

        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        productCategoryDataStorem = ProductCategoryDaoMem.getInstance();
        productCategoryDataStorem.add(mobile);
        productCategoryDataStoreDB = ProductCategoryDaoJdbc.getInstance();
        productCategoryDataStoreDB.add(mobile);

    }

    @Test
    public void find() throws Exception {

        assertEquals(mobile.getId(), productCategoryDataStorem.find(1).getId());
        assertEquals(mobile.getId(), productCategoryDataStoreDB.find(1).getId());

    }

    //try to find a category by wrong id
    @Test
    public void invalidFindId() throws Exception {

        assertNull(productCategoryDataStorem.find(3));
        assertNull(productCategoryDataStoreDB.find(3));
    }


    //try to remove an element by invalid id, the size remains but i think i have to throw an error ??
    @Test
    public void nonexistentRemoveId_ShouldDoNothing() throws Exception {

        productCategoryDataStorem.remove(3);
        productCategoryDataStoreDB.remove(3);
        assertEquals(1, productCategoryDataStorem.getAll().size());
        assertEquals(1, productCategoryDataStoreDB.getAll().size());

    }

    @Test
    public void remove() throws Exception {

        productCategoryDataStorem.remove(1);
        productCategoryDataStoreDB.remove(1);
        assertEquals(0, productCategoryDataStorem.getAll().size());
        assertEquals(0, productCategoryDataStoreDB.getAll().size());

    }

    @Test
    public void getAll() throws Exception {

        assertEquals(1, productCategoryDataStorem.getAll().size());
        assertEquals(1, productCategoryDataStoreDB.getAll().size());

    }

    @After
    public void tearDown() throws Exception {

        productCategoryDataStorem.remove(1);
        productCategoryDataStoreDB.remove(1);

    }
}