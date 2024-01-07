package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.itemget;

import java.sql.SQLException;
import java.util.List;

public interface itembo extends SuperBo {

    boolean saveItem(itemget dto) throws SQLException, ClassNotFoundException;

    List loadData() throws SQLException, ClassNotFoundException;


}
