package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.impl.orderdetalsboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.Tm.ordersdetalsTm;
import Edu.icet.DTO.item;
import Edu.icet.DTO.orderdetailsDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

public class OrderComplteFromController {

    // Declare an ObservableList to store data for the TableView
    private ObservableList<ordersdetalsTm> orderDetailsList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Pane subAnchorPane;

    @FXML
    private ComboBox<String> comboOrderId;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField txtcantac;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtdate;

    @FXML
    private ComboBox<String> comboItemName;

    @FXML
    private TextField txtqun;

    @FXML
    private TextField txtprise;

    @FXML
    private TextField quntp;

    @FXML
    private TextField txtservisecharge;

    @FXML
    private Label lblBookingId1;

    @FXML
    private Label Totalcalvalue;

    @FXML
    private TableView<ordersdetalsTm> tableView;

    @FXML
    private TableColumn<?, ?> colITname;

    @FXML
    private TableColumn<?, ?> colUnitPrise;

    @FXML
    private TableColumn<?, ?> colquan;

    @FXML
    private TableColumn<?, ?> colAmount;
    private static  String type;
    private static double Amount;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadorderdetals();
        loaditemdata();
        // Set the TableView items to the observable list
        tableView.setItems(orderDetailsList);
    }
    private void setCellValueFactory(){
        colITname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colUnitPrise.setCellValueFactory(new PropertyValueFactory<>("UnitPrise"));
        colquan.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void loadorderdetals() throws SQLException, ClassNotFoundException {
        Orderbo bo = new Orderboimpl();
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<OrderDto> orderDtoList =bo.alldata();
        for (OrderDto dto:orderDtoList){
            obList.add(dto.getOrderId());
        }
        comboOrderId.setItems(obList);
    }
    public void loaditemdata() throws SQLException, ClassNotFoundException {
        itembo bo = new itemboimpl();
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<item> itemList = bo.loaditem();
        for(item dto :itemList){
            obList.add(dto.getProductname());
        }
        comboItemName.setItems(obList);
    }


    @FXML
    void btncallitemOnAction(ActionEvent event) {
        int Qut = Integer.parseInt(txtqun.getText());
        double prise = Double.parseDouble(txtprise.getText());
        double callprise = prise*Qut;
        Amount+= callprise;
        quntp.setText(String.valueOf(callprise));
    }


    @FXML
    void btnAdditemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Step 1: Create an instance of orderdetailsDto
        orderdetailsDto orderDetailsDto = new orderdetailsDto();

        // Step 2: Retrieve values from text fields and set them in the orderDetailsDto object
        orderDetailsDto.setProductname(comboItemName.getValue());
        orderDetailsDto.setPrise(Double.parseDouble(txtprise.getText()));
        orderDetailsDto.setDate(txtdate.getText()); // Modify this according to your needs
        orderDetailsDto.setOrderId(comboOrderId.getValue());
        orderDetailsDto.setEmail(txtemail.getText());
        orderDetailsDto.setPhonenumber(txtcantac.getText());
        orderDetailsDto.setQuantity(Integer.parseInt(txtqun.getText()));
        orderDetailsDto.setSubtotal(Float.parseFloat(quntp.getText()));
        orderDetailsDto.setId(0);
        orderDetailsDto.setType(type);
        orderdetalsbo bo = new orderdetalsboimpl();
        bo.saveOrder(orderDetailsDto);
        ordersdetalsTm tmcalled = new ordersdetalsTm();
        tmcalled.setItemname(comboItemName.getValue());
        tmcalled.setUnitPrise(Double.parseDouble(txtprise.getText()));
        tmcalled.setQuantity(Integer.parseInt(txtqun.getText()));
        tmcalled.setAmount(Double.parseDouble(quntp.getText()));
        orderDetailsList.add(tmcalled);

    }

    @FXML
    void btnCalTotalOnAction(ActionEvent event) {
        double Servisecharge = Double.parseDouble(txtservisecharge.getText());
        double TotalBill=Servisecharge+Amount;
        Totalcalvalue.setText(String.valueOf(TotalBill));
    }

    @FXML
    void btnComplteOnAction(ActionEvent event) {

//        // Clear all text fields
//        comboOrderId.setValue(null);
//        nametxt.clear();
//        txtcantac.clear();
//        txtemail.clear();
//        txtdate.clear();
//        comboItemName.setValue(null);
//        txtqun.clear();
//        txtprise.clear();
//        quntp.clear();
//        txtservisecharge.clear();
//        Totalcalvalue.setText("");
          orderDetailsList.clear();

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
    void cmbItemOnAction(ActionEvent event) throws MessagingException {
        String productName = comboItemName.getValue();
        itembo bo = new itemboimpl();
        item dto = bo.searchItemNameCheck(productName);
        txtprise.setText(String.valueOf(dto.getPrise()));
    }

    @FXML
    void cmbOrderOnAction(ActionEvent event) throws MessagingException {
        String orderId = comboOrderId.getValue();
        Orderbo bo = new Orderboimpl();
        OrderDto dto = bo.searchByOrderdetails(orderId);
        nametxt.setText(dto.getName());
        txtcantac.setText(dto.getPhonenumber());
        txtemail.setText(dto.getEmail());
        System.out.println(dto.getDate());
        txtdate.setText(dto.getNote());
        type = dto.getType();
    }
}
