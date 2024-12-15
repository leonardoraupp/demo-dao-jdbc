package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDAO {

    private Connection connection = null;

    public DepartmentDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO department (Name) " +
                    "VALUES(?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, obj.getName());
            int rowsAffected = statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (rowsAffected > 0) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DbException("Unexpected error. No rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE department " +
                    "SET name = ? " +
                    "WHERE Id = ?");
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getId());

            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM department  " +
                    "WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT department.* FROM department " +
                    "WHERE Id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                return new Department(id, name);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public Department findByName(String name) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT department.* FROM department " +
                    "WHERE Name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String nameofDepartment = resultSet.getString("Name");
                return new Department(id, nameofDepartment);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT department.* FROM department");
            resultSet = statement.executeQuery();

            List<Department> departmentList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                departmentList.add(new Department(id, name));
            }
            return departmentList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }
}
