package Edu.icet.controller;

import Edu.icet.BO.custom.impl.Userboimpl;
import Edu.icet.BO.custom.userbo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginFromController {
    private userbo bocallcall = new Userboimpl();

    @FXML
    private TextField textemail;

    @FXML
    private TextField txtpass;

    @FXML
    private Label errorlabel;

    @FXML
    private AnchorPane rootNode;

    public static String type;

    public static String LogDetails;
    public void StartOnAction(ActionEvent actionEvent) throws IOException {
        String email = textemail.getText();
        String password = txtpass.getText();
        bocallcall.searchUser(email,password);
        System.out.println("ewrayyyyyyyyyyyyyyyyyyy");
        System.out.println("Type check"+type);
        if(type.equals("Employee")){
            LogDetails = "Employee";
            // Close the current stage
            Stage currentStage = (Stage) rootNode.getScene().getWindow();
            currentStage.close();

            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeDashboadFrom.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage(); // Create a new stage for the Employee Dashboard
            stage.setTitle("Employee Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

        } else if (type.equalsIgnoreCase("admin")) {
            System.out.println("dan ckeck aka");
            LogDetails = "Admin";
            // Close the current stage
            Stage currentStage = (Stage) rootNode.getScene().getWindow();
            currentStage.close();

            Parent adminRootNode = FXMLLoader.load(getClass().getResource("/view/ADMINDashboardFrom.fxml"));
            Scene adminScene = new Scene(adminRootNode);
            Stage adminStage = new Stage(); // Create a new stage for the Admin Dashboard
            adminStage.setTitle("Admin Dashboard");
            adminStage.setScene(adminScene);
            adminStage.centerOnScreen();
            adminStage.setResizable(false);
            adminStage.show();
        }



    }
    public String getLogin() {
        return type;
    }

    public void fogetOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/FogetEmailFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("FogetPassword");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    public void setdata(String typevo) throws IOException {
        type =typevo;
        System.out.println("Controoler case"+type);
    }

    public String getLogDetails(){
        return LogDetails;
    }



//    public void EmployeeDashGo() throws IOException {
//        try {
//            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeDashboadFrom.fxml"));
//            Scene scene = new Scene(rootNode);
//            Stage stage = (Stage) this.rootNode.getScene().getWindow();
//            stage.setTitle("Dashboard Form");
//            stage.setScene(scene);
//            stage.centerOnScreen();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception (e.g., show an error message)
//        }
//    }
//
//    public void AdminDashGo() {
//
//    }
}
