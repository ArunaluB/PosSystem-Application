package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DAO.Custom.Orderdetalsdao;
import Edu.icet.DAO.Custom.OrderfinalbillDao;
import Edu.icet.DAO.Custom.impl.OrderdetailsDaoimpl;
import Edu.icet.DAO.Custom.impl.Orderfinalbilldaoimpl;
import Edu.icet.DTO.BillFinalDetals;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.PayBillDto;
import Edu.icet.DTO.orderdetailsDto;
import Edu.icet.Entity.FinalBillRecodeEntity;
import Edu.icet.Entity.OrderEntity;
import Edu.icet.Entity.OrderEntityM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderdetalsboimpl implements orderdetalsbo {

    private Orderdetalsdao daocall = new OrderdetailsDaoimpl();
    private OrderfinalbillDao daobillfinal = new Orderfinalbilldaoimpl();
    @Override
    public boolean saveOrder(orderdetailsDto dto) throws SQLException, ClassNotFoundException {
        OrderEntityM obj = new OrderEntityM();
        // Map values from orderDetailsDto to orderEntityM
        obj.setProductname(dto.getProductname());
        obj.setPrise(dto.getPrise());
        obj.setDate(dto.getDate());
        obj.setType(dto.getType());
        obj.setOrderId(dto.getOrderId());
        obj.setEmail(dto.getEmail());
        obj.setPhonenumber(dto.getPhonenumber());
        obj.setQuantity(dto.getQuantity());
        obj.setSubtotal(dto.getSubtotal());
        System.out.println(obj);
        return daocall.save(obj);
    }

    @Override
    public boolean SaveFinalBillDetails(BillFinalDetals dto) throws SQLException, ClassNotFoundException {
        FinalBillRecodeEntity entity  =new FinalBillRecodeEntity();
        entity.setOrderid(dto.getOrderid());
        entity.setEmail(dto.getEmail());
        entity.setPhonenumber(dto.getPhonenumber());
        entity.setTotalBillprise(dto.getTotalBillprise());
        return daobillfinal.save(entity);
    }

    @Override
    public boolean PayComplte(PayBillDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }


}
