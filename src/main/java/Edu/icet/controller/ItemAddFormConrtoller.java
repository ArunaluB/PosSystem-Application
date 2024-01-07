package Edu.icet.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

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

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
    }
    @FXML
    public void uploadOnAction(ActionEvent actionEvent) {
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
