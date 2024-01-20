package Edu.icet.controller;

import Edu.icet.db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ReportFromController {

    @FXML
    private AnchorPane rootNode;





    @FXML
    void btnAnuwalDaReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnDailselReportOnAction(ActionEvent event) {

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
    void btnStoreOnAction(ActionEvent event) throws IOException {
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


    @FXML
    void btndashOnAction(ActionEvent event) throws IOException {
        LoginFromController obj = new LoginFromController();
        String logintoinde = obj.getLogDetails();
        System.out.println("logo detals tika"+logintoinde);

        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/ADMINDashboardFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        // Close the current stage
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();


    }

    @FXML
    void btnmouthdaReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewCusReportOnAction(ActionEvent event)  {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("report/CustomerDetails.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText("SELECT * FROM customerentity");
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport, //compiled report
                            null,
                            DbConnection.getInstance().getConnection() //database connection
                    );

            JFrame frame = new JFrame("Jasper Report Viewer");
            JRViewer viewer = new JRViewer(jasperPrint);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(viewer);
            frame.setSize(new Dimension(1200, 800));
            frame.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();  // Print the stack trace for debugging
            throw new RuntimeException("Error compiling/filling Jasper Report: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }


    }

    @FXML
    void btnComOnAction(ActionEvent event) throws IOException {
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

    @FXML
    void OrderOneCusOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Reportdataform.fxml"));
        Scene scene = new Scene(rootNode);

        Stage newStage = new Stage();
        newStage.setTitle("Report Customer");
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();

    }

}
