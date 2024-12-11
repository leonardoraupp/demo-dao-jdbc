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
        seller.setName("Leonardo");
        seller.setEmail("leonardo@gmail.com");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 18);
        seller.setBirthDate(calendar.getTime());
        seller.setDepartment(new Department(3));
        sellerDao.insert(seller);
    }
}