package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import Edu.icet.DTO.UserDto;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class RegisterFromController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPwd;

    @FXML
    private Button btnAdminFormBtn;

    @FXML
    private JFXRadioButton typeEm;

    @FXML
    private ToggleGroup choics;

    @FXML
    private JFXRadioButton TypeAD;

    private String Type =null;


    @FXML
    private Label messageLabel;


    public void btnSaveOnAction(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

         String name = txtUName.getText();
         String email = txtEmail.getText();
         String password = txtPwd.getText();

         UserDto user = new UserDto();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setType(Type);

        userbo userbocall = new Userboimpl();

        try {
            userbocall.saveUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//        // Set the title of the alert
        alert.setTitle(Type);
//
//        // Set the header text of the alert
        alert.setHeaderText(email);
//
//        // Set the content text of the alert
       alert.setContentText(password);
//
//        // Show the alert
        alert.showAndWait();

    }

    @FXML
    private String choics(ActionEvent actionEvent) {

        if (typeEm.isSelected()) {
            Type = "Employee";
        } else if (TypeAD.isSelected()) {
            Type = "Admin";
        }

        System.out.println("Selected type: " + Type); // Add this for debugging

        return Type;
    }
    private boolean checkPassWordRequirements (String password){
        if(password.matches("^(?=.*[a-z]) (?=.* [A-Z]) (?=.*\\d) (?=.* [@$!%*?&]) [A-Za-z\\d@$!%*?&] {8,}$")){
            messageLabel.setText("Passsword good this ");
            messageLabel.setTextFill(Color.GREEN);
            return true;
        }else {
            messageLabel.setText("Passsword Does not Meet Requrements  ");
            messageLabel.setTextFill(Color.RED);
            return false;
        }
    }
    private void clear (){
        txtPwd.setText("");
        messageLabel.setText("");
    }


}
