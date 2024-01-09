package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.ItemDto;
import Edu.icet.DTO.UserDto;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface itembo extends SuperBo {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteitem(String itemname) throws SQLException, ClassNotFoundException;

    void searchUserEmailCheck(String itemname) throws MessagingException;
}
