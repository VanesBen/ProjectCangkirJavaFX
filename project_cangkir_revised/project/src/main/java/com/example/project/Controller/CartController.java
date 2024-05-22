package com.example.project.Controller;

import com.example.project.Main;
import com.example.project.Model.Cart;
import com.example.project.Model.Courier;
import com.example.project.View.CartView;
import com.example.project.View.HomePage;
import com.example.project.View.Login;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class CartController extends CartView implements EventHandler<ActionEvent> {
    private boolean isExecuted;
    private Courier selectedCourier;
    private int totalPrice = 0;
    private int courierPrice = 0;
    private int insurancePrice = 0;
    public void cartLaunch() {
        initiate();
        addComponent();
        setAction();
        loadCourierData();
        getRowData();
        getCourierData();
    }

    public String generateTransactionID() {
        String query = "SELECT COUNT(TransactionID) AS Total FROM transactionHeader";
        String generatedId;
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                int total = connect.rs.getInt("Total");
                String formattedNumber = String.format("%03d", total+1);
                generatedId = "TR" + formattedNumber;
                return generatedId;
            }
        } catch (Exception e) {

        }
        return "halo";
    }

    public void insertTransaction(String transactionID, String userID, String courierID, int useDelivery) {
        String insertQuery = "INSERT INTO transactionheader " +
                "VALUES ('"+ transactionID +"', '"+ userID +"','"+ courierID +"', CURDATE(), "+ useDelivery +")";
        connect.execUpdate(insertQuery);
    }

    public void insertTranDetail(String transactionID, String cupID, int quantityCup) {
        String insertQuery = "INSERT INTO transactiondetail " +
                "VALUES ('"+ transactionID +"', '"+ cupID +"', "+ quantityCup +")";
        connect.execUpdate(insertQuery);
    }
    public void getRowData() {
        //listener for row table view
        cartTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedItemCart = cartTableView.getSelectionModel().getSelectedItem();
            }
        });
    }

    private void restartAll() {
        if(courierBox.getValue() != null || insurance.isSelected()) {
            courierBox.setValue(null);
            courierPLbl.setText("Courier Price: ");
            insurance.setSelected(false);
        }
        totalPrice = 0;
        courierPrice = 0;
        insurancePrice = 0;
    }

    private void emptyCart(String userID) {
        String query = String.format("DELETE FROM cart WHERE UserID = '%s'",userID);
        cartData.clear();
        connect.execUpdate(query);
    }

    private void deleteItemCart(Cart cartItem, String userID, String cupID) {
        String query = String.format("DELETE FROM cart WHERE UserID = '%s' AND CupID = '%s'",userID, cupID);
        cartData.remove(cartItem);
        connect.execUpdate(query);
    }

    public void loadCourierData() {
        String query = "SELECT * FROM mscourier";
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                String CourierID = connect.rs.getString("CourierID");
                String CourierName = connect.rs.getString("CourierName");
                int CourierPrice = connect.rs.getInt("CourierPrice");

                Courier newCourier = new Courier(CourierID, CourierName, CourierPrice);
                courierData.add(newCourier);
            }
        } catch (Exception e) {

        }
    }

    public void getCourierData() {
        courierBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Courier>() {
            @Override
            public void changed(ObservableValue<? extends Courier> observable, Courier oldValue, Courier newValue) {
                if (newValue != null) {
                    selectedCourier = newValue;
                    courierPLbl.setText("Courier Price: "+ selectedCourier.getCourierPrice());
                    if(courierPrice == 0) {
                        courierPrice = selectedCourier.getCourierPrice();
                        totalPrice += selectedCourier.getCourierPrice();
                    } else {
                        totalPrice -= courierPrice;
                        courierPrice = selectedCourier.getCourierPrice();
                        totalPrice += selectedCourier.getCourierPrice();
                    }
                    Price.setText("Total Price: "+ totalPrice);
                }
            }
        });
    }


    public void loadCartData(String userId) {
        String query = "SELECT c.CupID, CupName, CupPrice, Quantity " +
                "FROM cart c JOIN mscup mc ON c.CupID = mc.CupID " +
                "WHERE UserID = '"+ userId +"'";
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                String CupID = connect.rs.getString("c.CupID");
                String CupName = connect.rs.getString("CupName");
                int CupPrice = connect.rs.getInt("CupPrice");
                int Quantity = connect.rs.getInt("Quantity");

                Cart newCart = new Cart(CupID,CupName,CupPrice,Quantity);
                totalPrice += newCart.getTotal();
                cartData.add(newCart);
            }
        } catch (Exception e) {

        }
    }

    private void setAction() {
        menuHome.setOnAction(this);
        menuLogout.setOnAction(this);
        deleteBtn.setOnAction(this);
        insurance.setOnAction(this);
        checkoutBtn.setOnAction(this);
        courierBox.setOnAction(this);

        isExecuted = false;
        scene.setOnMouseEntered(event -> {
            if (isExecuted == false) {
                restartAll();
                cartData.clear();
                loadCartData(Login.userLogin.getUserID());
                Price.setText("Total Price: "+ totalPrice);
                cartL.setText(Login.userLogin.getUsername()+"'s Cart");
                isExecuted = true;
            }
        });

//        popup component
        yesBtn.setOnAction(this);
        noBtn.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent e) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        if (e.getSource() == menuHome) {
            Main.redirectPage(Main.getStage(), HomePage.getHomePageScene(),"cangkiR");
            isExecuted = false;
        } else if (e.getSource() == menuLogout) {
            Main.redirectPage(Main.getStage(), Login.getLoginScene(),"Login");
            isExecuted = false;
        } else if (e.getSource() == deleteBtn) {
            if(selectedItemCart == null) {
                errorAlert.setContentText("Please select item you want to delete");
                errorAlert.setHeaderText("Deletion Error");
                errorAlert.show();
            } else {
                deleteItemCart(selectedItemCart,Login.userLogin.getUserID(),selectedItemCart.getCupID());
                cartData.clear();
                restartAll();
                loadCartData(Login.userLogin.getUserID());
                Price.setText("Price: "+ totalPrice);
                if(courierBox.getValue() != null || insurance.isSelected()) {
                    courierBox.setValue(null);
                    courierPLbl.setText("Courier Price: ");
                    insurance.setSelected(false);
                }
                infoAlert.setContentText("Cart Deleted Successfully");
                infoAlert.setHeaderText("Deletion Information");
                infoAlert.show();
            }
        } else if (e.getSource() == insurance) {
            if(insurance.isSelected()) {
                totalPrice += 2000;
                Price.setText("Total Price: "+ totalPrice);
            } else {
                totalPrice -= 2000;
                Price.setText("Total Price: "+ totalPrice);
            }
        } else if(e.getSource() == checkoutBtn) {
            if(cartData.isEmpty()) {
                errorAlert.setContentText("Your cart is empty!");
                errorAlert.show();
            } else {
                popUpStage.setScene(popUpScene);
                popUpStage.setTitle("Confirmation");
                popUpStage.show();
            }
        } else if (e.getSource() == noBtn) {
            popUpStage.close();
        } else if (e.getSource() == yesBtn) {
            int insuraceSelect;
            String tranID = generateTransactionID();
            if(insurance.isSelected()) {
                insuraceSelect = 1;
            } else {
                insuraceSelect = 0;
            }
            insertTransaction(tranID,Login.userLogin.getUserID(),selectedCourier.getCourierID(),insuraceSelect);
            for (int i = 0; i < cartData.size(); i++) {
                Cart item = cartData.get(i);
                insertTranDetail(tranID,item.getCupID(),item.getQuantity());
            }
            popUpStage.close();
            emptyCart(Login.userLogin.getUserID());
            restartAll();
            infoAlert.setContentText("Checkout Successful");
            infoAlert.setHeaderText("Checkout Information");
            infoAlert.show();
        }
    }
}
