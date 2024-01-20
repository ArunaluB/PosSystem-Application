package Edu.icet.controller;

import Edu.icet.BO.custom.impl.itemboimpl;
import Edu.icet.BO.custom.itembo;
import Edu.icet.DTO.MyListener;
import Edu.icet.DTO.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFromControler implements Initializable{

    @FXML
    private AnchorPane rootNode;


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

    @FXML
    private Button updatebutton;




    private List<item> Items = new ArrayList<>();
    private Image image;

    private MyListener myListener;

    private static  String productNameUse;

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

            if (column == 2) {
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
        productNameUse = item.getProductname();
        System.out.println("product name aka"+productNameUse);
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

    public void UpdateOnAction(ActionEvent actionEvent) throws MessagingException, IOException {
        itembo bo = new itemboimpl();
        bo.setIteamname(productNameUse);
//        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ItemUpdate.fxml"));
//        Scene scene = new Scene(rootNode);
//
//        Stage stage =  (Stage) this.updatebutton.getScene().getWindow();
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/ItemUpdate.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Update Item");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();


    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnDriverOnAction(ActionEvent actionEvent) {
    }

    public void btnCarOnAction(ActionEvent actionEvent) {
    }

    public void btnBookingOnAction(ActionEvent actionEvent) {
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }

    public void btnAdminOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        // Close the current stage
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/HomeFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Home Page");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }


    public void ItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/ItemAddForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage newStage = new Stage();
        newStage.setTitle("ItemADD");
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
//        Stage stage = (Stage) this.rootNode.getScene().getWindow();
//        stage.setTitle("ItemAddFrom");
//        stage.setScene(scene);
//        stage.centerOnScreen();
    }

    public void ItemRemoveOnAction(ActionEvent actionEvent) throws IOException {
//        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/itemDeleteForm.fxml"));
//        Scene scene = new Scene(rootNode);
//        Stage stage = (Stage) this.rootNode.getScene().getWindow();
//        stage.setTitle("ItemRemove");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/itemDeleteForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage newStage = new Stage();
        newStage.setTitle("ItemRemove");
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
    }

    @FXML
    void btndashOnAction(ActionEvent event) throws IOException {
        LoginFromController obj = new LoginFromController();
        String logintoinde = obj.getLogin();
        System.out.println(obj.getLogin());
        System.out.println("Login Type: " + logintoinde);

        if(logintoinde.equals("Employee")){

            // Close the current stage
            Stage currentStage = (Stage) rootNode.getScene().getWindow();
            currentStage.close();

            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/EmployeeDashboadFrom.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage(); // Create a new stage for the Employee Dashboard
            stage.setTitle("Employee Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();


        } else if (logintoinde.equals("Admin")) {

            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/ADMINDashboardFrom.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            // Close the current stage
            Stage currentStage = (Stage) rootNode.getScene().getWindow();
            currentStage.close();

        }
    }


    public void DashOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnStoreOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemFromNewVerstionFrom.fxml"));
//        Parent updateRoot = loader.load();
//        Scene updateScene = new Scene(updateRoot);
//
//        Stage updateStage = new Stage();
//        updateStage.setTitle("Request View");
//        updateStage.setScene(updateScene);
//        updateStage.setResizable(false);
//        updateStage.show();
//
//        // You can close the current stage if needed
//        Stage currentStage = (Stage) rootNode.getScene().getWindow();
//        currentStage.close();
    }

    public void btnRequestSOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RequestViewOpenFrom.fxml"));
        Parent updateRoot = loader.load();
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Request View");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

        // You can close the current stage if needed
        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();
    }

    public void btnComplteOnAction(ActionEvent actionEvent) throws IOException {
//        // Close the current stage
//
//        FXMLLoader loader = FXMLLoader.load(getClass().getResource("/view/OrderComplteFrom.fxml"));
//        Parent updateRoot = loader.load();
//        Scene updateScene = new Scene(updateRoot);
//
//        Stage updateStage = new Stage();
//        updateStage.setTitle("Order Complte ");
//        updateStage.setScene(updateScene);
//        updateStage.setResizable(false);
//        updateStage.show();
//        // You can close the current stage if needed
//        Stage currentStage = (Stage) rootNode.getScene().getWindow();
//        currentStage.close();
        // Close the current stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderComplteFrom.fxml"));
        Parent updateRoot = loader.load();
        OrderComplteFromController orderComplteController = loader.getController();

        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Order Complete");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

        Stage currentStage = (Stage) rootNode.getScene().getWindow();
        currentStage.close();

        System.out.println("New stage opened. Closing the old stage.");
    }
}
