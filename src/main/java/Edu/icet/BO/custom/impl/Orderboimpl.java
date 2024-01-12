package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.DAO.Custom.Itemdao;
import Edu.icet.DAO.Custom.Orderdao;
import Edu.icet.DAO.Custom.impl.Itemdaoimpl;
import Edu.icet.DAO.Custom.impl.Orderdaoimpl;
import Edu.icet.DAO.Util.SendMail;
import Edu.icet.DAO.Util.SendReceiptEmail;
import Edu.icet.DTO.OrderDto;
import Edu.icet.Entity.OrderEntity;

import java.sql.SQLException;

public class Orderboimpl implements Orderbo {
    private Orderdao calledDao= new Orderdaoimpl();
    private static String orderIdSaveThis ;
    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        String lastOrderId = calledDao.getLastOrder();
        System.out.println(lastOrderId);

        if (lastOrderId == null) {
            System.out.println("EES00001");
            return "EES00001";
        } else {
            int num = Integer.parseInt(lastOrderId.substring(3)) + 1;
            String newOrderId = String.format("EES%05d", num);
            System.out.println(newOrderId);
            return newOrderId;
        }
    }

    public void setidmethod(String orderid){
        orderIdSaveThis = orderid;
    }
    public String getorderid(){
        return orderIdSaveThis;
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        System.out.println("awa dto  aka ");
        System.out.println(dto);
        SendReceiptEmail.sendReceipt(dto);
        try {
            SendReceiptEmail.sendReceipt(dto);
            System.out.println("Receipt email sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending receipt email: " + e.getMessage());
            e.printStackTrace();
            // Handle the exception as needed

        }
        OrderEntity entity =new OrderEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhonenumber(dto.getPhonenumber());
        entity.setType(dto.getType());
        entity.setNote(dto.getNote());
        entity.setStatus(dto.getStatus());
        entity.setDate(dto.getDate());
        return calledDao.save(entity);
    }

    @Override
    public boolean deleteOrder(String Orderid) throws SQLException, ClassNotFoundException {

        return calledDao.delete(Orderid);
    }
}
