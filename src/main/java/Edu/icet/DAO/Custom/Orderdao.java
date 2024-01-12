package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;
import Edu.icet.DTO.OrderDto;

import java.sql.SQLException;

public interface Orderdao extends Cruddao {
    String  getLastOrder() throws SQLException, ClassNotFoundException;

}
