package Edu.icet.BO.custom.impl;

import Edu.icet.BO.custom.userbo;
import Edu.icet.DAO.Custom.Userdao;
import Edu.icet.DAO.Custom.impl.Userdaoimpl;
import Edu.icet.DAO.Util.SendMail;
import Edu.icet.DTO.UserDto;
import Edu.icet.Entity.UserEntity;
import Edu.icet.controller.LoginFromController;
import Edu.icet.controller.VerifyCodeFromControoler;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.scene.paint.Color;

import javax.mail.MessagingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Userboimpl implements userbo {

   static String OTPConvert;
   static String Email;

    int random;
    int OTP;

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
    public boolean updateCustomer(String Email, String password) throws SQLException, ClassNotFoundException {
        UserEntity obj = new UserEntity();
        obj.setId((long)0);
        obj.setName("Namal");
        obj.setEmail(Email);
        obj.setPassword(password);
        obj.setType("Admin");
        return usercalldao.update(obj);
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

    @Override
    public void searchUserEmailCheck(String userEmail) throws MessagingException {
        Email =usercalldao.getSearchByUsername(userEmail);
        System.out.println(Email);
        random = new Random().nextInt(9000);
        OTP = 1000+random;
        System.out.println("" + OTP + "");
        setOtp(OTP);
        SendMail.outMail(""+OTP+"", Email, "E & E Servise Center Panadura");

    }

    @Override
    public boolean verifyCode(String otp) {

        System.out.println("Genarate====otp code ");
        System.out.println(OTPConvert);
        System.out.println("user dena aka");
        System.out.println(otp);

        if(!OTPConvert.equals(otp)) {
            System.out.println("meka thama yana aka"+Email);
         return false   ;
        }

        System.out.println("meka thama yana aka"+Email);
        return true;
    }


    public boolean validatepassword(String Npass, String CPass) throws SQLException, ClassNotFoundException {
        if(isValidPassword(Npass)) {
            if(Npass.equals(CPass)) {
                System.out.println("password 2 kama harii");
                usercalldao.updatePasswordByUsername(Email,Npass);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(String Email) throws SQLException, ClassNotFoundException {
        return usercalldao.delete(Email);
    }

    private void setOtp(int num){
        int otp = num;
        OTPConvert = String.valueOf(num);
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

    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long
        String regex = "^.{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return password.length() >= 8 && pattern.matcher(password).matches();
    }


}

//86f7e437faa5a7fce15d1ddcb9eaeaea377667b8