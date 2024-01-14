package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.orderdetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface orderdetalsbo extends SuperBo {
    boolean saveOrder(orderdetailsDto dto) throws SQLException, ClassNotFoundException;

    List<orderdetailsDto> loaditem() throws SQLException, ClassNotFoundException;
}

