package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.customerbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.BO.custom.impl.customerboimpl;
import Edu.icet.DAO.Custom.customerdao;
import Edu.icet.DAO.Custom.impl.Customerdaoimpl;
import Edu.icet.DTO.OrderDto;
import Edu.icet.Entity.CustomerEntity;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class OderPlaceFormControoler implements Initializable {



    @FXML
    private TextField txtod;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtphone;

    @FXML
    private JFXRadioButton typeEm;

    @FXML
    private ToggleGroup choics;

    @FXML
    private JFXRadioButton TypeAD;

    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtdate;

    @FXML
    private RadioButton typeele;

    @FXML
    private RadioButton TypeEler;

    private String Type =null;

    @FXML
    private AnchorPane root;
    public void placeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String getid = txtod.getText();
        String getName =txtname.getText();
        String getEmail = txtemail.getText();
        String getphone = txtphone.getText();
        String getnote =txtdes.getText();
        String getdate =txtdate.getText();

        System.out.println("data tika gaththa ");

        OrderDto dto = new OrderDto();
        dto.setOrderId(getid);
        dto.setName(getName);
        dto.setEmail(getEmail);
        dto.setPhonenumber(getphone);
        dto.setDate(getdate);
        dto.setNote(getnote);
        dto.setType(Type);
        dto.setStatus("Pending");

        System.out.println(dto);
        Orderbo callorderbo = new Orderboimpl();
        callorderbo.saveOrder(dto);
        showSuccessAlert();
        closeForm();


    }

    @FXML
    private String choics(ActionEvent actionEvent) {

        if (typeele.isSelected()) {
            Type = "Electronic";
        } else if (TypeEler.isSelected()) {
            Type = "Electrical";
        }

        System.out.println("Selected type: " + Type); // Add this for debugging

        return Type;
    }

    private String phonenumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerbo callbo = new customerboimpl();
        phonenumber = callbo.getphonenumber();
        customerdao daocall = new Customerdaoimpl();
        CustomerEntity entity = daocall.getItemByPhoneNumber(phonenumber);
        System.out.println(entity);
        txtname.setText(entity.getCustomerName());
        txtname.setEditable(false);
        txtemail.setText(entity.getEmail());
        txtemail.setEditable(false);
        txtphone.setText(entity.getContactnumber());
        txtphone.setEditable(false);

        // Get the current date and format it
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        txtdate.setEditable(false);
        txtdate.setText(currentDate);

        // oderid set
        Orderbo callorderbo = new Orderboimpl();
        String saveid = callorderbo.getorderid();
        txtod.setText(saveid);
        txtod.setEditable(false);

    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Order saved successfully!");

        alert.showAndWait();
    }

    private void closeForm() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/OderPlaceForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setTitle("FogetPassword");
        stage.setScene(scene);
        stage.centerOnScreen();
    }



}
