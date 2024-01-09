package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DTO.MyListener;
import Edu.icet.DTO.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFromControler implements Initializable{

    @FXML
    private VBox Choseitem;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label itemNameLable;

    @FXML
    private Label itemPriceLabel;

    @FXML
    private ImageView itemImg;

    @FXML
    private ScrollPane scrol;

    @FXML
    private GridPane grid;

    @FXML
    private Label typelabel;

    @FXML
    private Label avalabeltxt;



    private List<item> Items = new ArrayList<>();
    private Image image;

    private MyListener myListener;

   private List<item> getData() throws SQLException, ClassNotFoundException {
       List<item> itemsnew = new ArrayList<>();
       itembo bo = new itemboimpl();
       List<item> liveconnect = bo.loaditem();
       System.out.println(liveconnect.get(0).getProductname());
       System.out.println(liveconnect.get(0).getImgsrc());

//
//       // itemsnew.addAll(liveconnect);
       item itemobj;
      for(int i=0;i<10;i++){
           itemobj = new item();
           itemobj.setProductname("kiwi");

//          System.out.println(liveconnect.get(i).getImgsrc());
           itemobj.setPrise(40);
           itemobj.setImgsrc("/img/com.jpg");
           itemobj.setColor("FFB605");
           itemsnew.add(itemobj);

       }
      String p = "/img/PostMan.jpg";
       itemobj = new item();
       itemobj.setProductname("adarii");
       itemobj.setPrise(40);
       itemobj.setImgsrc(p);
       System.out.println(p);
       itemobj.setColor("1273de");
       itemsnew.add(itemobj);
//

       for (int i = 0; i < liveconnect.size() ; i++) {
           itemobj =new item() ;
           itemobj.setProductname(liveconnect.get(i).getProductname());
           itemobj.setPrise(liveconnect.get(i).getPrise());
           itemobj.setImgsrc(liveconnect.get(i).getImgsrc());
           itemobj.setColor(liveconnect.get(i).getColor());
           itemobj.setAvalable(liveconnect.get(i).getAvalable());
           itemsnew.add(itemobj);

       }
//       itemobj =new item() ;
//       itemobj.setProductname(liveconnect.get(0).getProductname());
//       itemobj.setPrise(liveconnect.get(0).getPrise());
//       itemobj.setImgsrc(liveconnect.get(0).getImgsrc());
//       itemobj.setColor(liveconnect.get(0).getColor());
//       itemsnew.add(itemobj);
            return itemsnew;
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Items.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (Items.size() > 0) {
            setChosenFruit(Items.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(item item) {
                    setChosenFruit(item);
                }

            };
        }
        int column = 0;
        int row = 1;
        for (int i = 0; i < Items.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/item.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            itemcontrooler itemcontrooler =  fxmlLoader.getController();
            itemcontrooler.setData(Items.get(i),myListener);

            if (column == 3) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }


    }
    private void setChosenFruit(item item) {
        itemNameLable.setText(item.getProductname());
        itemPriceLabel.setText(String.valueOf(item.getPrise()));
        image = new Image(getClass().getResourceAsStream(item.getImgsrc()));
        itemImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + item.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
        String code = item.getColor();
       if(code.equals("FFB605")){
           typelabel.setText("Electronic");
        } else {
            typelabel.setText("Electrical");
        }
        String avalable= item.getAvalable();
        System.out.println("a"+avalable);

        if (avalable != null && avalable.equals("Yes")) {
            avalabeltxt.setText("In Available stock");
            avalabeltxt.setTextFill(Color.GREEN);
        } else {
            // Handle the case when avalable is null
            if (avalable == null) {
                avalabeltxt.setText("Avalable value is null");
                avalabeltxt.setTextFill(Color.RED);
            } else {
                avalabeltxt.setText("Out of Available stock");
                avalabeltxt.setTextFill(Color.RED);
            }
        }


//        if(avalable.equals("Yes")){
//            avalabeltxt.setText("In Avalable stoke");
//            avalabeltxt.setTextFill(Color.GREEN);
//
//        }else {
//            avalabeltxt.setText("Out Avalable stoke");
//            avalabeltxt.setTextFill(Color.RED);
//
//        }
//       // typelabel.setText(item.ge);

    }

    public void UpdateOnAction(ActionEvent actionEvent) {

    }
}
