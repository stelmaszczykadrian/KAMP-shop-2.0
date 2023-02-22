package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private ProductCategoryDaoJdbc productCategoryDaoJdbc;
    private SupplierDaoJdbc supplierDaoJdbc;

    public ProductDaoJdbc(DataSource dataSource, JdbcTemplate jdbcTemplate, ProductCategoryDaoJdbc productCategoryDaoJdbc, SupplierDaoJdbc supplierDaoJdbc) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.productCategoryDaoJdbc = productCategoryDaoJdbc;
        this.supplierDaoJdbc = supplierDaoJdbc;
    }

    @Override
    public void add(Product product) {

        var sql = """
                INSERT INTO product(name, price, currency, description, product_category_id, supplier_id)
                VALUES (?, ?, ?, ?, ?, ?);
                 """;
        jdbcTemplate.update(
                sql, product.getName(),
                product.getPrice(),
                product.getDefaultCurrency(),
                product.getDescription(),
                product.getProductCategory().getId(),
                product.getSupplier().getId()
        );
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                        SELECT name, price, currency, description, product_category_id, supplier_id
                         FROM product WHERE id = ?""";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString("name");
            BigDecimal price = rs.getBigDecimal("price");
            String currency = rs.getString("currency");
            String description = rs.getString("description");
            ProductCategory productCategory = productCategoryDaoJdbc.find(rs.getInt(5));
            Supplier supplier = supplierDaoJdbc.find(rs.getInt(6));
            Product product = new Product(name, price, currency, description, productCategory, supplier);
            return product;
        } catch (SQLException e) {
                throw new RuntimeException("Error while reading product", e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing product", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, price, currency, description, product_category_id, supplier_id FROM product";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                ProductCategory productCategory = productCategoryDaoJdbc.find(rs.getInt(5));
                Supplier supplier = supplierDaoJdbc.find(rs.getInt(6));
                Product product = new Product(name, price, currency, description, productCategory, supplier);
                product.setId(id);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, price, currency, description, product_category_id, supplier_id FROM product WHERE supplier = ?";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                ProductCategory productCategory = productCategoryDaoJdbc.find(rs.getInt(5));
                Product product = new Product(name, price, currency, description, productCategory, supplier);
                product.setId(id);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products by supplier", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, price, currency, description, product_category_id, supplier_id FROM product WHERE productCategory = ?";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                Supplier supplier = supplierDaoJdbc.find(rs.getInt(6));
                Product product = new Product(name, price, currency, description, productCategory, supplier);
                product.setId(id);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products by productCategory", e);
        }
    }
}
