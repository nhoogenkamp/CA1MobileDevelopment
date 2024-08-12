package com.example.ca1mobiledevelopment;

public class Offer {

    // fields representing a id for a promotion, name, description, discount, and image
    private String id;
    private String name;
    private String description;
    private double discount;
    private int imageResourceId; // New field for image resource ID

    // constructor to initialize the offer with values
    public Offer(String id, String name, String description, double discount, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.imageResourceId = imageResourceId;
    }

    //getter methods to retrieve the id, name, description, discount and images.

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
