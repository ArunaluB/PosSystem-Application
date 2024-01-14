package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DAO.Custom.Orderdetalsdao;
import Edu.icet.DAO.Custom.impl.OrderdetailsDaoimpl;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.orderdetailsDto;
import Edu.icet.Entity.OrderEntity;
import Edu.icet.Entity.OrderEntityM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderdetalsboimpl implements orderdetalsbo {

    private Orderdetalsdao daocall = new OrderdetailsDaoimpl();
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
    public List<orderdetailsDto> loaditem() throws SQLException, ClassNotFoundException {
//        List<orderdetailsDto> entityList = calledDao.getAll();
//
//        System.out.println("check data ana aka bo ge");
//        System.out.println(entityList.get(0).getOrderId());
//
//        System.out.println(entityList.get(0).getOrderId());
//        List<orderdetailsDto> dtoList = new ArrayList<>();
//
//        for (orderdetailsDto entity : entityList) {
//
//            // Check if the status is "pending"
//            if () {
//                OrderDto dto = new OrderDto();
//                dto.setOrderId(entity.getOrderId());
//                dto.setName(entity.getName());
//                dto.setEmail(entity.getEmail());
//                dto.setPhonenumber(entity.getPhonenumber());
//                dto.setNote(entity.getNote());
//                dto.setStatus(entity.getStatus());
//                dto.setType(entity.getType());
//                dto.setDate(entity.getDate());
//
//                dtoList.add(dto);
//            }
//        }
//
//        System.out.println("order bo ake aka ");
//
//        if (!dtoList.isEmpty()) {
//            System.out.println(dtoList.get(0).getDate());
//        } else {
//            System.out.println("dtoList is empty");
//        }
//
//        return dtoList;
//    }
        return null;
    }
}
