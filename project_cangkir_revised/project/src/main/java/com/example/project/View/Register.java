package com.example.project.View;

import com.example.project.Controller.Connect;
import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;


public class Register {

    protected Label usernamelbl, emaillbl, passwordlbl, regis, gender;
    protected Button button;
    protected TextField usernameTxt, emailTxt;
    protected PasswordField passwordTxt;
    protected GridPane usernameGrid, passGrid, emailGrid, genderGrid;
    protected static Scene sceneRegister;
    protected VBox vbox;

    protected ToggleGroup genderGroup;
    protected RadioButton malebtn, femalebtn;

    protected Hyperlink loginLink;

    protected Connect connect = Connect.getInstance();


    public static Scene getRegisterScene() {
        return sceneRegister;
    }


    public void initialize() {
        regis = new Label("Register");
        usernamelbl = new Label("Username");
        emaillbl = new Label("Email");
        passwordlbl = new Label("Password");
        gender = new Label("Gender");

        loginLink = new Hyperlink();
        loginLink.setText("Already have an account? Click here to loginPage");

        button = new Button("Register");

        usernameTxt = new TextField();
        emailTxt = new TextField();

        passwordTxt = new PasswordField();

        usernameGrid = new GridPane();
        passGrid = new GridPane();
        emailGrid = new GridPane();
        genderGrid = new GridPane();

        malebtn = new RadioButton("Male");
        femalebtn = new RadioButton("Female");
        genderGroup = new ToggleGroup();

        malebtn.setToggleGroup(genderGroup);
        femalebtn.setToggleGroup(genderGroup);
    }

    public void register() {

        button.setPrefWidth(100);
        button.setPrefHeight(40);

        regis.setFont(Font.font(null, FontWeight.BOLD,30));
        gender.setFont(Font.font(null, FontWeight.BOLD,18));

        usernameTxt.setPrefWidth(400);
        usernameGrid.add(usernamelbl, 0, 0);
        usernameGrid.add(usernameTxt, 0, 1);
        usernameGrid.setAlignment(Pos.CENTER);

        emailTxt.setPrefWidth(400);
        emailGrid.add(emaillbl, 0, 0);
        emailGrid.add(emailTxt, 0, 1);
        emailGrid.setAlignment(Pos.CENTER);

        passwordTxt.setPrefWidth(400);
        passGrid.add(passwordlbl, 0, 0);
        passGrid.add(passwordTxt, 0, 1);
        passGrid.setAlignment(Pos.CENTER);

//	    genderGrid.setPrefWidth(400);
        genderGrid.add(gender, 0, 0);
        genderGrid.add(malebtn, 0, 1);
        genderGrid.add(femalebtn, 1, 1);
        genderGrid.setAlignment(Pos.CENTER_LEFT);
        genderGrid.setMargin(gender, new Insets(0,20,25,145));
        genderGrid.setMargin(malebtn, new Insets(0,20,0,145));

        vbox = new VBox(regis, usernameGrid, emailGrid, passGrid, genderGrid, loginLink, button);
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        sceneRegister = new Scene(vbox,700,700);
    }
}