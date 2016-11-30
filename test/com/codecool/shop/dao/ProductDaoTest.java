package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by svindler on 28.11.2016.
 */
public class ProductDaoTest {

    private Supplier nokia;
    private ProductCategory mobile;
    private Product nokia705;
    private ProductDaoMem productDao;

    @Before
    public void setUp() throws Exception {

        nokia = new Supplier("Nokia", "Electronic stuff");
        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        nokia705 = new Product("Amazon Fire", 49, "USD",
                "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.",
                mobile, nokia);

        productDao = ProductDaoMem.getInstance();
        productDao.add(nokia705);



    }

    @Test
    public void getAll() throws Exception {

        assertEquals(1, productDao.getAll().size());

    }

    //try to add null value to our list
    @Test(expected=NullPointerException.class)
    public void testNullException() throws Exception {

        productDao.add(null);

    }


    @Test
    public void getByCategory() throws Exception {

        assertEquals(1, productDao.getBy(mobile).size());

    }

    //try to add to null category i don't know where to catch this exception
    @Test
    public void getByWrongCategory() throws Exception {

        ProductCategory nullCategory = null;
        assertEquals(0, productDao.getBy(nullCategory).size());

    }


    @Test
    public void remove() throws Exception {

        productDao.remove(1);
        assertEquals(0, productDao.getAll().size());

    }


    @Test
    public void find() throws Exception {

        assertEquals(nokia705.getName(), productDao.find(1).getName());

    }


    @Test
    public void getBySupplier() throws Exception {

        assertEquals(1, productDao.getBy(nokia).size());

    }

    @After
    public void tearDown() throws Exception {

        productDao.remove(1);

    }

}