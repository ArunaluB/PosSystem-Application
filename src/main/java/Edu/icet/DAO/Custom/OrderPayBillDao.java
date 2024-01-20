package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;
import Edu.icet.Entity.FinalBillRecodeEntity;

public interface OrderPayBillDao extends Cruddao {
    public FinalBillRecodeEntity getOrderByOrderId(String Orderid) ;
}
