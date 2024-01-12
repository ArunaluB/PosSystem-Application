package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.UserDto;

import java.sql.SQLException;

public interface Orderbo extends SuperBo {
    String generateId() throws SQLException, ClassNotFoundException;
    void setidmethod(String orderid);
    String getorderid();
    boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
}
