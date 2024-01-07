package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DTO.itemget;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Button btnAdminFormBtn;

    @FXML
    private JFXRadioButton typeEm1;

    @FXML
    private ToggleGroup choics;

    @FXML
    private JFXRadioButton TypeEm2;
    private String Type =null;
    private String imagePath= null;

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ProductName =txtPName.getText();
        String prise = txtprise.getText();
        System.out.println("tttttttttttttttt"); // Add this for debugging

        itemget itemcallbo = new itemget();
        itemcallbo.setProductname(ProductName);
        itemcallbo.setPrice(prise);
        itemcallbo.setImgsrc(imagePath);
        itemcallbo.setType(Type);

        itembo itembo = new itemboimpl();
        itembo.saveItem(itemcallbo);

    }
    @FXML
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
                imagePath = "/img/" + selectedFile.getName();
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
