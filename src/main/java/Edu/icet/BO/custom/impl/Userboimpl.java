package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.userbo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Custom.impl.Userdaoimpl;
import Edu.icet.DTO.UserDto;
import Edu.icet.Entity.UserEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class Userboimpl implements userbo {

    private int   count =1;
    private Userdao usercalldao = new Userdaoimpl();

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {

        String usergetpassword = dto.getPassword();
        String Encrippassword = passwordHash(usergetpassword);
        UserEntity obj = new UserEntity();
        obj.setId(String.valueOf(count));
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        obj.setType(dto.getType());
        obj.setPassword(Encrippassword);
        count++;
        return usercalldao.save(obj);
    }

    @Override
    public boolean updateCustomer(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void searchUser(String username, String password) {
        usercalldao.getPasswordByUsername(username);

    }

    private String passwordHash(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] rbt= md.digest();
            StringBuilder sb =new StringBuilder();
            for(byte b:rbt) {
                sb.append(String.format("%02x" , b));
            }
            System.out.println(sb);
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordcheck(List list){

    }
}
