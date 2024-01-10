package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;
import Edu.icet.Entity.ItemEntity;

public interface Itemdao extends Cruddao {

    ItemEntity getItemByProductname(String itemName);

}
