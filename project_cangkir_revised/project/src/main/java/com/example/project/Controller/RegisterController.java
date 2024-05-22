package com.example.project.Controller;

import com.example.project.Main;
import com.example.project.View.Login;
import com.example.project.View.Register;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;

public class RegisterController extends Register implements EventHandler<ActionEvent> {


    public void launchRegister() {
        initialize();
        register();
        setEvent();
    }
    public static boolean isAlphanumeric(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(!Character.isLetterOrDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public String generateID() {
        String query = "SELECT COUNT(UserID) AS Total FROM msuser";
        String generatedId;
        connect.rs = connect.execQuery(query);
        try {
            while (connect.rs.next()) {
                int total = connect.rs.getInt("Total");
                String formattedNumber = String.format("%03d", total+1);
                generatedId = "US" + formattedNumber;
                return generatedId;
            }
        } catch (Exception e) {

        }
        return "halo";
    }

    public void insertRow(String userID, String username, String email, String userPassword, String userGender,
                          String userRole) {
        String insertQuery = "INSERT INTO msuser " +
                "VALUES ('"+ userID +"', '"+ username +"', '"+ email +"', '"+userPassword+"', '"+ userGender +"', '"+ userRole
                + "')";
        connect.execUpdate(insertQuery);
    }

    private void setEvent() {
        button.setOnAction(this);
        loginLink.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == button) {

            String username = usernameTxt.getText();
            String email = emailTxt.getText();
            String password = passwordTxt.getText();
            String selectedGender = "";
            RadioButton selectedRadioButton = (RadioButton) genderGroup.getSelectedToggle();

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            if(username.isEmpty() || email.isEmpty() || password.isEmpty() || selectedRadioButton == null) {
                errorAlert.setContentText("Please fill out your username");
                errorAlert.setHeaderText("Register Error");
                errorAlert.show();

            } else if(!email.endsWith("@gmail.com")) {
                errorAlert.setContentText("Make sure your email ends with @gmail.com");
                errorAlert.setHeaderText("Register Error");
                errorAlert.show();

            } else if (password.length() < 8 || password.length() > 15) {
                errorAlert.setContentText("Make sure your password has a length of 8 - 15 characters");
                errorAlert.setHeaderText("Register Error");
                errorAlert.show();

            } else if (!isAlphanumeric(password)) {
                errorAlert.setContentText("Password must be alphanumeric");
                errorAlert.setHeaderText("Register Error");
                errorAlert.show();
            } else  {
                selectedGender = selectedRadioButton.getText();
                insertRow(generateID(),username,email,password,selectedGender,"User");
                Main.redirectPage(Main.getStage(), Login.getLoginScene(),"Login");
                passwordTxt.clear();
                usernameTxt.clear();
                emailTxt.clear();

            }


        } else if(e.getSource() == loginLink) {
            Main.redirectPage(Main.getStage(), Login.getLoginScene(),"Login");
        }

    }
}
