package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.DAO.Custom.Orderdao;
import Edu.icet.DAO.Custom.impl.Orderdaoimpl;
import Edu.icet.DAO.Util.CompleteEmail;
import Edu.icet.DAO.Util.SendReceiptEmail;
import Edu.icet.DTO.OrderDto;
import Edu.icet.Entity.OrderEntity;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Edu.icet.DAO.Util.CompleteEmail.sendReceiptComplted;

public class Orderboimpl implements Orderbo {
    @Override
    public boolean updateByCompele(OrderDto dto,String completed) throws SQLException, ClassNotFoundException {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setName(dto.getName());
        entity.setPhonenumber(dto.getPhonenumber());
        entity.setNote(dto.getNote());
        entity.setType(dto.getType());
        entity.setDate(dto.getDate());
        if(completed.equalsIgnoreCase("completed")){
            entity.setStatus("completed");
        } else if (completed.equals("close")) {
            entity.setStatus("Close");
        }



        return calledDao.update(entity) ;

    }

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

    @Override
    public OrderDto searchByOrderdetails(String itemname) throws MessagingException {
        OrderEntity Entity =calledDao.getOrderByOrderId(itemname);
        OrderDto dto = new OrderDto();
        if(Entity == null){
            dto.setOrderId("000000");
            dto.setStatus("close");
            return dto;
        }
       // OrderDto dto = new OrderDto();
        dto.setOrderId(Entity.getOrderId());
        dto.setName(Entity.getName());
        dto.setEmail(Entity.getEmail());
        dto.setPhonenumber(Entity.getPhonenumber());
        dto.setNote(Entity.getNote());
        dto.setType(Entity.getStatus());
        dto.setStatus(Entity.getStatus());
        return dto;
    }

    @Override
//    public List<OrderDto> loaditem() throws SQLException, ClassNotFoundException {
//        List<OrderEntity> entityList = calledDao.getAll();
//        System.out.println("check data ana aka bo ge");
//        System.out.println(entityList.get(0).getOrderId());
//
//        System.out.println(entityList.get(0).getOrderId());
//        List<OrderDto> dtoList = new ArrayList<>();
//
//        for (OrderEntity entity : entityList) {
//            OrderDto dto = new OrderDto();
//            dto.setOrderId(entity.getOrderId());
//            dto.setName(entity.getName());
//            dto.setEmail(entity.getEmail());
//            dto.setPhonenumber(entity.getPhonenumber());
//            dto.setNote(entity.getNote());
//            dto.setStatus(entity.getStatus());
//            dto.setType(entity.getType());
//            dto.setDate(entity.getDate());
//
//            dtoList.add(dto);
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

    public List<OrderDto> loaditem() throws SQLException, ClassNotFoundException {
        List<OrderEntity> entityList = calledDao.getAll();

        System.out.println("check data ana aka bo ge");
        System.out.println(entityList.get(0).getOrderId());

        System.out.println(entityList.get(0).getOrderId());
        List<OrderDto> dtoList = new ArrayList<>();

        for (OrderEntity entity : entityList) {
            String checkst = entity.getStatus();
            // Check if the status is "pending"
            if (checkst.equals("Pending")) {
                OrderDto dto = new OrderDto();
                dto.setOrderId(entity.getOrderId());
                dto.setName(entity.getName());
                dto.setEmail(entity.getEmail());
                dto.setPhonenumber(entity.getPhonenumber());
                dto.setNote(entity.getNote());
                dto.setStatus(entity.getStatus());
                dto.setType(entity.getType());
                dto.setDate(entity.getDate());

                dtoList.add(dto);
            }
        }

        System.out.println("order bo ake aka ");

        if (!dtoList.isEmpty()) {
            System.out.println(dtoList.get(0).getDate());
        } else {
            System.out.println("dtoList is empty");
        }

        return dtoList;
    }

    @Override
    public List<OrderDto> alldata() throws SQLException, ClassNotFoundException {
        List<OrderEntity> entityList = calledDao.getAll();
        List<OrderDto> dtoList = new ArrayList<>();
        for (OrderEntity entity : entityList) {
            OrderDto dto = new OrderDto();
            dto.setOrderId(entity.getOrderId());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dto.setPhonenumber(entity.getPhonenumber());
            dto.setNote(entity.getNote());
            dto.setStatus(entity.getStatus());
            dto.setType(entity.getType());
            dto.setDate(entity.getDate());

            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public boolean updateItem(OrderDto dto) throws SQLException, ClassNotFoundException {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setName(dto.getName());
        entity.setPhonenumber(dto.getPhonenumber());
        entity.setNote(dto.getNote());
        entity.setType(dto.getType());
        entity.setDate(dto.getDate());
        entity.setStatus("Processing");
       // entity.setStatus("Pppp");
     //   entity.setOrderidnatural(dto.getOrderId());
        return calledDao.update(entity) ;
    }




}
