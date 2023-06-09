package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class ProductDao implements com.codecool.shop.dao.ProductDao {
    private DataSource dataSource;
    private ProductCategoryDao productCategoryDaoJdbc;
    private SupplierDao supplierDaoJdbc;

    public ProductDao(DataSource dataSource, ProductCategoryDao productCategoryDaoJdbc, SupplierDao supplierDaoJdbc) {
        this.dataSource = dataSource;
        this.productCategoryDaoJdbc = productCategoryDaoJdbc;
        this.supplierDaoJdbc = supplierDaoJdbc;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (name, price, currency, description, product_category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getDefaultPrice());
            statement.setString(3, product.getDefaultCurrencyString());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getProductCategory().getId());
            statement.setInt(6, product.getSupplier().getId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new product",e);
        }
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
            ProductCategory productCategory = productCategoryDaoJdbc.find(rs.getInt("product_category_id"));
            Supplier supplier = supplierDaoJdbc.find(rs.getInt("supplier_id"));
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
            String sql = "SELECT id, name, price, currency, description, product_category_id, supplier_id FROM product";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                String currency = rs.getString("currency");
                String description = rs.getString("description");
                ProductCategory productCategory = productCategoryDaoJdbc.find(rs.getInt("product_category_id"));
                Supplier supplier = supplierDaoJdbc.find(rs.getInt("supplier_id"));
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
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, currency, description, product_category_id, supplier_id FROM product WHERE supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product oneProduct = new Product(rs.getString(2),rs.getBigDecimal(3), rs.getString(4), rs.getString(5),  productCategoryDaoJdbc.find(rs.getInt(6)),  supplierDaoJdbc.find(rs.getInt(7)));
                oneProduct.setId(rs.getInt(1));
                result.add(oneProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding product by supplier", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, currency, description, product_category_id, supplier_id FROM product WHERE product_category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product oneProduct = new Product(rs.getString(2),rs.getBigDecimal(3), rs.getString(4), rs.getString(5),  productCategoryDaoJdbc.find(rs.getInt(6)),  supplierDaoJdbc.find(rs.getInt(7)));
                oneProduct.setId(rs.getInt(1));
                result.add(oneProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding product by productCategory", e);
        }
    }
}
