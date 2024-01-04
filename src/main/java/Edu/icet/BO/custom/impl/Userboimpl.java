package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.userbo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Custom.impl.Userdaoimpl;
import Edu.icet.DTO.UserDto;
import Edu.icet.Entity.UserEntity;
import Edu.icet.controller.LoginFromController;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.scene.paint.Color;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class Userboimpl implements userbo {

    private int count = 1;
    private Userdao usercalldao = new Userdaoimpl();

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {

        String usergetpassword = dto.getPassword();
        if (usergetpassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            System.out.println("password hari");
        }

        String Encrippassword = passwordHash(usergetpassword);
        UserEntity obj = new UserEntity();
        obj.setId((long)0);
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
        String FrendEndHashpass = passwordHash(password);
        String hashpass = usercalldao.getPasswordByUsername(username);
        System.out.println(hashpass);
        if(FrendEndHashpass.equals(hashpass)) {
            System.out.println("paword aka hari ");
        } else {
            System.out.println("password aka waradiy");
        }

    }

    private String passwordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] rbt = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : rbt) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb);
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordcheck(List list) {

    }


}

//86f7e437faa5a7fce15d1ddcb9eaeaea377667b8