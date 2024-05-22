package com.example.project.Model;

public class Courier {
    private String courierID;
    private String courierName;
    private int courierPrice;

    public Courier(String courierID, String courierName, int courierPrice) {
        this.courierID = courierID;
        this.courierName = courierName;
        this.courierPrice = courierPrice;
    }

    public String getCourierID() {
        return courierID;
    }

    public String getCourierName() {
        return courierName;
    }

    public int getCourierPrice() {
        return courierPrice;
    }
}
