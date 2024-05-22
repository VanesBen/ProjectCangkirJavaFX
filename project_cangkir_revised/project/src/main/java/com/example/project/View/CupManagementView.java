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

public class CupManagementView {
    protected Alert errorAlert;
    public static boolean isErrorShow = true;

    protected BorderPane bp;
    protected GridPane gp,pr, st;
    protected static Scene cupManagementscene;
    protected FlowPane fp ,fl;
    protected VBox vb ,menuV;

    protected MenuBar menuBar;
    protected MenuItem menuCupManagement, menuLogout;
    protected Menu menu;

    protected TableView<Cup> tv;
    protected TableColumn<Cup,String> cupNameCol;
    protected TableColumn<Cup,Integer> cupPriceCol;
    protected Label cupManagementL, addCupLbl, updatePriceLbl, removeCupLbl, cupNameLbl, cupPriceLbl;
    protected Button addCupBtn, updatePriceBtn, removeCupBtn;
    protected TextField cupNameField, cupPriceField;
    protected Connect connect = Connect.getInstance();
    protected ObservableList<Cup> cupData;

    public static Scene getCPScene() {
        return cupManagementscene;
    }


    protected void initiate() {
        errorAlert = new Alert(Alert.AlertType.ERROR);

        bp = new BorderPane();
        gp = new GridPane();

        tv = new TableView<Cup>();
        cupNameCol = new TableColumn<>("Cup Name");
        cupNameCol.setMinWidth(250);
        cupNameCol.setCellValueFactory(cellData -> cellData.getValue().cupNameProperty());
        cupPriceCol = new TableColumn<>("Cup Price");
        cupPriceCol.setMinWidth(250);
        cupPriceCol.setCellValueFactory(cellData -> cellData.getValue().cupPriceProperty().asObject());


        cupManagementL = new Label("Cup Management");
        cupNameLbl = new Label("Cup Name");
        cupPriceLbl = new Label("Cup Price");

        addCupLbl = new Label("Add Cup");
        addCupBtn = new Button("Add Cup");

        updatePriceLbl = new Label("Update Price");
        updatePriceBtn = new Button("Update Price");

        removeCupLbl = new Label("Remove Cup");
        removeCupBtn = new Button("Remove Cup");

        cupNameField = new TextField();
        cupPriceField = new TextField();

        cupNameField.setPromptText("Enter Cup Name");
        cupPriceField.setPromptText("Enter Cup Price");


        cupManagementscene = new Scene(bp, 1000, 800);
        fp = new FlowPane();
        pr = new GridPane();
        vb = new VBox();
        st = new GridPane();
        fl = new FlowPane();

        menuBar = new MenuBar();
        menuCupManagement = new MenuItem("Cup Management");
        menuLogout = new MenuItem("Log Out");
        menu = new Menu("Menu");
    }



    protected void addComponent() {

        menuBar.getMenus().add(menu);
        menu.getItems().add(menuCupManagement);
        menu.getItems().add(menuLogout);
        menuV = new VBox(menuBar);

        tv.getColumns().addAll(cupNameCol,cupPriceCol);
        tv.setItems(HomePage.cupData);


        gp.add(cupManagementL, 0, 0);
        gp.setMargin(cupManagementL, new Insets(0,0,-20,20));
        gp.setMargin(tv, new Insets(20,0,20,20));
        gp.add(tv, 0, 1);
        gp.add(vb, 1, 1);

        fp.getChildren().add(gp);
        fp.setAlignment(Pos.BOTTOM_LEFT);

        vb.setMargin(updatePriceBtn, new Insets(10,10,10,0));


        bp.setBottom(fp);
        bp.setCenter(fl);
        bp.setTop(menuV);

        vb.getChildren().add(cupNameLbl);
        vb.getChildren().add(cupNameField);
        vb.getChildren().add(cupPriceLbl);
        vb.getChildren().add(cupPriceField);

        vb.getChildren().addAll(addCupBtn);
        vb.getChildren().addAll(updatePriceBtn);
        vb.getChildren().addAll(removeCupBtn);

        vb.setMargin(addCupBtn, new Insets(20,10,0,10));
        vb.setMargin(updatePriceBtn, new Insets(10,10,0,10));
        vb.setMargin(removeCupBtn, new Insets(10,10,0,10));

        vb.setMargin(cupNameField, new Insets(10,10,0,10));
        vb.setMargin(cupPriceField, new Insets(10,10,0,10));
        vb.setMargin(cupNameLbl, new Insets(10,20,0,10));
        vb.setMargin(cupPriceLbl, new Insets(20,20,0,10));


        addCupBtn.setMinHeight(60);
        addCupBtn.setMinWidth(200);

        updatePriceBtn.setMinHeight(60);
        updatePriceBtn.setMinWidth(200);

        removeCupBtn.setMinHeight(60);
        removeCupBtn.setMinWidth(200);


        tv.setMinHeight(450);
        tv.setMinWidth(450);

        cupManagementL.setFont(Font.font(null, FontWeight.BOLD, 30));

    }


}
