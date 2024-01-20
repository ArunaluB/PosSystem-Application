package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.OrderPayBillDao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.FinalBillRecodeEntity;
import Edu.icet.Entity.OrderEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderPayBillDaoimpl implements OrderPayBillDao {
    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
        System.out.println("========================================");
        System.out.println(entity);
        System.out.println("========================================");
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
    public FinalBillRecodeEntity getOrderByOrderId(String orderId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<FinalBillRecodeEntity> query = session.createQuery("FROM FinalBillRecodeEntity WHERE orderid = :orderid", FinalBillRecodeEntity.class);
            query.setParameter("orderid", orderId);

            FinalBillRecodeEntity orderEntity = query.uniqueResult();

            transaction.commit();

            return orderEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log or handle the exception appropriately in your application
            return null;
        } finally {
            session.close();
        }
    }


}
