package com.example.project.Controller;

import com.example.project.Main;
import com.example.project.View.*;
import com.example.project.Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class LoginController extends Login implements EventHandler<ActionEvent> {

    public void launchLogin() {
        inisialisasiLogin();
        login();
        setEvent();
    }

    private void setEvent() {
        loginBtn.setOnAction(this);
        registerLink.setOnAction(this);

    }

    private boolean isExists(String columnName, String username) {
        try {
            String query = String.format("SELECT * FROM msuser WHERE %s = '%s'", columnName, username);
            connect.rs = connect.execQuery(query);

            if (connect.rs.next()) {
                String UserID = connect.rs.getString("UserID");
                String Username = connect.rs.getString("Username");
                String Email = connect.rs.getString("UserEmail");
                String UserPassword = connect.rs.getString("UserPassword");
                String UserGender = connect.rs.getString("UserGender");
                String UserRole = connect.rs.getString("UserRole");

                userLogin = new User(UserID, Username, Email, UserPassword, UserGender, UserRole);

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == loginBtn ) {
            String userName = usernameTxt.getText();
            String pasWord = pass.getText();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            if(userName.equals("") || pasWord.equals("")) {
                errorAlert.setContentText("Field cannot be empty");
                errorAlert.setHeaderText("Login Error");
                errorAlert.show();
            }
            else if (isExists("Username",userName)) {
                if(!userName.equals(userLogin.getUsername()) || !pasWord.equals(userLogin.getUserPassword())) {
                    errorAlert.setContentText("Credential Wrong");
                    errorAlert.setHeaderText("Login Error");
                    errorAlert.show();
                } else {
                    if (userLogin.getUserRole().equals("User")) {
                        Main.redirectPage(Main.getStage(), HomePage.getHomePageScene(),"cangKir");
                    } else if (userLogin.getUserRole().equals("Admin")) {
                        Main.redirectPage(Main.getStage(), CupManagementView.getCPScene(),"cangKir");
                    }
                    usernameTxt.clear();
                    pass.clear();
                }
            }
            else {
                errorAlert.setContentText("Credential Wrong");
                errorAlert.setHeaderText("Login Error");
                errorAlert.show();
            }
        } else if (e.getSource() == registerLink) {
            Main.redirectPage(Main.getStage(),Register.getRegisterScene(),"Register");
        }
    }
}
