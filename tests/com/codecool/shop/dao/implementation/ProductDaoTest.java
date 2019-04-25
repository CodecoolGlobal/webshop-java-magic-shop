//package com.codecool.shop.dao.implementation;
//
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.model.Product;
//import com.codecool.shop.model.ProductCategory;
//import com.codecool.shop.model.Supplier;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProductDaoTest {
//
//    private ProductDao productDao = ProductDaoMem.getInstance();
//    private ProductCategory category = new ProductCategory("test", "testDepo", "This is a test category");
//    private Supplier supplier = new Supplier(1,"Test","Test");
//
//    @BeforeEach
//    void removeAll() {
//        productDao.removeAll();
//    }
//
//    @Test
//    void addProductTest() {
//        Product product = new Product("Test",0.0f, "GBP", "Test", category, supplier);
//
//        int sizeBeforeAdd = productDao.getAll().size();
//        productDao.add(product);
//        int sizeAfterAdd = productDao.getAll().size();
//
//        assertEquals(sizeBeforeAdd, sizeAfterAdd-1);
//        assertEquals(productDao.getAll().get(sizeAfterAdd - 1), product);
//    }
//
//    @Test
//    void findProductTest() {
//        assertNull(productDao.find(1));
//
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//
//        assertNotNull(productDao.find(1));
//        assertEquals(productDao.find(1).getId(), 1);
//    }
//
//    @Test
//    void removeProductTest() {
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//
//        int sizeBeforeRemove = productDao.getAll().size();
//        productDao.remove(1);
//        productDao.remove(100);
//        int sizeAfterRemove = productDao.getAll().size();
//
//        assertEquals(sizeAfterRemove, sizeBeforeRemove - 1);
//        assertFalse(productDao.getAll().contains(productDao.find(1)));
//
//        assertTrue(productDao.getAll().contains(productDao.find(2)));
//        assertTrue(productDao.getAll().contains(productDao.find(3)));
//    }
//
//    @Test
//    void getAll() {
//    }
//
//    @Test
//    void getBySupplierTest() {
//
//        Supplier anotherSupplier = new Supplier(4,"Test","Test");
//        int sumOfProducts = 0;
//
//
//        productDao.add(new Product(1,"Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product(2,"Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product(3,"Test",0.0f, "GBP", "Test", category, supplier));
//
//        productDao.add(new Product(4,"Test",0.0f, "GBP", "Test", category, anotherSupplier));
//        productDao.add(new Product(5,"Test",0.0f, "GBP", "Test", category, anotherSupplier));
//        productDao.add(new Product(6,"Test",0.0f, "GBP", "Test", category, anotherSupplier));
//
//        for (Product product:productDao.getBy(supplier)) {
//            if (product.getSupplier().equals(supplier)) {
//                sumOfProducts += 1;
//            }
//            else {
//                fail();
//            }
//        }
//        assertEquals(sumOfProducts,productDao.getBy(supplier).size());
//
//
//
//
//    }
//
//    @Test
//    void getByCategoryTest() {
//        ProductCategory anotherCategory = new ProductCategory("Test","Test","Test");
//        int sumOfProducts = 0;
//
//
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", category, supplier));
//
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", anotherCategory, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", anotherCategory, supplier));
//        productDao.add(new Product("Test",0.0f, "GBP", "Test", anotherCategory, supplier));
//
//        for (Product product:productDao.getBy(category)) {
//            if (product.getProductCategory().equals(category)) {
//                sumOfProducts += 1;
//            }
//            else {
//                fail();
//            }
//        }
//        assertEquals(sumOfProducts,productDao.getBy(category).size());
//    }
//}