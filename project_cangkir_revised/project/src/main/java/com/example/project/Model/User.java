package com.example.project.Model;

public class User {
    private String UserID;
    private String Username;
    private String Email;
    private String UserPassword;
    private String UserGender;
    private String UserRole;

    public String getUserID() {
        return UserID;
    }

    public String getEmail() {
        return Email;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public String getUserGender() {
        return UserGender;
    }

    public String getUserRole() {
        return UserRole;
    }

    public User(String userID, String username, String email, String userPassword, String userGender, String userRole) {
        UserID = userID;
        Username = username;
        Email = email;
        UserPassword = userPassword;
        UserGender = userGender;
        UserRole = userRole;
    }

    public String getUsername() {
        return Username;
    }
}
