package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.UserDto;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface userbo extends SuperBo {

    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(String Email,String password) throws SQLException, ClassNotFoundException;

    void searchUser(String username, String password) throws IOException;

    boolean searchUserEmailCheck(String userEmail) throws MessagingException, IOException;



    boolean verifyCode(String otp);

    boolean validatepassword(String Npass, String CPass) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String Email) throws SQLException, ClassNotFoundException;

}
