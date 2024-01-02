package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import Edu.icet.DTO.UserDto;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginFromController {



    @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXTextField txtpassword;


    public void loginOnAction(ActionEvent actionEvent) {
        String Email = textEmail.getText();
        String password = txtpassword.getText();

        userbo userbocall = new Userboimpl();
        userbocall.searchUser(Email, password);

    }


    public void fogetpasswordonAction(ActionEvent actionEvent) {
    }



}
