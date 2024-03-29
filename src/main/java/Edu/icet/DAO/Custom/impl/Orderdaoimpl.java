package Edu.icet.DAO.Custom.impl;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.DAO.Custom.Orderdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.DTO.OrderDto;
import Edu.icet.Entity.ItemEntity;
import Edu.icet.Entity.OrderEntity;
import Edu.icet.Entity.UserEntity;
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
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            String orderId = ((OrderEntity) entity).getOrderId();
            OrderEntity order = session.get(OrderEntity.class, orderId);

            if (order != null) {
                // Update only if the order with the given orderId exists
                order.setStatus(((OrderEntity) entity).getStatus());
                session.saveOrUpdate(order);
                transaction.commit();
                return true;
            } else {
                // Handle the case when the order with the given orderId does not exist
                System.out.println("Order with orderId " + orderId + " not found.");
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (log or throw a custom exception)
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<OrderEntity> query = session.createQuery("FROM OrderEntity WHERE OrderId = :OrderId", OrderEntity.class);
            query.setParameter("OrderId", value);

            OrderEntity userEntity = query.uniqueResult();
            if (userEntity != null) {
                session.delete(userEntity);
            }

            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception as needed
            return false;

        } finally {
            session.close();
        }
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Executing getAll query");
            Query<OrderEntity> query = session.createQuery("FROM OrderEntity", OrderEntity.class);
            List<OrderEntity> list = query.list();
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

    @Override
    public OrderEntity getOrderByOrderId(String Orderid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query<OrderEntity> query = session.createQuery("FROM OrderEntity WHERE OrderId = :OrderId", OrderEntity.class);
            query.setParameter("OrderId", Orderid);
            OrderEntity OrderEntity = query.uniqueResult();

            transaction.commit();

            return OrderEntity;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately in your application
            return null;
        } finally {
            session.close();
        }

    }

}
