package com.example.project.Controller;

import com.example.project.View.CupManagementView;
import javafx.scene.control.Alert;

import java.sql.*;

public class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "cangkir";
    private final String HOST = "localhost:3306";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s",HOST, DATABASE);

    public ResultSet rs;
    public ResultSetMetaData rsm;

    private Connection con;
    private Statement st;
    private static Connect connect;
    public static Connect getInstance() {
        if(connect == null) {
            return new Connect();
        } else {
            return connect;
        }
    }

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet execQuery(String query) {
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public void execUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                String errorM =  e.getMessage();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                if (errorM.contains("'Username'")) {
                    errorAlert.setContentText("Please choose a different username");
                    errorAlert.setHeaderText("Register Error");
                    errorAlert.show();
                } else if(errorM.contains("'Email'")) {
                    errorAlert.setContentText("Please choose a different email");
                    errorAlert.setHeaderText("Register Error");
                    errorAlert.show();
                } else if (errorM.contains("'CupName'")) {
                    errorAlert.setContentText("Cup Already Exists");
                    errorAlert.show();
                    CupManagementView.isErrorShow = false;
                }
            } else {
                e.printStackTrace();
            }

        }

    }


}
