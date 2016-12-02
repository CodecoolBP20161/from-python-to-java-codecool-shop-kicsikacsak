package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mz on 2016.12.01..
 */
public class ProductDaoJdbcTest {
    private Supplier nokia;
    private SupplierDao supplierDao;
    private ProductCategoryDao categoryDao;
    private ProductCategory mobile;
    private Product nokia705;
    private ProductDaoJdbc productDao;

    private static Integer n = 0;


    @Before
    public void setUp() throws Exception {
        n ++;
        nokia = new Supplier("Nokia", "Electronic stuff");
        mobile = new ProductCategory("Nokia", "Electronic stuff", "Boring stuff for testing");
        nokia705 = new Product("Amazon Fire", 49, "USD",
                "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.",
                mobile, nokia);

        productDao = ProductDaoJdbc.getInstance();
        categoryDao = ProductCategoryDaoJdbc.getInstance();
        supplierDao = SupplierDaoJdbc.getInstance();

        categoryDao.add(mobile);
        supplierDao.add(nokia);
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
    @Test(expected = NullPointerException.class)
    public void getByWrongCategory() throws Exception {

        ProductCategory nullCategory = null;
        assertNull(productDao.getBy(nullCategory).size());

    }

    @Test
    public void remove() throws Exception {

        productDao.remove(n);
        assertEquals(0, productDao.getAll().size());

    }


    @Test
    public void find() throws Exception {

        assertEquals(nokia705.getName(), productDao.find(n).getName());

    }


    @Test
    public void getBySupplier() throws Exception {

        assertEquals(1, productDao.getBy(nokia).size());

    }

    @After
    public void tearDown() throws Exception {

        productDao.remove(n);
        supplierDao.remove(n);
        categoryDao.remove(n);

    }


}