package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.customerdao;
import Edu.icet.DAO.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class Customerdaoimpl implements customerdao {
    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
        System.out.println("dao layer awa ");
        System.out.println(entity);
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Object entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
