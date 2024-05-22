
package com.example.project.View;

import com.example.project.Controller.Connect;
import com.example.project.Model.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Login {
    public static User userLogin;

    protected Label nama, loginLbl, usernameLbl, passLabel;
    protected Button loginBtn;
    protected TextField usernameTxt;
    protected GridPane userNameGrid, passwordGrid;
    protected PasswordField pass;
    protected static Scene mainScene;
    protected VBox vbox, vbox2;

    protected Hyperlink registerLink;

    protected Connect connect = Connect.getInstance();

    public static Scene getLoginScene() {
        return mainScene;
    }

    public void inisialisasiLogin() {


        nama = new Label("Halo");
        loginLbl = new Label("Login");
        loginBtn = new Button("Login");
        usernameLbl = new Label("Username");
        usernameTxt = new TextField();
        userNameGrid = new GridPane();
        passwordGrid = new GridPane();
        passLabel = new Label("Password");
        pass = new PasswordField();
        registerLink = new Hyperlink();
        registerLink.setText("Don't have an account yet? Register here!");
        vbox = new VBox(loginLbl, userNameGrid, passwordGrid , loginBtn,registerLink);
        mainScene = new Scene(vbox,700,700);
        vbox2 = new VBox(nama);
    }

    public void login() {

        //Login Button
        loginBtn.setPrefWidth(100);
        loginBtn.setPrefHeight(40);
        // Login Button End

        //Tulisan Login
        loginLbl.setFont(Font.font(null, FontWeight.BOLD,30));
        // Tulisan Login End


        //Username Label and TextField
        usernameTxt.setPrefWidth(400);
        userNameGrid.add(usernameLbl, 0, 0);
        userNameGrid.add(usernameTxt, 0, 1);
        userNameGrid.setAlignment(Pos.CENTER);
        // Username Label and TextField end

        //Password Label and Password Field
        passwordGrid.add(passLabel, 0, 0);
        passwordGrid.add(pass, 0, 1);
        passwordGrid.setAlignment(Pos.CENTER);
        pass.setPrefWidth(400);
        //Password Label and Password Field End

        //Login Scene
        vbox.setSpacing(25);;
        vbox.setAlignment(Pos.CENTER);
        //Login Scene End

    }


}
