package com.example.project.Controller;

import com.example.project.View.CartView;
import com.example.project.View.HomePage;
import com.example.project.Main;
import com.example.project.Model.Cup;
import com.example.project.View.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class HomePageController extends HomePage implements EventHandler<ActionEvent> {
    private Cup selectedCup;
    private int quantity;
    private int totalPrice;
    public void homePageLaunch() {
        initiate();
        addComponent();
        loadCupData();
        setAction();
        getRowData();
        if(Login.userLogin != null) {
            CartController newCardController = new CartController();
            newCardController.loadCartData(Login.userLogin.getUserID());
        }
    }

    public void loadCupData() {
        String query = "SELECT * FROM mscup";
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                String CupID = connect.rs.getString("CupID");
                String CupName = connect.rs.getString("CupName");
                int CupPrice = connect.rs.getInt("CupPrice");

                Cup newCup = new Cup(CupID,CupName,CupPrice);
                cupData.add(newCup);
            }
        } catch (Exception e) {

        }
    }


    public void getRowData() {
        cupListTable.getSelectionModel().selectedItemProperty().addListener((observable, oldCup, newCup) -> {
            if (newCup != null) {
                selectedCup = cupListTable.getSelectionModel().getSelectedItem();
                quantity = nameBox.getValue();
                totalPrice = selectedCup.getCupPrice() * quantity;

                cupN.setText("Cup: " + selectedCup.getCupName());
                Price.setText("Price: " + totalPrice);
            }
        });

        nameBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Cup selectedCup = cupListTable.getSelectionModel().getSelectedItem();
            if (selectedCup != null) {
                cupN.setText("Cup: " + selectedCup.getCupName());
                int totalPrice = selectedCup.getCupPrice() * newValue.intValue();
                Price.setText("Price: " + totalPrice);
            }
        });
    }
    private boolean isItemExists(String value1, String value) {
        try {
            String query = String.format("SELECT * FROM cart WHERE UserID = '%s' AND CupID = '%s'", value1, value);
            connect.rs = connect.execQuery(query);
            if (connect.rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    private void updateCart(int addedValue, String userID, String cupID) {
        String query = String.format("UPDATE cart SET Quantity = Quantity + %d WHERE CupID = '%s' AND UserID = '%s'"
                ,addedValue, cupID,userID);
        connect.execUpdate(query);
    }

    private void insertCart(int quantity, String userID, String cupID) {
        String insertQuery = "INSERT INTO cart " +
                "VALUES ('"+ userID +"', '"+ cupID +"', "+ quantity +")";
        connect.execUpdate(insertQuery);
    }

    public void setAction() {
        menuLogout.setOnAction(this);
        menuCart.setOnAction(this);
        addToCartBtn.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == menuLogout) {
            Main.redirectPage(Main.getStage(), Login.getLoginScene(),"Login");
        } else if (e.getSource() == addToCartBtn) {
            int selectedIndex = cupListTable.getSelectionModel().getSelectedIndex();
            if(selectedIndex == -1) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Please select a cup to be added");
                errorAlert.setHeaderText("Cart Error");
                errorAlert.show();
            } else {
                if(isItemExists(Login.userLogin.getUserID(),selectedCup.getCupID())) {
                    updateCart(quantity,Login.userLogin.getUserID(),selectedCup.getCupID());
                } else {
                    insertCart(quantity,Login.userLogin.getUserID(),selectedCup.getCupID());
                }
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setContentText("Item Successfully added to cart!");
                infoAlert.setHeaderText("Cart Info");
                infoAlert.show();
            }
        } else if (e.getSource() == menuCart) {
            Main.redirectPage(Main.getStage(), CartView.getCartScene(),"cangkiR");

        }
    }
}
