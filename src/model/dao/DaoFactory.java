package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.Connection;

public class DaoFactory {
    private static Connection conn = DB.getConnection();

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(conn);
    }
}
