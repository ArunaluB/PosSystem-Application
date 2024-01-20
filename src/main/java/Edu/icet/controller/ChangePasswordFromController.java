package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ChangePasswordFromController {
    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtConfirmNewPwd;
    public void btnResetPwdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String New = txtNewPassword.getText();
        String renew = txtConfirmNewPwd.getText();

        userbo usecalbo = new Userboimpl();

        if(usecalbo.validatepassword(New,renew)) {
            showAlert("Success", "Password is valid!");

            // Close the current stage
            Stage currentStage = (Stage) txtNewPassword.getScene().getWindow();
            currentStage.close();

            // Open a new stage for the login form
            Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/LoginFrom.fxml"));
            Scene updateScene = new Scene(updateRoot);

            Stage updateStage = new Stage();
            updateStage.setTitle("Customer Deleted From");
            updateStage.setScene(updateScene);
            updateStage.setResizable(false);
            updateStage.show();


        } else {
            showAlert("Error", "Invalid password. Please try again.");
            clearFields();
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
