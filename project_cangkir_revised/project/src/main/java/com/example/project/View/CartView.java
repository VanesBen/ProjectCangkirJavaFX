package com.example.project.View;

import com.example.project.Controller.Connect;
import com.example.project.Model.Cart;
import com.example.project.Model.Courier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.labs.scene.control.window.Window;

public class CartView {

    protected Cart selectedItemCart;

    protected BorderPane bp;
    protected GridPane gp,pr, st;
    public static Scene scene;
    protected FlowPane fp ,fl;
    protected VBox vb ,menuV;


    protected MenuBar menuBar;
    protected MenuItem menuHome, menuCart, menuLogout;
    protected Menu menu;

    protected ComboBox<Courier> courierBox = new ComboBox<Courier>();

    protected Spinner<Integer> nameBox = new Spinner<Integer>();

    protected TableView<Cart> cartTableView;
    protected TableColumn<Cart,String> cupNameCol;
    protected TableColumn<Cart,Integer> cupPriceCol;
    protected TableColumn<Cart,Integer> quantityCol;
    protected TableColumn<Cart,Integer> totalCol;
    protected ObservableList<Cart> cartData;
    protected ObservableList<Courier> courierData;

    protected Connect connect = Connect.getInstance();

    protected Label cartL, deleteLbl,Price, courierLbl, courierPLbl;
    protected Button checkoutBtn, deleteBtn;

    protected CheckBox insurance;


//    POP UP Component
    protected Stage popUpStage;
    protected Group root;
    protected Scene popUpScene;

    protected Window window;
    protected Label textWindow ;
    protected GridPane gpBtn;
    protected VBox vbContent;

    protected Button yesBtn;
    protected Button noBtn;
//    END POP UP COMPONENT

    public static Scene getCartScene() {
        return scene;
    }

    protected void initiate() {

        bp = new BorderPane();
        gp = new GridPane();
        cartL = new Label("CartView");

        cartTableView = new TableView<Cart>();
        cartData = FXCollections.observableArrayList();
        courierData = FXCollections.observableArrayList();

        cupNameCol = new TableColumn<>("Cup Name");
        cupNameCol.setMinWidth(100);
        cupNameCol.setCellValueFactory(cellData -> cellData.getValue().cupNameProperty());

        cupPriceCol = new TableColumn<>("Cup Price");
        cupPriceCol.setMinWidth(100);
        cupPriceCol.setCellValueFactory(cellData -> cellData.getValue().cupPriceProperty().asObject());

        quantityCol = new TableColumn<>("Quantity");
        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        totalCol = new TableColumn<>("Total");
        totalCol.setMinWidth(100);
        totalCol.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

//        cartData.add(new Cart("CupKaca",10000,10));


        deleteLbl = new Label("Delete Item");
        deleteBtn = new Button("Delete Item");

        courierLbl = new Label("Courier");
        courierPLbl = new Label("Courier Price : ");
        insurance = new CheckBox("Use Delivery Insurance");


        Price = new Label("Total Price : ");
        checkoutBtn = new Button("Checkout");
        scene = new Scene(bp, 1000, 800);
        fp = new FlowPane();
        pr = new GridPane();
        vb = new VBox();
        st = new GridPane();
        fl = new FlowPane();

        menuBar = new MenuBar();
        menuHome = new MenuItem("Home");
        menuCart = new MenuItem("Cart");
        menuLogout = new MenuItem("Log Out");
        menu = new Menu("Menu");
        nameBox = new Spinner();


//        Componnent POP UP
        popUpStage = new Stage();
        root = new Group();
        window = new Window("Checkout Confirmation");
        textWindow = new Label("Are you sure want to purchase?");
        yesBtn = new Button("Yes");
        noBtn = new Button("No");
        gpBtn  = new GridPane();
        vbContent = new VBox();
        popUpScene = new Scene(root,500,500);
//        END POP UP COMPONENT
    }



    protected void addComponent() {

        menu.getItems().add(menuHome);
        menu.getItems().add(menuCart);
        menu.getItems().add(menuLogout);
        menuBar.getMenus().add(menu);
        menuV = new VBox(menuBar);


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        nameBox.setValueFactory(valueFactory);


        pr.add(deleteLbl, 0, 0);
        pr.add(Price, 1, 0);
        vb.getChildren().addAll(deleteLbl);
        vb.getChildren().addAll(deleteBtn);
        vb.getChildren().addAll(courierLbl);
        vb.getChildren().addAll(courierBox);
        vb.getChildren().addAll(courierPLbl);
        vb.getChildren().addAll(insurance);
        vb.getChildren().addAll(Price);
        vb.getChildren().addAll(checkoutBtn);
        gp.add(cartL, 0, 0);
        gp.add(cartTableView, 0, 1);
        gp.add(vb, 1, 1);

        fp.getChildren().add(gp);

        fp.setAlignment(Pos.BOTTOM_LEFT);



        gp.setMargin(cartL, new Insets(0,0,-20,20));
        gp.setMargin(cartTableView, new Insets(20,0,20,20));
        cartTableView.getColumns().addAll(cupNameCol,cupPriceCol,quantityCol,totalCol);
        cartTableView.setItems(cartData);
        courierBox.setItems(courierData);

//        Courier newCourier = new Courier("sss00","JTK",1000);
//        courierData.add(newCourier);

        courierBox.setConverter(new StringConverter<Courier>() {
            @Override
            public String toString(Courier courier) {
                return (courier != null) ? courier.getCourierName() : null;
            }

            @Override
            public Courier fromString(String string) {
                //convert back to string
                return null;
            }
        });

        gp.setMargin(vb, new Insets(60,60,60,20));


        vb.setMargin(deleteLbl, new Insets(20,20,20,0));
        //vb.setMargin(nameBox, new Insets(5,5,5,0));
        vb.setMargin(Price, new Insets(20,20,20,0));
        //vb.setMargin(addToCartBtn, new Insets(5,5,5,0));
        vb.setMargin(courierLbl, new Insets(20,20,20,0));
        vb.setMargin(courierPLbl, new Insets(20,20,20,0));

        bp.setBottom(fp);
        bp.setCenter(fl);
        bp.setTop(menuV);


        //style

        deleteBtn.setMinHeight(40);
        deleteBtn.setMinWidth(150);


        courierLbl.setFont(Font.font(null, FontWeight.BOLD, 27));
        courierPLbl.setFont(Font.font(null, FontWeight.BOLD, 27));


        cartTableView.setMinHeight(450);
        cartTableView.setMinWidth(450);

        courierBox.setMinHeight(20);
        courierBox.setMinWidth(120);

        checkoutBtn.setMinHeight(40);
        checkoutBtn.setMinWidth(100);
        nameBox.setMinWidth(200);
        cartL.setFont(Font.font(null, FontWeight.BOLD, 30));
        deleteLbl.setFont(Font.font(null, FontWeight.BOLD, 30));
        Price.setFont(Font.font(null, FontWeight.BOLD, 30));


//        POP UP COMPONENT
        textWindow.setFont(Font.font(null, FontWeight.BOLD,20));
        window.setMovable(false);
        window.setResizableWindow(false);
        window.setMinHeight(500);
        window.setMinWidth(500);

        vbContent.getChildren().addAll(textWindow, gpBtn);
        vbContent.setAlignment(Pos.CENTER);
        vbContent.setSpacing(25);

        gpBtn.add(yesBtn,0,0);
        gpBtn.add(noBtn,1,0);
        gpBtn.setHgap(50);
        gpBtn.setAlignment(Pos.CENTER);

        window.getContentPane().getChildren().add(vbContent);
        root.getChildren().add(window);
//        END OF POP UP COMPONENT
    }


}
