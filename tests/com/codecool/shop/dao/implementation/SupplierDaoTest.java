package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    private SupplierDao supplierDao = SupplierDaoMem.getInstance();


    @BeforeEach
    void removeAll() {
        supplierDao.removeAll();
    }

    @Test
    void removeAllSupplierTest() {
        supplierDao.add(new Supplier("test", "test"));
        supplierDao.add(new Supplier("test", "test"));
        supplierDao.add(new Supplier("test", "test"));

        supplierDao.removeAll();

        assertEquals(supplierDao.getAll().size(), 0);
    }

    @Test
    void addProductCategoryTest() {
        Supplier supplier = new Supplier("test", "test");

        int sizeBeforeAdd = supplierDao.getAll().size();
        supplierDao.add(supplier);
        int sizeAfterAdd = supplierDao.getAll().size();

        assertEquals(sizeBeforeAdd, sizeAfterAdd-1);
        assertEquals(supplierDao.getAll().get(sizeAfterAdd - 1), supplier);
    }

    @Test
    void findProductCategoryTest() {

        assertNull(supplierDao.find(1));

        supplierDao.add(new Supplier("test", "test"));

        assertNotNull(supplierDao.find(1));
        assertEquals(supplierDao.find(1).getId(), 1);

    }

    @Test
    void removeProductCategoryTest() {
        supplierDao.add(new Supplier("test", "test"));
        supplierDao.add(new Supplier("test", "test"));
        supplierDao.add(new Supplier("test", "test"));

        int sizeBeforeRemove = supplierDao.getAll().size();
        supplierDao.remove(1);
        supplierDao.remove(100);
        int sizeAfterRemove = supplierDao.getAll().size();

        assertEquals(sizeAfterRemove, sizeBeforeRemove - 1);
        assertFalse(supplierDao.getAll().contains(supplierDao.find(1)));

        assertTrue(supplierDao.getAll().contains(supplierDao.find(2)));
        assertTrue(supplierDao.getAll().contains(supplierDao.find(3)));

    }

}