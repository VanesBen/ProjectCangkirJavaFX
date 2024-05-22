package com.example.project.View;

import com.example.project.Controller.Connect;
import com.example.project.Model.Cup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomePage{

    protected BorderPane bp;
    protected GridPane gp,pr,st;
    protected static Scene scene;
    protected FlowPane fp ,fl;
    protected VBox vb ,menuV;


    protected MenuBar menuBar;
    protected MenuItem menuHome, menuCart, menuLogout;
    protected Menu menu;

    protected Spinner<Integer> nameBox = new Spinner<Integer>();

    protected TableView<Cup> cupListTable;
    public static ObservableList<Cup> cupData;
    protected TableColumn<Cup,String> cupNameCol;
    protected TableColumn<Cup,Integer> cupPriceCol;
    protected Label cupL, cupN,Price;
    protected Button addToCartBtn;

    protected Connect connect = Connect.getInstance();

    public static Scene getHomePageScene() {
        return scene;
    }

    public void initiate() {

        bp = new BorderPane();

        gp = new GridPane();

        cupListTable = new TableView<Cup>();
        cupData = FXCollections.observableArrayList();


        cupNameCol = new TableColumn<>("Cup Name");
        cupNameCol.setMinWidth(225);
//        cupNameCol.setCellValueFactory(cellData -> cellData.getValue().cupNameProperty());
        cupNameCol.setCellValueFactory(cellData -> cellData.getValue().cupNameProperty());

        cupPriceCol = new TableColumn<>("Cup Price");
        cupPriceCol.setMinWidth(225);
        cupPriceCol.setCellValueFactory(cellData -> cellData.getValue().cupPriceProperty().asObject());

        cupL = new Label("Cup List");
        cupN = new Label("Cup Name");
        Price = new Label("Price");

        addToCartBtn = new Button("Add To Cart");

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

        scene = new Scene(bp, 1000, 800);
    }



    public void addComponent() {

        menu.getItems().add(menuHome);
        menu.getItems().add(menuCart);
        menu.getItems().add(menuLogout);
        menuBar.getMenus().add(menu);
        menuV = new VBox(menuBar);


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        nameBox.setValueFactory(valueFactory);


        pr.add(cupN, 0, 0);
        pr.add(Price, 1, 0);
        vb.getChildren().addAll(cupN);
        vb.getChildren().addAll(nameBox);
        vb.getChildren().addAll(Price);
        vb.getChildren().addAll(addToCartBtn);
        gp.add(cupL, 0, 0);
        gp.add(cupListTable, 0, 1);
        gp.add(vb, 1, 1);
        //st.add(gp, 0, 0);
        //st.add(vb, 1, 1);
        fp.getChildren().add(gp);
        //fl.getChildren().add(vb);
        fp.setAlignment(Pos.BOTTOM_LEFT);
        //fl.setAlignment(Pos.BOTTOM_CENTER);



        gp.setMargin(cupL, new Insets(0,0,-20,20));
        gp.setMargin(cupListTable, new Insets(20,0,20,20));

        gp.setMargin(vb, new Insets(60,60,60,20));


        vb.setMargin(cupN, new Insets(20,20,20,0));
        //vb.setMargin(nameBox, new Insets(5,5,5,0));
        vb.setMargin(Price, new Insets(20,20,20,0));
        //vb.setMargin(addToCartBtn, new Insets(5,5,5,0));

        bp.setBottom(fp);
        bp.setCenter(fl);
        bp.setTop(menuV);


        //style
        cupListTable.setMinHeight(450);
        cupListTable.setMinWidth(450);
        cupListTable.getColumns().addAll(cupNameCol,cupPriceCol);
        cupListTable.setItems(cupData);

        addToCartBtn.setMinHeight(40);
        addToCartBtn.setMinWidth(175);
        nameBox.setMinWidth(200);
        cupL.setFont(Font.font(null, FontWeight.BOLD, 30));
        cupN.setFont(Font.font(null, FontWeight.BOLD, 30));
        Price.setFont(Font.font(null, FontWeight.BOLD, 30));

    }


}