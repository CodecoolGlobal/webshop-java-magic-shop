package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {

    private static SupplierDaoDB instance = null;
    private DBconfig dBconfig = DBconfig.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoDB() {
    }

    public static SupplierDaoDB getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO supplier (name,description) values (?, ?)")
        ) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM supplier WHERE id = (?)")
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return getSupplier(resultSet);
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
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM supplier WHERE id = (?)")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> resultList = new ArrayList<>();

        try (
                Connection conn = dBconfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM supplier")
        ) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = getSupplier(resultSet);
                resultList.add(supplier);
            }
        } catch (Exception e) {
            System.out.println("sqlerror" + e);
        }
        return resultList;
    }

    private Supplier getSupplier(ResultSet resultSet) throws SQLException {
        return new Supplier(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"));
    }
}
