package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class SupplierDao implements com.codecool.shop.dao.SupplierDao {

    private DataSource dataSource;

    public SupplierDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new supplier",e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM supplier WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            return new Supplier(name, description);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding supplier", e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing supplier", e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all supplier", e);
        }
    }
}
