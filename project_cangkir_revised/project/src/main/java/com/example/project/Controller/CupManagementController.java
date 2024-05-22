package com.example.project.Controller;

import com.example.project.Main;
import com.example.project.Model.Cup;
import com.example.project.View.CupManagementView;
import com.example.project.View.HomePage;
import com.example.project.View.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class CupManagementController extends CupManagementView implements EventHandler<ActionEvent> {

    private Cup selectedCup;

    public void launchCupManagement() {
        initiate();
        addComponent();
        setAction();
        getRowData();
    }


    public void setAction() {
        menuLogout.setOnAction(this);
        addCupBtn.setOnAction(this);
        removeCupBtn.setOnAction(this);
        updatePriceBtn.setOnAction(this);
    }

    public String generateCupID() {
        String query = "SELECT COUNT(CupID) AS Total FROM mscup";
        String generatedId;
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                int total = connect.rs.getInt("Total");
                String formattedNumber = String.format("%03d", total+1);
                generatedId = "CU" + formattedNumber;
                return generatedId;
            }
        } catch (Exception e) {

        }
        return "halo";
    }

    public void getRowData() {
        //listener for row table view
        tv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
//                selectedCup = newValue;
                selectedCup = tv.getSelectionModel().getSelectedItem();
                cupNameField.setText(selectedCup.getCupName());
                cupPriceField.setText(String.valueOf(selectedCup.getCupPrice()));
            }
        });
    }

    public void insertCup(String cupID, String cupName, int cupPrice) {
        String insertQuery = "INSERT INTO mscup " +
                "VALUES ('"+ cupID +"', '"+ cupName +"', "+ cupPrice +")";
        connect.execUpdate(insertQuery);
    }

    private void deleteCup(String cupID) {
        String query = String.format("DELETE FROM mscup WHERE CupID = '%s'", cupID);
        HomePage.cupData.remove(selectedCup);
        connect.execUpdate(query);
    }
    private void updateCup(int newPrice, String cupID) {
        String query = String.format("UPDATE mscup SET CupPrice = %d WHERE CupID = '%s' "
                ,newPrice, cupID);
        connect.execUpdate(query);
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
                HomePage.cupData.add(newCup);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void handle(ActionEvent e) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        if(e.getSource() == menuLogout) {
            Main.redirectPage(Main.getStage(), Login.getLoginScene(), "Login");
        } else if (e.getSource() == addCupBtn) {
            if (cupNameField.getText().equals("") || cupPriceField.getText().equals("")) {
                errorAlert.setContentText("Please fill out the cup name");
                errorAlert.show();
            } else if (Integer.parseInt(cupPriceField.getText()) > 1000000 ||
                    Integer.parseInt(cupPriceField.getText()) < 5000) {
                errorAlert.setContentText("Cup price must be 5000 - 1000000");
                errorAlert.show();
            } else {
                insertCup(generateCupID(), cupNameField.getText(), Integer.parseInt(cupPriceField.getText()));
                infoAlert.setContentText("Cup Succesfully Added");
                if(isErrorShow) {
                    infoAlert.show();
                }
                HomePage.cupData.clear();
                loadCupData();
            }
        } else if (e.getSource() == removeCupBtn) {
            if (selectedCup == null) {
                errorAlert.setContentText("Please select a cup from the table to be removed!");
                errorAlert.show();
            } else {
                deleteCup(selectedCup.getCupID());
                HomePage.cupData.clear();
                loadCupData();
                selectedCup = null;
                cupNameField.clear();
                cupPriceField.clear();
            }
        } else if (e.getSource() == updatePriceBtn) {
            if (selectedCup == null) {
                errorAlert.setContentText("Please select a cup from the table to be updated!");
                errorAlert.show();
            } else if (Integer.parseInt(cupPriceField.getText()) > 1000000 ||
                    Integer.parseInt(cupPriceField.getText()) < 5000) {
                errorAlert.setContentText("Cup price must be 5000 - 1000000");
                errorAlert.show();
            } else {
                updateCup(Integer.parseInt(cupPriceField.getText()),selectedCup.getCupID());
                HomePage.cupData.clear();
                loadCupData();
                selectedCup = null;
                cupNameField.clear();
                cupPriceField.clear();
            }
        }
    }
}
