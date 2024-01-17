package Edu.icet.controller;

import Edu.icet.Entity.OrderReportData;
import Edu.icet.db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportdataformControler {
    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Button deleteOnAction;

    public void PrintOnAction(ActionEvent actionEvent) throws IOException {
        String contactNumber = txtPhoneNumber.getText();
        generateReport(contactNumber);
    }

    public static void generateReport(String contactNumber) {
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();

            String sqlQuery = "SELECT o.OrderId, o.Productname, o.date, o.subtotal " +
                    "FROM OrderEntityM o " +
                    "JOIN FinalBillRecodeEntity f ON o.OrderId = f.orderid " +
                    "WHERE o.phonenumber = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, contactNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<OrderReportData> reportDataList = new ArrayList<>();

            while (resultSet.next()) {
                OrderReportData orderReportData = new OrderReportData(
                        resultSet.getString("OrderId"),
                        resultSet.getString("Productname"),
                        resultSet.getString("date"),
                        resultSet.getFloat("subtotal")
                );
                reportDataList.add(orderReportData);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/report/CustomerDetails.jrxml");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDataList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ContactNumber", contactNumber);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Change the export path to your desired location
            String exportPath = "src/main/resources/report/CustomerDetails_" + contactNumber + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, exportPath);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
