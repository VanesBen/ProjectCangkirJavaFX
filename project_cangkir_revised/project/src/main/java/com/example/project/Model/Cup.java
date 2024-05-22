package com.example.project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cup {
    private final SimpleStringProperty cupID;
    private final SimpleStringProperty cupName;
    private final SimpleIntegerProperty cupPrice;

    public Cup(String cupID, String cupName, int cupPrice) {
        this.cupID = new SimpleStringProperty(cupID);
        this.cupName =  new SimpleStringProperty(cupName);
        this.cupPrice =  new SimpleIntegerProperty(cupPrice);
    }

    public String getCupID() {
        return cupID.get();
    }

    public String getCupName() {
        return cupName.get();
    }

    public int getCupPrice() {
        return cupPrice.get();
    }

    public SimpleStringProperty cupIDProperty() {
        return cupID;
    }

    public SimpleStringProperty cupNameProperty() {
        return cupName;
    }

    public SimpleIntegerProperty cupPriceProperty() {
        return cupPrice;
    }
}
