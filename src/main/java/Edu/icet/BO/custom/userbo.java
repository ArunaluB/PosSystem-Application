package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.UserDto;

import java.sql.SQLException;

public interface userbo extends SuperBo {

    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(UserDto dto) throws SQLException, ClassNotFoundException;
}
