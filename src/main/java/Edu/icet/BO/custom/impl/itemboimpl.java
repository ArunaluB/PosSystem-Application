package Edu.icet.BO.custom.impl;

import Edu.icet.BO.SuperBo;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Custom.impl.Userdaoimpl;
import Edu.icet.DAO.Custom.impl.itemdaoimpl;
import Edu.icet.DAO.Custom.itemdao;

import java.sql.SQLException;
import java.util.List;

public class itemboimpl implements itembo {


    private itemdao itemcalldao = new itemdaoimpl();
    @Override
    public List loadData() throws SQLException, ClassNotFoundException {
        return itemcalldao.getAll();
    }
}
