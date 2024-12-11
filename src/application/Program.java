package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Calendar;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("Test 1: FindById:");

        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println();

        System.out.println("Test 2: FindAll sellers:");

        List<Seller> findAllSellers = sellerDao.findAll();
        findAllSellers.forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("Test 3: Find sellers by department:");

        List<Seller> findSellersByDepartment = sellerDao.findByDepartment(new Department(2));
        findSellersByDepartment.forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("Test 4: Insert seller");

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

        System.out.println("Test 5: Update seller");

        Seller updatedSeller = new Seller();

        updatedSeller.setId(9);
        updatedSeller.setName("Toji");
        updatedSeller.setEmail("toji@gmail.com");

        calendar.set(2002, Calendar.APRIL, 18);
        updatedSeller.setBirthDate(calendar.getTime());

        updatedSeller.setBaseSalary(4600.00);
        updatedSeller.setDepartment(new Department(3));
        sellerDao.update(updatedSeller);

    }
}