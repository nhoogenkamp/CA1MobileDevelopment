package com.example.ca1mobiledevelopment;

// This class defines the contract between view and the presenter for the FAQ feature

import java.util.List;

public interface MainView {

    //method to display products in the view and show a purchase diaglog for a product
    void displayProducts(List<Product> products);
    void showPurchaseDialog(String productName);
}
