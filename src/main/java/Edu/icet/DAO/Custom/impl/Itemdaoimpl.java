package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.Itemdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.CustomerEntity;
import Edu.icet.Entity.ItemEntity;
import Edu.icet.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class Itemdaoimpl implements Itemdao {
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

            String productName = ((ItemEntity) entity).getProductname();
            ItemEntity item = session.byNaturalId(ItemEntity.class).using("Productname", productName).load();

            if (item != null) {
                // Update only if the item with the given product name exists
                item.setAvalible(((ItemEntity) entity).getAvalible());
                item.setPrise(((ItemEntity) entity).getPrise());

                session.saveOrUpdate(item);
                transaction.commit();
                return true;
            } else {
                // Handle the case when the item with the given product name does not exist
                System.out.println("Item with product name " + productName + " not found.");
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

            Query<ItemEntity> query = session.createQuery("FROM ItemEntity WHERE Productname = :Productname", ItemEntity.class);
            query.setParameter("Productname", value); // Correct parameter name

            ItemEntity itemEntity = query.uniqueResult();
            if (itemEntity != null) {
                session.delete(itemEntity);
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
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM ItemEntity");
        List<ItemEntity> list = query.list();
        session.close();
        return list;
    }


    @Override
    public ItemEntity getItemByProductname(String itemName) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query<ItemEntity> query = session.createQuery("FROM ItemEntity WHERE Productname = :Productname", ItemEntity.class);
            query.setParameter("Productname", itemName);
            ItemEntity itemEntity = query.uniqueResult();

            transaction.commit();

            return itemEntity;

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
