package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DTO.ItemDto;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ItemAddFormConrtoller {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPName;



    @FXML
    private TextField txtprise;

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
    private String Type =null;
    private String imagePath= null;

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ProductName =txtPName.getText();
        String prise = txPrice.getText();
        ItemDto dtoobj = new ItemDto();
        try{
            double priseDouble = Double.parseDouble(prise);
            System.out.println(prise);
            System.out.println("tttttttttttttttt"); // Add this for debugging


            dtoobj.setProductname(ProductName);
            dtoobj.setPrise(prise);
            dtoobj.setImgsrc(imagePath);
            dtoobj.setType(Type);
        }catch (NumberFormatException e) {

            System.out.println("Invalid price input. Please enter a valid number.");


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number for the price.");

            alert.showAndWait();
            txPrice.setText("");

        }
//        System.out.println(prise);
//        System.out.println("tttttttttttttttt"); // Add this for debugging
//
//        ItemDto dtoobj = new ItemDto();
//        dtoobj.setProductname(ProductName);
//        dtoobj.setPrise(prise);
//        dtoobj.setImgsrc(imagePath);
//        dtoobj.setType(Type);

//        System.out.println(dtoobj);
//        System.out.println(dtoobj.getProductname());
//        System.out.println(imagePath);
//        System.out.println(Type);
//        System.out.println(imagePath);
//        System.out.println(Type);

        itembo calbomethod = new itemboimpl();
        calbomethod.saveItem(dtoobj);



//        calbomethod.saveItem(dtoobj);
//        if(calbomethod.saveItem(dtoobj)){
//            System.out.println("Error ");
//        }



//        itemget itemcallbo = new itemget();
//        itemcallbo.setProductname(ProductName);
//        itemcallbo.setPrice(prise);
//        itemcallbo.setImgsrc(imagePath);
//        itemcallbo.setType(Type);
//
//        itembo itembo = new itemboimpl();
//        itembo.saveItem(itemcallbo);
        System.out.println("tttttttttttttttt"); // Add this for debugging
    }

    public void uploadOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                // Save the image file to the specified package
                saveImage(selectedFile);

                // Save the image path to the database
                imagePath = "/UploadImg/" + selectedFile.getName();
                System.out.println(imagePath);
                // saveImageEntityToDatabase("/img/" + selectedFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No image file has been selected");
        }
    }


    private void saveImage(File file) throws IOException {
        // Specify the directory where you want to save the images
        String imagePackagePath = "src/main/resources/UploadImg";
        File destinationFile = new File(imagePackagePath, file.getName());

        // Copy the selected image file to the specified package
        FileUtils.copyFile(file, destinationFile);
    }

    @FXML
    private String choics(ActionEvent actionEvent) {

        if(typeEm1.isSelected()) {
            Type = "Electronic";
        } else if (TypeEm2.isSelected()) {
            Type = "Electrical";
        }

        System.out.println("Selected type: " + Type); // Add this for debugging

        return Type;
    }
}
