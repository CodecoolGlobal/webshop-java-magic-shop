package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoDB implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoDB instance = null;
    private SupplierDao supplierDao = SupplierDaoDB.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoDB.getInstance();

    private DBconfig dBconfig = DBconfig.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoDB() {
    }

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO product (name,defaultprice,currencystring,productcategory,supplier,description) values (?, ?, ?, ?, ? ,?)")
        ) {
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getDefaultPrice());
            stmt.setString(3, product.getDefaultCurrency().toString());
            stmt.setString(4, product.getProductCategory().toString());
            stmt.setString(5, product.getSupplier().toString());
            stmt.setString(6, product.getDescription());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public Product find(int id) {
        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select product.*, productcategory.id as categoryid, supplier.id as supplierid" +
                                " from product join productcategory on product.productcategory = productcategory.name " +
                                "join supplier on product.supplier = supplier.name" +
                                " where product.id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            return new Product(resultSet.getString("name"),
                    resultSet.getFloat("defaultprice"),
                    resultSet.getString("currencystring"),
                    resultSet.getString("description"),
                    productCategoryDao.find(resultSet.getInt("categoryid")),
                    supplierDao.find(resultSet.getInt("supplierid")));


        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM product WHERE id = (?)")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> resultList = new ArrayList<>();

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                resultList.add(product);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> resultList = new ArrayList<>();

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select product.*, productcategory.id as categoryid, supplier.id as supplierid" +
                        " from product join productcategory on product.productcategory = productcategory.name " +
                        "join supplier on product.supplier = supplier.name" +
                        " where supplier.id = (?)")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                resultList.add(product);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> resultList = new ArrayList<>();

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select product.*, productcategory.id as categoryid, supplier.id as supplierid" +
                        " from product join productcategory on product.productcategory = productcategory.name " +
                        "join supplier on product.supplier = supplier.name" +
                        " where productcategory.id = (?)")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                resultList.add(product);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getString("name"),
                resultSet.getFloat("defaultprice"),
                resultSet.getString("currencystring"),
                resultSet.getString("description"),
                productCategoryDao.find(resultSet.getInt("categoryid")),
                supplierDao.find(resultSet.getInt("supplierid")));
    }
}
