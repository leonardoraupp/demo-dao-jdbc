package model.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection = null;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {
//        PreparedStatement statement;
//        ResultSet resultSet;
//        try {
//            connection.createStatement();
//
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        }
    }

    @Override
    public void update(Seller obj) {

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
                    "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id " +
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
                    "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id " +
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
    public List<Seller> findByDepartment( Department department) {
        return List.of();
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
