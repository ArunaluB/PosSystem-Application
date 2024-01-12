package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;
import Edu.icet.Entity.CustomerEntity;
import Edu.icet.Entity.ItemEntity;

public interface customerdao extends Cruddao {
   CustomerEntity getItemByPhoneNumber(String phonenumber);
   String getSearchByCustomer(String phonenumber);
}
