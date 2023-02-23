package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public ProductCategoryDaoJdbc(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(ProductCategory productCategory) {
//        if (productCategory.getDescription() == null) {
//            productCategory.setDescription("");
//        }
//        var sql = """
//                INSERT INTO product_category(name, department, description)
//                VALUES (?, ?, ?);
//                 """;
//        jdbcTemplate.update(
//                sql, productCategory.getName(),
//                productCategory.getDepartment(),
//                productCategory.getDescription()
//        );
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_category(name, department, description) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productCategory.getName());
            statement.setString(2, productCategory.getDepartment());
            statement.setString(3, productCategory.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            productCategory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new supplier",e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, department, description FROM product_category WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            String description = resultSet.getString("description");
            return new ProductCategory(name, department, description);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding productCategory", e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product_category WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing product_category", e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM product_category";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<ProductCategory> productCategories = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                ProductCategory productCategory = new ProductCategory(name, department, description);
                productCategory.setId(id);
                productCategories.add(productCategory);
            }
            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all productCategories", e);
        }
    }
}