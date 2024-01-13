package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.UserDto;
import Edu.icet.DTO.item;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

public interface Orderbo extends SuperBo {
    String generateId() throws SQLException, ClassNotFoundException;
    void setidmethod(String orderid);
    String getorderid();
    boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String Orderid) throws SQLException, ClassNotFoundException;

    OrderDto searchByOrderdetails(String itemname) throws MessagingException;

    List<OrderDto> loaditem() throws SQLException, ClassNotFoundException;

    boolean updateItem(OrderDto dto) throws SQLException, ClassNotFoundException;

}
