package Edu.icet.controller;

import Edu.icet.DTO.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Edu.icet.DTO.item;

public class itemcontrooler {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private Label typename;

    private item item;

    private MyListener myListener;

    @FXML
    private Label avlable;


    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(item);
    }

    public void setData(item item, MyListener myListener) {
        this.item= item;
        this.myListener=myListener;
        nameLabel.setText(item.getProductname());
        priceLable.setText(String.valueOf(item.getPrise()));
        Image image = new Image(getClass().getResourceAsStream(item.getImgsrc()));
        img.setImage(image);
       // avlable.setText(item.getAvalable());



    }

}
