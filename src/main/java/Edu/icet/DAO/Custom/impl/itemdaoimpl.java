package Edu.icet.DAO.Custom.impl;

import Edu.icet.DAO.Custom.itemdao;
import Edu.icet.DAO.Util.HibernateUtil;
import Edu.icet.Entity.ItemEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class itemdaoimpl implements itemdao {
    @Override
    public boolean save(Object entity) throws SQLException, ClassNotFoundException {
        return false;
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
        Query query = session.createQuery("FROM ItemEntity");
        List<ItemEntity> list = query.list();
        session.close();
        return list;

    }
}
