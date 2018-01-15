package com.example.evy.ocs;

/**
 * Car represents the product of the store
 */

public class car {

    private String Name;
    private String Price;

    public car() {}

    public car(String name, String price) {
        this.Name = name;
        this.Price = price;
    }

    public String getName() { return Name; }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

}
