package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DeleteFromControler {
    @FXML
    private TextField Emalitxt;


    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Email = Emalitxt.getText();
        userbo user = new Userboimpl();
        user.deleteCustomer(Email);
        showAlert("Success", "Account is Deleted");

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
