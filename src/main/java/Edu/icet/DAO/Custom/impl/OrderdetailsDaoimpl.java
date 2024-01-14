package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.Orderdetalsdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.OrderEntity;
import Edu.icet.Entity.OrderEntityM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderdetailsDaoimpl implements Orderdetalsdao {
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
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Executing getAll query");
            Query<OrderEntityM> query = session.createQuery("FROM OrderEntityM", OrderEntityM.class);
            List<OrderEntityM> list = query.list();
            System.out.println("Query executed successfully");

            transaction.commit();
            System.out.println("dao aka ban");
            System.out.println(list.get(0).getDate());

            return list;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log the exception or handle it as needed
            return null;

        } finally {
            session.close();
        }
    }
}
