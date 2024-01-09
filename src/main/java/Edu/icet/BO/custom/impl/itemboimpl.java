package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.itembo;
import Edu.icet.DAO.Custom.Itemdao;
import Edu.icet.DAO.Custom.impl.Itemdaoimpl;
import Edu.icet.DTO.ItemDto;
import Edu.icet.DTO.UserDto;
import Edu.icet.Entity.ItemEntity;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class itemboimpl implements itembo {

   private Itemdao itemdao =new Itemdaoimpl();

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto.getType());
        ItemEntity obj =new ItemEntity();
        obj.setId(0);
        obj.setProductname(dto.getProductname());
        obj.setPrise(Double.valueOf(dto.getPrise()));
        obj.setImgsrc(dto.getImgsrc());
        String chocise = dto.getType();
        if(chocise.equals("Electronic")){
            obj.setType("Electronic");
            obj.setColor("#8ed1fc");
            obj.setAvalible("Yes");
        }else {
            obj.setType("Electrical");
            obj.setColor("#fccb00");
            obj.setAvalible("Yes");

        }
        System.out.println(obj);

        return itemdao.save(obj) ;
    }

    @Override
    public boolean deleteCustomer(String itemname) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void searchUserEmailCheck(String itemname) throws MessagingException {

    }
}
