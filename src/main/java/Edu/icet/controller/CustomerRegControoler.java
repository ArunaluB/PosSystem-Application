package Edu.icet.controller;

import Edu.icet.BO.custom.customerbo;
import Edu.icet.BO.custom.impl.customerboimpl;
import Edu.icet.DTO.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegControoler {


    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtCName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtConN;

    @FXML
    private Label errorlabel;
    @FXML
    private Label label2;


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        errorlabel.setText("");
        label2.setText("");
        String customer = txtCName.getText();
        String email = txtEmail.getText();
        String Contactnumber =txtConN.getText();
        boolean isValidemail = isValidEmail(email);
        boolean  isValidPhone = isValidPhoneNumber(Contactnumber);

        if (isValidemail && isValidPhone) {
            CustomerDto dto = new CustomerDto();
            dto.setCustomerName(customer);
            dto.setEmail(email);
            dto.setContactnumber(Contactnumber);

            customerbo calledbo = new customerboimpl();
            calledbo.saveCustomer(dto);
            System.out.println(dto);

        }else {
            if (!isValidemail) {
                errorlabel.setText("Invalid email format");
                txtEmail.setText("");
            }
            if (!isValidPhone) {
                label2.setText("Invalid phone number format");
                txtConN.setText("");
            }
        }



//
//        CustomerDto dto = new CustomerDto();
//        dto.setCustomerName(customer);
//        dto.setEmail(email);
//        dto.setContactnumber(Contactnumber);
//
//        customerbo calledbo = new customerboimpl();
//        calledbo.saveCustomer(dto);
//        System.out.println(dto);

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Validate phone number format: 10 digits and starts with 0
        String phoneRegex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
