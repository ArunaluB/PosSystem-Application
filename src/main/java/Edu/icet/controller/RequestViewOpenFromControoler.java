package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.Tm.ordertm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

public class RequestViewOpenFromControoler {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<ordertm> tableView;

    @FXML
    private TableColumn<?, ?> Orderid;

    @FXML
    private TableColumn<?, ?> colcustomer;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private TableColumn<?, ?> colnote;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colprocss;

    @FXML
    private TextField txtSearchCar;

    public final ObservableList<ordertm> obListRe = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setCellValueFactory();
        try {
            loadAllTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void setCellValueFactory() {
        // property value akata anne tm ake variable name tika
        Orderid.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colcustomer.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colprocss.setCellValueFactory(new PropertyValueFactory<>("getprocessButton"));
   }


    public void loadAllTable() throws SQLException, ClassNotFoundException {
        obListRe.clear();
        Orderbo bo = new Orderboimpl();
        List<OrderDto> dtolist = bo.loaditem();
        System.out.println("dto list aka");
        System.out.println(dtolist);
        for (OrderDto orderDto : dtolist) {
            Button getButton = new Button("Getprocess");
            getButton.setStyle("-fx-background-color: white; -fx-text-fill: green; -fx-font-weight: bold;");
            ordertm tabledatils = new ordertm();
            tabledatils.setOrderId(orderDto.getOrderId());
            tabledatils.setName(orderDto.getName());
            tabledatils.setType(orderDto.getType());
            tabledatils.setNote(orderDto.getNote());
            tabledatils.setDate(orderDto.getDate());
            tabledatils.setGetprocessButton(getButton);
            obListRe.add(tabledatils);
        }

        // Set items to the TableView
        tableView.setItems(obListRe);
    }

    public void btnADDCarOnAction(ActionEvent actionEvent) {
    }

    public void txtSEARCHOnAction(ActionEvent actionEvent) {
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnDriverOnAction(ActionEvent actionEvent) {
    }

    public void btnBookingOnAction(ActionEvent actionEvent) {
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }

    public void btnAdminOnAction(ActionEvent actionEvent) {
    }
}
