package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection = null;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("");

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
            statement = connection.prepareStatement("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("");

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
