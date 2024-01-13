package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;
import Edu.icet.DTO.OrderDto;
import Edu.icet.Entity.OrderEntity;

import java.sql.SQLException;

public interface Orderdao extends Cruddao {
    String  getLastOrder() throws SQLException, ClassNotFoundException;
    OrderEntity getOrderByOrderId(String Orderid);

}
