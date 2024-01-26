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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
         System.out.println("tttttttttttttttt"); // Add this for debugging


        if (isValidPassword(password)) {
            System.out.println("mmmmmmmmmmm"); // Add this for debugging
            messageLabel.setText("");

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

        }else {
            System.out.println("yyyyyyyyyyyy"); // Add this for debugging

            clear();
            messageLabel.setText("Generate a Strong Password");
            messageLabel.setTextFill(Color.RED);

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

    private void clear (){
        txtPwd.setText("");
        messageLabel.setText("");
    }

    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long, including numbers, letters, and symbols
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }


}
