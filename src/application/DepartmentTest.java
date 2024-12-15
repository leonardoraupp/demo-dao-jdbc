package application;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.util.Scanner;

public class DepartmentTest {
    public static void main(String[] args) {

        DepartmentDAO departmentDao = DAOFactory.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("---------Test 1: Find Department by Id---------");
        System.out.println(departmentDao.findById(1));

        System.out.println();
        System.out.println();

        System.out.println("---------Test 2: Find All Departments---------");
        departmentDao.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("---------Test 3: Create a Department---------");
        System.out.print("Enter a name for the department: ");
        String depName = sc.nextLine();
        departmentDao.insert(new Department(depName));
        if (departmentDao.findByName(depName).getName().equals(depName)) {
            System.out.println("Department created.");
        }

        System.out.println();
        System.out.println();

        System.out.println("---------Test 4: Update a Department---------");

        System.out.print("Enter an id: ");
        int depId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the department name: ");
        depName = sc.nextLine();
        departmentDao.update(new Department(depId, depName));

        if (departmentDao.findById(depId).getName().equals(depName)) {
            System.out.println("Department updated.");
        }

        System.out.println();
        System.out.println();

        System.out.println("---------Test 5: Delete a Department---------");

        System.out.println("Enter a department Id to delete: ");
        depId = sc.nextInt();

        departmentDao.deleteById(depId);
        System.out.println("Department deleted.");

        System.out.println();
        System.out.println();
        System.out.println("Updated list:");
        departmentDao.findAll().forEach(System.out::println);
    }
}
