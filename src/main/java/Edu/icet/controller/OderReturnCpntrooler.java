package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class OderReturnCpntrooler {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtphone;
    public void OderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String id = txtphone.getText();
        Orderbo bo = new Orderboimpl();
        bo.deleteOrder(id);
        showSuccessAlert();
        closeForm();

    }
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Order Deleted successfully!");

        alert.showAndWait();
    }

    private void closeForm() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeDashboadFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setTitle("FogetPassword");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
