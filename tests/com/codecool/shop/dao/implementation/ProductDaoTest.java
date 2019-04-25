package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategory category = new ProductCategory("test", "testDepo", "This is a test category");
    private Supplier supplier = new Supplier("Test","Test");

    @BeforeEach
    void removeAll() {
        productDao.removeAll();
    }

    @Test
    void addProductTest() {
        Product product = new Product("Test",0.0f, "GBP", "Test", category, supplier);

        int sizeBeforeAdd = productDao.getAll().size();
        productDao.add(product);
        int sizeAfterAdd = productDao.getAll().size();

        assertEquals(sizeBeforeAdd, sizeAfterAdd-1);
        assertEquals(productDao.getAll().get(sizeAfterAdd - 1), product);
    }

    @Test
    void findProductTest() {
        assertNull(productDao.find(1));

        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));

        assertNotNull(productDao.find(1));
        assertEquals(productDao.find(1).getId(), 1);
    }

    @Test
    void removeProductTest() {
        new Product("Test",0.0f, "GBP", "Test", category, supplier);
        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));

        int sizeBeforeRemove = productDao.getAll().size();
        productDao.remove(1);
        productDao.remove(100);
        int sizeAfterRemove = productDao.getAll().size();

        assertEquals(sizeAfterRemove, sizeBeforeRemove - 1);
        assertFalse(productDao.getAll().contains(productDao.find(1)));

        assertTrue(productDao.getAll().contains(productDao.find(2)));
        assertTrue(productDao.getAll().contains(productDao.find(3)));
    }

    @Test
    void getAll() {
    }

    @Test
    void getBy() {

    }

    @Test
    void getBy1() {
    }
}