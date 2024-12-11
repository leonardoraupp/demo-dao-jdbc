package model.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;
import model.dao.SellerDao;

import java.sql.*;
import java.util.*;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection = null;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartment().getId());

            int rowAffected = statement.executeUpdate();

            if (rowAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DbException("Unexpected error. No rows affected.");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE seller " +
                    "SET Name = ?, " +
                    "Email = ?, " +
                    "BirthDate = ?, " +
                    "BaseSalary = ?, " +
                    "DepartmentId = ? " +
                    "WHERE id = ?;");

            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            statement.setDouble(4, obj.getBaseSalary());
            statement.setInt(5, obj.getDepartment().getId());
            statement.setInt(6, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT seller.*, department.Name as DepartmentName " +
                    "FROM seller " +
                    "INNER JOIN department ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id =  ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                return instantiateSeller(resultSet, department);
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
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT seller.*, department.Name as DepartmentName " +
                    "FROM seller " +
                    "INNER JOIN department ON seller.DepartmentId = department.Id " +
                    "ORDER BY seller.Name ASC");
            resultSet = statement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departments = new HashMap<>();

            while (resultSet.next()) {

                Department department = departments.get(resultSet.getInt("DepartmentId"));

                if (department == null) {
                    department = instantiateDepartment(resultSet);
                    departments.put(resultSet.getInt("DepartmentId"), department);
                }
                Seller seller = instantiateSeller(resultSet, department);
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT seller.*, department.Name as DepartmentName " +
                    "FROM seller " +
                    "INNER JOIN department ON seller.DepartmentId = department.Id " +
                    "WHERE seller.DepartmentId = ? " +
                    "ORDER BY seller.Name ASC");
            statement.setInt(1, department.getId());
            resultSet = statement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (resultSet.next()) {
                Department dep = departmentMap.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    departmentMap.put(dep.getId(), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                sellers.add(seller);
            }
            return sellers;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepartmentName"));
        return department;
    }
}
