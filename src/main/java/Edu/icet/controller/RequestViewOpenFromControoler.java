package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.Tm.ordertm;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RequestViewOpenFromControoler  {

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
            getButton.setOnAction(event -> {
                try {
                    processUpdate(orderDto);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
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

    public void processUpdate(OrderDto dto) throws SQLException, ClassNotFoundException {
        Orderbo bocalled = new Orderboimpl();
        bocalled.updateItem(dto);
        // Remove the selected item from the TableView
        ordertm selectedItem = tableView.getSelectionModel().getSelectedItem();
        obListRe.remove(selectedItem);
        try {
            loadAllTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        // Show success alert
        showSuccessAlert();
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        try {
            loadAllTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("get processes successfully!");

        alert.showAndWait();
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        LoginFromController obj = new LoginFromController();
        String logintoinde = obj.getLogin();
        System.out.println(obj.getLogin());
        System.out.println("Login Type: " + logintoinde);

        if(logintoinde.equals("Employee")){
//
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



    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
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

    public void btnAdminOnAction(ActionEvent actionEvent) {

    }

    public void btnStoreOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemFromNewVerstionFrom.fxml"));
        Parent updateRoot = loader.load();
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Store");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

        // You can close the current stage if needed
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
    }

    public void btnComplteOnAction(ActionEvent actionEvent) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/OrderComplteFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);
        Stage updateStage = new Stage();
        updateStage.setTitle("Order Complte ");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
    }
}
