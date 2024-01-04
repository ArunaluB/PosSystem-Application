package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VerifyCodeFromControoler {
    @FXML
    private void initialize(){
        ErrorMessagelabel.setText("");
    }

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField firstNum;

    @FXML
    private TextField secondNum;

    @FXML
    private TextField thirdNum;

    @FXML
    private TextField forthNum;

    @FXML
    private Label ErrorMessagelabel;
    public void conformOnAction(ActionEvent actionEvent) throws IOException {

        String first = firstNum.getText();
        String second = secondNum.getText();
        String third= thirdNum.getText();
        String forth = forthNum.getText();

       String otpmassage =  first+second+third+forth;
       userbo usecallbo =new Userboimpl();
       if(usecallbo.verifyCode(otpmassage)){
           verificationsuccess();
       } else {
           ErrorMessagelabel.setText("OTP does not match try again");

       }


    }
    public void verificationsuccess() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ChangePasswordFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
