package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.customerdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.CustomerEntity;
import Edu.icet.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity WHERE Contactnumber = :contactNumber", CustomerEntity.class);
            query.setParameter("contactNumber", value);

            CustomerEntity customerEntity = query.uniqueResult();
            if (customerEntity != null) {
                session.delete(customerEntity);
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
    public List getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
