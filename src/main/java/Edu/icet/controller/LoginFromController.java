package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginFromController {
    private userbo bocallcall = new Userboimpl();

    @FXML
    private TextField textemail;

    @FXML
    private TextField txtpass;

    @FXML
    private Label errorlabel;



    public void StartOnAction(ActionEvent actionEvent) {
        String email = textemail.getText();
        String password = txtpass.getText();
        bocallcall.searchUser(email,password);
        System.out.println("ewrayyyyyyyyyyyyyyyyyyy");
    }

    public void fogetOnAction(ActionEvent actionEvent) {
    }
}
