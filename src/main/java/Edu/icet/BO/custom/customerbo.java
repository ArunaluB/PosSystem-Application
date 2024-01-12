package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.CustomerDto;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.item;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface customerbo extends SuperBo {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String ContactNumber) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    String  searchByCustomerDetails(String phonenumber) throws MessagingException;

    String getphonenumber();

}
