package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.ItemDto;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.item;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

public interface itembo extends SuperBo {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteitem(String itemname) throws SQLException, ClassNotFoundException;
    List<item> loaditem() throws SQLException, ClassNotFoundException;

    boolean updateItem(String itemname,Double prise,String avalable) throws SQLException, ClassNotFoundException;

    item searchItemNameCheck(String itemname) throws MessagingException;

    String setIteamname(String itemname);

    String getd();

    item rowdetalis ();

    //item olddatashown()
}
