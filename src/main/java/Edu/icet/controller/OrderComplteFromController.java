package Edu.icet.controller;

import Edu.icet.Entity.OrderCompositeKey;
import Edu.icet.Entity.OrderdetailsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class OrderComplteFromController {


    @FXML
    private AnchorPane rootNode;

    @FXML
    private Pane subAnchorPane;

    @FXML
    private ComboBox<?> comboOrderId;

    @FXML
    private Label nametxt;

    @FXML
    private Label txtphone;

    @FXML
    private Label txtemail;

    @FXML
    private Label txtdate;

    @FXML
    private ComboBox<?> comboItemName;

    @FXML
    private Label priseunittxt;

    @FXML
    private TextField quintitytextfeils;

    @FXML
    private Label totalitem;

    @FXML
    private TextField txtservisecharge;

    @FXML
    private Label lblBookingId1;

    @FXML
    private Label Totalcalvalue;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colCarNo;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDrId;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colPickUpDate;

    @FXML
    private TableColumn<?, ?> colDays;

    @FXML
    void btnAdditemOnAction(ActionEvent event) {
        OrderdetailsEntity obj = new OrderdetailsEntity();
        obj.setQuantity(10);
        obj.setSubtotal(200);

    }

    @FXML
    void btnCalTotalOnAction(ActionEvent event) {

//        OrderCompositeKey compositeKey = new OrderCompositeKey("yourOrderId", 123); // Sample data
//        OrderdetailsEntity orderDetails = new OrderdetailsEntity(compositeKey, 5, 100.0f); // Sample data

    }

    @FXML
    void btnComplteOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

    @FXML
    void cmbOrderOnAction(ActionEvent event) {

    }
}
