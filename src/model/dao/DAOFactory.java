package model.dao;

import db.DB;
import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDAOJDBC;

public class DAOFactory {

    public static SellerDAO createSellerDao() {
        return new SellerDAOJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDao() {
        return new DepartmentDAOJDBC(DB.getConnection());
    }
}
