package Edu.icet.DAO.Custom.impl;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class Userdaoimpl implements Userdao {

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
    public String getPasswordByUsername(String username) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Use HQL to create a query to retrieve the password for the given username
            Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE Email = :username", UserEntity.class);
            query.setParameter("username", username);
            UserEntity userEntity = query.uniqueResult();

            // Check if userEntity is not null before retrieving the password
            String password = (userEntity != null) ? userEntity.getPassword() : null;

            transaction.commit();

            return password;
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

    public String getSearchByUsername(String username) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Use HQL to create a query to retrieve the password for the given username
            Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE Email = :username", UserEntity.class);
            query.setParameter("username", username);
            UserEntity userEntity = query.uniqueResult();

            // Check if userEntity is not null before retrieving the password
            String Email = (userEntity != null) ? userEntity.getEmail() : null;

            transaction.commit();

            return Email;

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





