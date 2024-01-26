package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.BO.custom.impl.orderdetalsboimpl;
import Edu.icet.BO.custom.orderdetalsbo;
import Edu.icet.DTO.OrderDto;
import Edu.icet.DTO.item;
import Edu.icet.db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OrderStatusFromControoler {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtOderid;

    @FXML
    private TextField txtcusname;

    @FXML
    private TextField txtcantac;

    @FXML
    private TextField odersttxt;
    @FXML
    private Label statuslabel;


    public void SearchOnAction(ActionEvent actionEvent) throws MessagingException {
        String OrderId = txtOderid.getText();
        Orderbo bocall = new Orderboimpl();
        OrderDto savedata =bocall.searchByOrderdetails(OrderId);
        System.out.println(savedata);
        txtcusname.setText(savedata.getName());
        txtcantac.setText(savedata.getPhonenumber());
        String getstatus =savedata.getStatus();
        String Assigndate = savedata.getDate();
        if(getstatus.equals("Processing")) {
            statuslabel.setText("Processing order");
            statuslabel.setTextFill(Color.BLUE);
        } else if (getstatus.equals("completed")) {
            statuslabel.setText("completed this  order");
            statuslabel.setTextFill(Color.GREEN);
        } else if (getstatus.equals("closed")) {
            statuslabel.setText("close this customer pays ");
            statuslabel.setTextFill(Color.GREEN);
        } else if (getstatus.equals("Pending")) {
            LocalDate currentDate = LocalDate.now();

            // Add a null check for Assigndate
            if (Assigndate != null && !Assigndate.isEmpty()) {
                // Convert the string date to LocalDate
                LocalDate stringDate = LocalDate.parse(Assigndate);

                // Calculate the difference in days
                long daysDifference = ChronoUnit.DAYS.between(stringDate, currentDate);

                // Determine the zone based on the difference
                if (daysDifference > 10) {
                    statuslabel.setText("Pending more than 10 days ");
                    statuslabel.setTextFill(Color.RED);
                } else if (daysDifference > 5) {
                    statuslabel.setText("Pending more than 5 days");
                    statuslabel.setTextFill(Color.ORANGE);
                } else {
                    statuslabel.setText("Pending less than 5 days");
                    statuslabel.setTextFill(Color.PINK);
                }
            } else {
                // Handle the case where Assigndate is null or empty
                statuslabel.setText("Oder place today");
                statuslabel.setTextFill(Color.RED);
            }
        }else if (getstatus == null || savedata.getOrderId() == null){
            statuslabel.setText("Order Return ");
            statuslabel.setTextFill(Color.MAROON);
        } else{
            statuslabel.setText("Close order");
            statuslabel.setTextFill(Color.RED);
        }



    }
    @FXML
    void closeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Orderbo bocall = new Orderboimpl();
        OrderDto itemdto = new OrderDto();
        String OrderId = txtOderid.getText();
        orderdetalsbo bosetd = new orderdetalsboimpl();
        itemdto.setOrderId(OrderId);
        String set = "close";
        bocall.updateByCompele(itemdto,set);
        bosetd.PayComplte(OrderId);
        try{
            // Generate and export the bill as a PDF
            generateAndExportBill(OrderId);

            // You can add any additional actions or UI updates here if needed

            // Inform the user about the successful closure
            statuslabel.setText("Order closed successfully");
            statuslabel.setTextFill(Color.GREEN);
        } catch (Exception e) {
        // Handle exceptions appropriately
        e.printStackTrace(); // You might want to log the exception or show an error message to the user
    }

    }

    private void generateAndExportBill(String orderId) {
        try {
            // Use the absolute path to load the JRXML file
            String filePath = getClass().getClassLoader().getResource("report/Bill.jrxml").getFile();
            JasperDesign jDesign = JRXmlLoader.load(new File(filePath));

            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT *\n" +
                    "FROM OrderEntityM\n" +
                    "INNER JOIN CustomerEntity ON CustomerEntity.Contactnumber = OrderEntityM.phonenumber\n" +
                    "INNER JOIN PaymentDetailsEntity ON PaymentDetailsEntity.orderid = OrderEntityM.OrderId\n" +
                    "WHERE OrderEntityM.OrderId = '" + orderId + "';\n");
            jDesign.setQuery(query);

            JasperReport compileReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());

            // Specify the absolute path for exporting the PDF file
            String directoryPath = "D:\\Panadura\\EEServiseCenter\\bill\\";
            String pdfFilePath = directoryPath + orderId + ".pdf";

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Creates parent directories if they don't exist
            }

            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace(); // You might want to log the exception or show an error message to the user
        }
    }



}
