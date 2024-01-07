package Edu.icet.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ItemAddFormController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUName;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnAdminFormBtn;

    private Stage stage; // Declare the Stage variable

    private String imagePath;
    private String Type =null;

    @FXML
    private JFXRadioButton TypeElectronic;

    @FXML
    private JFXRadioButton TypeElectrical;

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        // Implement save functionality here
    }

    @FXML
    void uploadOnAction(ActionEvent event) {
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
    private void choics(ActionEvent actionEvent) {
        if (TypeElectronic.isSelected()) {
            Type = "Electronic";
        } else if (TypeElectrical.isSelected()) {
            Type = "Electrical";
        }

        System.out.println("Selected type: " + Type); // Add this for debugging
    }

}
