package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ChangePasswordFromController {
    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtConfirmNewPwd;
    public void btnResetPwdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String New = txtNewPassword.getText();
        String renew = txtConfirmNewPwd.getText();

        userbo usecalbo = new Userboimpl();

        if(usecalbo.validatepassword(New,renew)) {
            showAlert("Success", "Password is valid!");
        } else {
            showAlert("Error", "Invalid password. Please try again.");
        }





    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void clearFields() {
        txtConfirmNewPwd.setText("");
        txtNewPassword.setText("");
    }

}
