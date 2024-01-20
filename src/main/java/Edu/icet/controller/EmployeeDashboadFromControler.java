package Edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDashboadFromControler implements Initializable {



    @FXML
    private AnchorPane rootNode;

  //  private static String LogDetails;

    @FXML
    void CusAddOnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/customerReg.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Customer Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    @FXML
    void DashOnAction(ActionEvent event) {

    }

    @FXML
    void DeleteOnCusAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/DeleteCustomer.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Customer Deleted From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    @FXML
    void OrderStutesOnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/OrderStatusFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Order Status");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    @FXML
    void OrederROnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/OrderReturnFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Customer Return ");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    @FXML
    void btnComplteOnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/OrderComplteFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Order Complte ");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

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
    void btnRequestSOnAction(ActionEvent event) throws IOException {
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

    @FXML
    void btnSalaryOnAction(ActionEvent event) {

    }

    @FXML
    void btnStoreOnAction(ActionEvent event) throws IOException {
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

    @FXML
    void placeorderOnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/OderFirestStepFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Order place");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }
}
