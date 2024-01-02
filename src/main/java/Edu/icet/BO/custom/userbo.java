package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface userbo extends SuperBo {

    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(UserDto dto) throws SQLException, ClassNotFoundException;

    void searchUser(String username,String password);

    void passwordcheck(List list);
}
