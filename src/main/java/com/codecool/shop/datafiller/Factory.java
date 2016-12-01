package com.codecool.shop.datafiller;

import com.codecool.shop.controller.DataStoreSwitcher;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

/**
 * Created by doramedgyasszay on 2016. 11. 16..
 */

//This class is responsible for instantiation
public class Factory {

    private static Factory instance = null;

    private static DataStoreSwitcher switcher = new DataStoreSwitcher();

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public void newProduct(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {

        ProductDao productDataStore = switcher.getProductDao();
        productDataStore.add(new Product(name, defaultPrice, currencyString, description, productCategory, supplier));
    }

    public ProductCategory productCategory(String name, String department, String description) {
        ProductCategoryDao productCategoryDataStore = switcher.getProductCategoryDao();
        ProductCategory category = new ProductCategory(name, department, description);
        productCategoryDataStore.add(category);
        return category;
    }

    public Supplier newSupplier(String name, String description) {
        SupplierDao supplierDataStore = switcher.getSupplierDao();
        Supplier supplier = new Supplier(name, description);
        supplierDataStore.add(supplier);
        return supplier;
    }
}
