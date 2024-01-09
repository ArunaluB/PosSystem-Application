package Edu.icet.controller;

import Edu.icet.BO.custom.customerbo;
import Edu.icet.BO.custom.impl.customerboimpl;
import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DeleteCustomerController {

    @FXML
    private TextField phonetxt;

    @FXML
    private Button deleteOnAction;
    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String CNumber =  phonetxt.getText();
//       itembo bo = new itemboimpl();
        customerbo bo = new customerboimpl();

       bo.deleteCustomer(CNumber);
       showAlert("Success", "Customer is Deleted");

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
