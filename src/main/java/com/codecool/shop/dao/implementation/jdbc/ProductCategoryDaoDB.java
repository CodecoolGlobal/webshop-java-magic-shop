package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDB implements ProductCategoryDao {

    private static ProductCategoryDaoDB instance = null;
    private DBconfig dBconfig = DBconfig.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoDB() {
    }

    public static ProductCategoryDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDB();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO productcategory (name,description,department) values (?, ?, ?)")
        ) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setString(3, category.getDepartment());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productcategory WHERE id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new ProductCategory(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
            }

        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM productcategory WHERE id = (?)")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> resultList = new ArrayList<>();

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productcategory")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory =new ProductCategory(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                resultList.add(productCategory);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }
}
