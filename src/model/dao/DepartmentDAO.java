package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);

    Department findById(Integer id);
    Department findByName(String name);

    List<Department> findAll();
}
