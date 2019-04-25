package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.jdbc.DBconfig;
import com.codecool.shop.dao.implementation.jdbc.SupplierDaoDB;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    private SupplierDao supplierDao = SupplierDaoDB.getInstance();

    @BeforeAll
    static void dbinit() {
        DBconfig dBconfig = DBconfig.getInstance();

        dBconfig.setupConfig("TESTconfigDB.cfg");
    }


    @BeforeEach
    void removeAll() {
        supplierDao.removeAll();
    }

    @Test
    void removeAllSupplierTest() {
        supplierDao.add(new Supplier(1,"test1", "test"));
        supplierDao.add(new Supplier(2,"test2", "test"));
        supplierDao.add(new Supplier(3,"test3", "test"));

        supplierDao.removeAll();

        assertEquals(supplierDao.getAll().size(), 0);
    }

    @Test
    void addSupplierTest() {
        Supplier supplier = new Supplier(1,"test4", "test");

        int sizeBeforeAdd = supplierDao.getAll().size();
        supplierDao.add(supplier);
        int sizeAfterAdd = supplierDao.getAll().size();

        assertEquals(sizeBeforeAdd, sizeAfterAdd-1);
//        assertEquals(supplierDao.getAll().get(sizeAfterAdd - 1), supplier);
    }

    @Test
    void findSupplierTest() {

        assertNull(supplierDao.find(1));

        supplierDao.add(new Supplier(1,"test5", "test"));

        assertNotNull(supplierDao.find(1));
        assertEquals(supplierDao.find(1).getId(), 1);

    }

    @Test
    void removeSupplierTest() {
        supplierDao.add(new Supplier(1,"test6", "test"));
        supplierDao.add(new Supplier(2,"test7", "test"));
        supplierDao.add(new Supplier(3,"test8", "test"));

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