package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository
{
    @Autowired
    private DataSource dataSource;

    public List<Product> getProducts()
    {
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, price, description, category, image FROM product")) {

            while (rs.next()){
                products.add(new Product(rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getInt("price"),
                                        rs.getString("description"),
                                        rs.getString("category"),
                                        rs.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProduct(Integer id)
    {
        Product foundProduct = null;
        //test
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, price, description, category, image FROM product WHERE id = " + id)) {

            if (rs.next()){
                foundProduct = (new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundProduct;
    }

    public Product getProductByName(String name)
    {
        Product foundProduct = null;
        //test
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, price, description, category, image FROM product WHERE name = " + name)) {

            if (rs.next()){
                foundProduct = (new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundProduct;
    }

    // todo: metod för att hämta en product

    // todo: metod för att spara produkter
}
