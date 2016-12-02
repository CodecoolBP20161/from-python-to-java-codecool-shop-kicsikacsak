package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SupplierDaoTest {

    private Supplier nokia;
    private SupplierDaoMem productSupplierDao;


    @Before
    public void setUp() throws Exception {

        nokia = new Supplier("Nokia", "Electronic stuff");
        productSupplierDao = SupplierDaoMem.getInstance();
        productSupplierDao.add(nokia);

    }

    @Test
    public void find() throws Exception {

        assertEquals(nokia.getId(), productSupplierDao.find(1).getId());

    }

    @Test(expected = NullPointerException.class)
    public void invalidFindId() throws Exception {

        assertEquals(nokia.getId(), productSupplierDao.find(3).getId());
    }


    @Test
    public void invalidRemoveId() throws Exception {
        productSupplierDao.remove(3);
        assertEquals(1, productSupplierDao.getAll().size());

    }


    @Test
    public void remove() throws Exception {

        productSupplierDao.remove(1);
        assertEquals(0, productSupplierDao.getAll().size());

    }

    @Test
    public void getAll() throws Exception {

        assertEquals(1, productSupplierDao.getAll().size());

    }

    @After
    public void tearDown() throws Exception {

        productSupplierDao.remove(1);

    }


}