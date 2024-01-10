package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DAO.Custom.Itemdao;
import Edu.icet.DAO.Custom.impl.Itemdaoimpl;
import Edu.icet.DTO.item;
import Edu.icet.Entity.ItemEntity;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemUpdateControoler implements Initializable {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPName;

    @FXML
    private TextField txPrice;

    @FXML
    private Button btnAdminFormBtn;

    @FXML
    private JFXRadioButton typeEm1;

    @FXML
    private ToggleGroup choics;

    @FXML
    private JFXRadioButton TypeEm2;

    private String itemname ;

    private String Type =null;

    private String itemnn;
    private double priseDouble;
    private Itemdao itemdao =new Itemdaoimpl();

    public void setdata(String itemname){
        this.itemname=itemname;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Prise =txPrice.getText();
        try{
            priseDouble = Double.parseDouble(Prise);
            System.out.println(Prise);
            System.out.println("tttttttttttttttt"); // Add this for debugging


        }catch (NumberFormatException e) {

            System.out.println("Invalid price input. Please enter a valid number.");


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number for the price.");

            alert.showAndWait();
            txPrice.setText("");

        }
        itembo itembocall = new itemboimpl();
        itembocall.updateItem(itemnn,priseDouble,Type);

    }
    @FXML
    private String choics(ActionEvent actionEvent) {

        if (typeEm1.isSelected()) {
            Type = "Yes";
        } else if (TypeEm2.isSelected()) {
            Type = "No";
        }

        System.out.println("Selected type: " + Type); // Add this for debugging

        return Type;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          System.out.println(getdata());
          ItemEntity entity = itemdao.getItemByProductname(getdata());
          txtPName.setText(entity.getProductname());
          txtPName.setEditable(false);
          itemnn =entity.getProductname();
          txPrice.setText(String.valueOf(entity.getPrise()));



//        itembo bo = new itemboimpl();
//        String itemname = bo.getd();
//        System.out.println(itemname);
//        ItemEntity entity = itemdao.getItemByProductname(itemname);
//        txtPName.setText(entity.getProductname());

    }

    private String getdata(){
        itembo bo = new itemboimpl();
        return bo.getd();
    }

    public void uploadOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    }
}
