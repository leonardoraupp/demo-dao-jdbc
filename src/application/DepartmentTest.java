package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class DepartmentTest {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("---------Test 1: Find Department by Id---------");
        System.out.println(departmentDao.findById(1));

        System.out.println();
        System.out.println();

        System.out.println("---------Test 2: Find All Departments---------");
        departmentDao.findAll().forEach(System.out::println);

    }
}
