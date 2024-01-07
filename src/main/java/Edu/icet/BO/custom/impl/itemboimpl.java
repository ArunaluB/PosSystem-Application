package Edu.icet.BO.custom.impl;

import Edu.icet.BO.SuperBo;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Custom.impl.Userdaoimpl;
import Edu.icet.DAO.Custom.impl.itemdaoimpl;
import Edu.icet.DAO.Custom.itemdao;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.itemget;
import Edu.icet.Entity.ItemEntity;

import java.sql.SQLException;
import java.util.List;

public class itemboimpl implements itembo {


    private itemdao itemcalldao = new itemdaoimpl();


    @Override
    public boolean saveItem(itemget dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto.getProductname());
        ItemEntity
        return false;
    }

    @Override
    public List loadData() throws SQLException, ClassNotFoundException {
        return itemcalldao.getAll();
    }

}
