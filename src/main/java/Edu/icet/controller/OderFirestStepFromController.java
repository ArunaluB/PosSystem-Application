package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.customerbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.BO.custom.impl.customerboimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class OderFirestStepFromController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtphone;
    public void OderOnAction(ActionEvent actionEvent) throws MessagingException, IOException, SQLException, ClassNotFoundException {
        String phonenumber = txtphone.getText();
        System.out.println(phonenumber);
        customerbo bocallobj = new customerboimpl();
        String name = bocallobj.searchByCustomerDetails(phonenumber);
        Orderbo bocall = new Orderboimpl();
        String id = bocall.generateId();
        bocall.setidmethod(id);
        System.out.println(id);
        if (name == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Customer Not Registered");
            alert.setContentText("This Customer is not registered. Please ask your customer details.");
            alert.showAndWait();
        }

        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/OderPlaceForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setTitle("FogetPassword");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
