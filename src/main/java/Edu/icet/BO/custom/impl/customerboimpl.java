package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.customerbo;
import Edu.icet.DTO.CustomerDto;

import java.sql.SQLException;

public class customerboimpl implements customerbo {


    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String ContactNumber) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
