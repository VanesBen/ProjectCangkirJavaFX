package com.example.project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cart {
    private final SimpleStringProperty cupName;
    private String cupID;
    private final SimpleIntegerProperty cupPrice;
    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty total;

    public String getCupID() {
        return cupID;
    }

    public Cart(String cupID, String cupName, int cupPrice, int cupQuantity) {
        this.cupName = new SimpleStringProperty(cupName);
        this.cupID = cupID;
        this.cupPrice= new SimpleIntegerProperty(cupPrice);
        this.quantity= new SimpleIntegerProperty(cupQuantity);
        this.total= new SimpleIntegerProperty((cupPrice * quantity.get()));
    }

    public String getCupName() {
        return cupName.get();
    }

    public SimpleStringProperty cupNameProperty() {
        return cupName;
    }

    public int getCupPrice() {
        return cupPrice.get();
    }

    public SimpleIntegerProperty cupPriceProperty() {
        return cupPrice;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public int getTotal() {
        return total.get();
    }

    public SimpleIntegerProperty totalProperty() {
        return total;
    }
}
