package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;

/**
 * Created by doramedgyasszay on 2016. 12. 01..
 */
public class DataStoreSwitcher {

    private DataStoreSwitcher() {
    }

    private static DataStoreSwitcher instance = null;

    public static synchronized DataStoreSwitcher getInstance() {
        if (instance == null) {
            instance = new DataStoreSwitcher();
        }
        return instance;
    }

     public enum DataStore {
        DATABASE,
        MEMORY
    }

    public static DataStore dataStoreType = DataStore.MEMORY;

    public static ProductDao getProductDao() {
        ProductDao product = null;
        switch (dataStoreType) {
            case DATABASE:
                product = ProductDaoJdbc.getInstance();
                break;
            case MEMORY:
                product = ProductDaoMem.getInstance();
                break;
            default:
                break;
        }
        return product;
    }

    public static ProductCategoryDao getProductCategoryDao() {
        ProductCategoryDao productCategory = null;
        switch (dataStoreType) {
            case DATABASE:
                productCategory = ProductCategoryDaoJdbc.getInstance();
                break;
            case MEMORY:
                productCategory = ProductCategoryDaoMem.getInstance();
                break;
            default:
                break;
        }
        return productCategory;
    }

        public static SupplierDao getSupplierDao() {
        SupplierDao supplier = null;
        switch (dataStoreType) {
            case DATABASE:
                supplier = SupplierDaoJdbc.getInstance();
                break;
            case MEMORY:
                supplier = SupplierDaoMem.getInstance();
                break;
            default:
                break;
        }
        return supplier;
    }

}
