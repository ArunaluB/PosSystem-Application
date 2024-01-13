package Edu.icet.controller;

import Edu.icet.BO.custom.Orderbo;
import Edu.icet.BO.custom.impl.Orderboimpl;
import Edu.icet.DTO.OrderDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.mail.MessagingException;
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
            statuslabel.setTextFill(Color.YELLOW);
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
                statuslabel.setTextFill(Color.PINK);
            }
        }


    }
}
