package Edu.icet.DAO.Custom.impl;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.DAO.Custom.Orderdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.DTO.OrderDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class Orderdaoimpl implements Orderdao {


    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
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

    @Override
    public String getLastOrder() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "SELECT OrderId FROM OrderEntity ORDER BY OrderId DESC";
            Query<String> query = session.createQuery(hql, String.class);  // Change Integer to String
            query.setMaxResults(1);
            String lastOrderId = query.uniqueResult();

            if (lastOrderId != null) {
                return lastOrderId;
            }
            return null;
        } finally {
            if (transaction != null) {
                transaction.commit();
            }
            session.close();
        }
    }

}