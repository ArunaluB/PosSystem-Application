package Edu.icet.BO.custom;

import Edu.icet.BO.SuperBo;
import Edu.icet.DTO.BillFinalDetals;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.PayBillDto;
import Edu.icet.DTO.orderdetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface orderdetalsbo extends SuperBo {
    boolean saveOrder(orderdetailsDto dto) throws SQLException, ClassNotFoundException;

    public boolean SaveFinalBillDetails(BillFinalDetals dto) throws SQLException, ClassNotFoundException;
    public boolean PayComplte(String OrderId) throws SQLException, ClassNotFoundException;

}

