package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.impl.orderdetalsboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DAO.Util.CompleteEmail;
import Edu.icet.DTO.BillFinalDetals;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.Tm.ordersdetalsTm;
import Edu.icet.DTO.item;
import Edu.icet.DTO.orderdetailsDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private static double serviceincludeBill ;

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
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        orderDetailsDto.setDate(formattedDate);

        // Step 2: Retrieve values from text fields and set them in the orderDetailsDto object
        orderDetailsDto.setProductname(comboItemName.getValue());
        orderDetailsDto.setPrise(Double.parseDouble(txtprise.getText()));
       // orderDetailsDto.setDate(txtdate.getText()); // Modify this according to your needs
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
        serviceincludeBill = TotalBill;
        Totalcalvalue.setText(String.valueOf(TotalBill));
    }

    @FXML
    void btnComplteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        Orderbo bo = new Orderboimpl();
        OrderDto dto = new OrderDto();
        dto.setOrderId(comboOrderId.getValue());
        dto.setName(nametxt.getText());
        dto.setEmail(txtemail.getText());
        System.out.println(txtemail.getText());
        dto.setPhonenumber(txtcantac.getText());
        dto.setType(String.valueOf(Totalcalvalue));
        dto.setDate(currentDate);
        dto.setStatus("Completed bring your items");
        dto.setNote(txtdate.getText());
        String pass = "completed";
        bo.updateByCompele(dto, pass);
        CompleteEmail.sendReceiptComplted(dto,serviceincludeBill);

        // final bill table
        BillFinalDetals billdto = new BillFinalDetals();
        billdto.setOrderid(comboOrderId.getValue());
        billdto.setEmail(txtemail.getText());
        billdto.setPhonenumber(txtcantac.getText());
        billdto.setTotalBillprise(serviceincludeBill);
        orderdetalsbo boset = new orderdetalsboimpl();
        boset.SaveFinalBillDetails(billdto);

        // Clear all text fields
//        // comboOrderId.setValue(null);
        nametxt.setText("");
        txtcantac.setText("");
        txtemail.setText("");
        txtdate.setText("");
//        //   comboItemName.setValue(null);
        txtqun.setText("");
        txtprise.setText("");
        quntp.setText("");
        txtservisecharge.setText("");
        Totalcalvalue.setText("");
        orderDetailsList.clear();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        // Close the current stage
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/HomeFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Home Page");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws MessagingException {
        String productName = comboItemName.getValue();
        itembo bo = new itemboimpl();
        item dto = bo.searchItemNameCheck(productName);
        System.out.println("prise aka"+dto.getPrise());
        txtprise.setText(String.valueOf(dto.getPrise()));
    }

    @FXML
    void cmbOrderOnAction(ActionEvent event) throws MessagingException {
        orderdetailsDto orderDetailsDto = new orderdetailsDto();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        String orderId = comboOrderId.getValue();
        Orderbo bo = new Orderboimpl();
        OrderDto dto = bo.searchByOrderdetails(orderId);
        nametxt.setText(dto.getName());
        txtcantac.setText(dto.getPhonenumber());
        txtemail.setText(dto.getEmail());
        System.out.println(dto.getDate());
        txtdate.setText(formattedDate);
        type = dto.getType();
    }

    public void DashOnAction(ActionEvent actionEvent) throws IOException {

    }
    @FXML
    void btnDashOnAction(ActionEvent event) throws IOException {
        LoginFromController obj = new LoginFromController();
        String logintoinde = obj.getLogin();
        System.out.println(obj.getLogin());
        System.out.println("Login Type: " + logintoinde);

        if(logintoinde.equals("Employee")){

            // Close the current stage
            Stage currentStage = (Stage) rootNode.getScene().getWindow();
            currentStage.close();

            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeDashboadFrom.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage(); // Create a new stage for the Employee Dashboard
            stage.setTitle("Employee Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();


        } else if (logintoinde.equals("Admin")) {

            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/ADMINDashboardFrom.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
//            // Close the current stage
//            Stage currentStage = (Stage) rootNode.getScene().getWindow();
//            currentStage.close();

        }
    }


    public void btnStoreOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemFromNewVerstionFrom.fxml"));
        Parent updateRoot = loader.load();
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Request View");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

        // You can close the current stage if needed
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();

    }

    public void btnRequestSOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RequestViewOpenFrom.fxml"));
        Parent updateRoot = loader.load();
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Request View");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

        // You can close the current stage if needed
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
    }
}
