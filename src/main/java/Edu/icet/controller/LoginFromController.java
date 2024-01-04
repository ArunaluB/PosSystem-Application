package Edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginFromController {

    @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXTextField txtpassword;

    @FXML
    private TextField txtpass;

    @FXML
    private Label errorlabel;



    public void StartOnAction(ActionEvent actionEvent) {
    }

    public void fogetOnAction(ActionEvent actionEvent) {
    }
}
