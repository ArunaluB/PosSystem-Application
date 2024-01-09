package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ItemDeleteFormControler {

    @FXML
    private TextField txtname;

    @FXML
    private Button deleteOnAction;

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ItemName = txtname.getText();
        itembo bo = new itemboimpl();
        bo.deleteitem(ItemName);
        showAlert("Success", "item is Deleted");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
