package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();


    @BeforeEach
    void removeAll() {
        productCategoryDao.removeAll();
    }

    @Test
    void removeAllProductCategoryTest() {
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));

        productCategoryDao.removeAll();

        assertEquals(productCategoryDao.getAll().size(), 0);
    }

    @Test
    void addProductCategoryTest() {
        ProductCategory category = new ProductCategory("test", "testDepo", "This is a test category");

        int sizeBeforeAdd = productCategoryDao.getAll().size();
        productCategoryDao.add(category);
        int sizeAfterAdd = productCategoryDao.getAll().size();

        assertEquals(sizeBeforeAdd, sizeAfterAdd-1);
        assertEquals(productCategoryDao.getAll().get(sizeAfterAdd - 1), category);
    }

    @Test
    void findProductCategoryTest() {

        assertNull(productCategoryDao.find(1));

        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));

        assertNotNull(productCategoryDao.find(1));
        assertEquals(productCategoryDao.find(1).getId(), 1);

    }

    @Test
    void removeProductCategoryTest() {
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));
        productCategoryDao.add(new ProductCategory("test", "testDepo", "This is a test category"));

        int sizeBeforeRemove = productCategoryDao.getAll().size();
        productCategoryDao.remove(1);
        productCategoryDao.remove(100);
        int sizeAfterRemove = productCategoryDao.getAll().size();

        assertEquals(sizeAfterRemove, sizeBeforeRemove - 1);
        assertFalse(productCategoryDao.getAll().contains(productCategoryDao.find(1)));

        assertTrue(productCategoryDao.getAll().contains(productCategoryDao.find(2)));
        assertTrue(productCategoryDao.getAll().contains(productCategoryDao.find(3)));

    }

}