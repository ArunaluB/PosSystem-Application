package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DAO.Custom.OrderPayBillDao;
import Edu.icet.DAO.Custom.Orderdetalsdao;
import Edu.icet.DAO.Custom.OrderfinalbillDao;
import Edu.icet.DAO.Custom.impl.OrderPayBillDaoimpl;
import Edu.icet.DAO.Custom.impl.OrderdetailsDaoimpl;
import Edu.icet.DAO.Custom.impl.Orderfinalbilldaoimpl;
import Edu.icet.DTO.BillFinalDetals;
import Edu.icet.DTO.PayBillDto;
import Edu.icet.DTO.orderdetailsDto;
import Edu.icet.Entity.FinalBillRecodeEntity;
import Edu.icet.Entity.OrderEntityM;
import Edu.icet.Entity.PaymentDetailsEntity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class orderdetalsboimpl implements orderdetalsbo {

    private Orderdetalsdao daocall = new OrderdetailsDaoimpl();
    private OrderfinalbillDao daobillfinal = new Orderfinalbilldaoimpl();
    private OrderPayBillDao payment = new OrderPayBillDaoimpl();
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
    public boolean PayComplte(String OrderId) throws SQLException, ClassNotFoundException {
        System.out.println("payout akata awa");
        FinalBillRecodeEntity entity = payment.getOrderByOrderId(OrderId);
        System.out.println(payment.getOrderByOrderId(OrderId));
        PaymentDetailsEntity entityPaymentTranfer = new PaymentDetailsEntity();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        // Avoid setting the id explicitly, as it should be auto-generated
        // entityPaymentTranfer.setId(someValue); // Remove this line
        entityPaymentTranfer.setOrderid(entity.getOrderid());
        entityPaymentTranfer.setDate(currentDate);
        entityPaymentTranfer.setTotalPrise(entity.getTotalBillprise());
        System.out.println(entityPaymentTranfer);
        return payment.save(entityPaymentTranfer);

    }



}
