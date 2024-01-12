package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.customerbo;
import Edu.icet.DAO.Custom.customerdao;
import Edu.icet.DAO.Custom.impl.Customerdaoimpl;
import Edu.icet.DTO.CustomerDto;
import Edu.icet.Entity.CustomerEntity;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class customerboimpl implements customerbo {

    private customerdao calledDAO = new Customerdaoimpl();
    public static String phoneNumberSearch ;


    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        CustomerEntity entityobj = new CustomerEntity();
        entityobj.setId((long)0);
        entityobj.setCustomerName(dto.getCustomerName());
        entityobj.setEmail(dto.getEmail());
        entityobj.setContactnumber(dto.getContactnumber());
        System.out.println(entityobj);

        return calledDAO.save(entityobj);
    }

    @Override
    public boolean deleteCustomer(String ContactNumber) throws SQLException, ClassNotFoundException {
        return calledDAO.delete(ContactNumber);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String searchByCustomerDetails(String phonenumber) throws MessagingException {
        phoneNumberSearch = phonenumber;
        String Name = calledDAO.getSearchByCustomer(phonenumber);
        return Name;
    }
    public String getphonenumber(){
        return phoneNumberSearch;
    }


}
