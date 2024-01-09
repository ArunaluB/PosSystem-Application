package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.itembo;
import Edu.icet.DAO.Custom.Itemdao;
import Edu.icet.DAO.Custom.impl.Itemdaoimpl;
import Edu.icet.DTO.ItemDto;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.item;
import Edu.icet.Entity.ItemEntity;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            obj.setColor("FFB605");
            obj.setAvalible("Yes");
        }else {
            obj.setType("Electrical");
            obj.setColor("1273de");
            obj.setAvalible("Yes");

        }
        System.out.println(obj);

        return itemdao.save(obj) ;
    }

    @Override
    public boolean deleteitem(String itemname) throws SQLException, ClassNotFoundException {
        return itemdao.delete(itemname);
    }

    @Override
    public List<item> loaditem() throws SQLException, ClassNotFoundException {
        List<ItemEntity> entityList = itemdao.getAll();
        List<item> dtoList = new ArrayList<>();

        for (ItemEntity entity : entityList) {
            item dto = new item();
            dto.setProductname(entity.getProductname());
            dto.setPrise(entity.getPrise());
            dto.setImgsrc(entity.getImgsrc());
            dto.setColor(entity.getColor());
            dto.setAvalable(entity.getAvalible());
            dtoList.add(dto);
//            System.out.println("Converted entity to DTO:");
//            System.out.println("Product Name: " + dto.getProductname());
//            System.out.println("Price: " + dto.getPrise());
//            System.out.println("Image Source: " + dto.getImgsrc());
//            System.out.println("Color: " + dto.getColor());
//            System.out.println("Available: " + dto.getAvalable());

        }
        System.out.println(dtoList);
        return dtoList;
    }


    @Override
    public void searchUserEmailCheck(String itemname) throws MessagingException {

    }
}
