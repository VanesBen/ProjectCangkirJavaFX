package com.example.project;

import com.example.project.Controller.*;
import com.example.project.View.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    private RegisterController registerController;
    private LoginController loginController;
    private HomePageController homePageController;
    private static CartController cartController;
    private CupManagementController cupManagementController;

    public static Stage getStage() {
        return primaryStage;
    }

    public void initialization () {
        //Load all scenes that needed
        registerController = new RegisterController();
        registerController.launchRegister();

        loginController = new LoginController();
        loginController.launchLogin();

        homePageController = new HomePageController();
        homePageController.homePageLaunch();

        cartController = new CartController();
        cartController.cartLaunch();

        cupManagementController = new CupManagementController();
        cupManagementController.launchCupManagement();

    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.primaryStage = stage;
            initialization();


            primaryStage.setScene(Login.getLoginScene());
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (Exception e) {
            throw e;
        }

    }

    public static void main(String[] args) {
        launch();
    }

    public static void redirectPage(Stage stage, Scene newScene, String pageName) {
        stage.setScene(newScene);
        stage.setTitle(pageName);
        stage.show();
    }

}
