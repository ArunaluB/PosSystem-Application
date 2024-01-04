package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;

public class FogetEmailFromControler {



    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUserEmail;

    public void btnSendOTPOnAction(ActionEvent actionEvent) throws MessagingException, IOException {
        String userEmail = txtUserEmail.getText();
        userbo userbocall = new Userboimpl();
        userbocall.searchUserEmailCheck(userEmail);
        System.out.println("mail aka awa ");
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/VerifyCodeFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
