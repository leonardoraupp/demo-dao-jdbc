package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("---------Test 1: FindById---------");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println();

        System.out.println("---------Test 2: FindAll sellers---------");
        List<Seller> findAllSellers = sellerDao.findAll();
        findAllSellers.forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("---------Test 3: Find sellers by department---------");
        System.out.println("Enter a department ID:");
        int idDepartment = sc.nextInt();
        List<Seller> findSellersByDepartment = sellerDao.findByDepartment(new Department(idDepartment));
        findSellersByDepartment.forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("---------Test 4: Insert seller---------");
        Seller newSeller = new Seller();
        newSeller.setName("Leonardo");
        newSeller.setEmail("leonardo@gmail.com");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 18);
        newSeller.setBirthDate(calendar.getTime());

        newSeller.setDepartment(new Department(3));
//        sellerDao.insert(newSeller);

        System.out.println();
        System.out.println();

        System.out.println("---------Test 5: Update seller---------");
        Seller updatedSeller = sellerDao.findById(9);
        updatedSeller.setName("Maki");
        updatedSeller.setEmail("maki@gmail.com");
        sellerDao.update(updatedSeller);
        System.out.println("Updated seller.");

        System.out.println();
        System.out.println();

        System.out.println("--------- Test 6: Delete seller ---------");
        System.out.println("Enter a seller ID to delete:");
        int idSeller = sc.nextInt();
        sellerDao.deleteById(idSeller);

        sc.close();
    }
}