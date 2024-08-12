package com.example.ca1mobiledevelopment;

// This class acts as the model in the MVP architecture for the "About Us" feature where all the data is coming from.
public class AboutUsModel {

    //method to retrieve company information
    public String getCompanyInfo() {
        // In a real application, I would retrieve company information from a
        // database or server but I don't know how yet and due to time constraints and other CA's have decided not to do it.
        return "Welcome to Napolitan Pizza Niels! We are committed to serving the best pizza in town.";
    }
}
