package Edu.icet.DAO.Custom;

import Edu.icet.DAO.Cruddao;

public interface Userdao extends Cruddao {
    Userdao searchCustomer(String id);
}
