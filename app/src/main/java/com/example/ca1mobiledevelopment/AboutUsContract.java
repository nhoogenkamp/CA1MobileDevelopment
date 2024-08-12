package com.example.ca1mobiledevelopment; // Declares the package name for organizing the code

// AboutUsContract.java - Defines the contract between the View and the Presenter in the MVP architecture for the "About Us" feature
public interface AboutUsContract {

    // This defines the responsibilities of the View and method to display company information in the view
    interface View {
        void displayCompanyInfo(String companyInfo);
    }

    //This defines the responsibilities of the Presenter and method to load company information that will be used by the presenter
    interface Presenter {
        void loadCompanyInfo();
    }
}
