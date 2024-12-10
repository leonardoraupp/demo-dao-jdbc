package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println("Test 1: FindById:");
        System.out.println(seller);

        System.out.println();
        System.out.println();

        List<Seller> findAllSellers = sellerDao.findAll();
        System.out.println("Test 2: FindAll sellers:");
        findAllSellers.forEach(System.out::println);
        System.out.println(seller);
    }
}