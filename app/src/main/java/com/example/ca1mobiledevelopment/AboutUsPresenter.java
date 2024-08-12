package com.example.ca1mobiledevelopment;

// This class acts as the presenter in the MVP architecture for the "About Us" feature
//The presenter handles the presentation logic and acts between view interface and model
public class AboutUsPresenter implements AboutUsContract.Presenter {
    private final AboutUsContract.View view;
    private final AboutUsModel model;

    // Constructor for AboutUsPresenter - Takes a View and a Model as parameters
    public AboutUsPresenter(AboutUsContract.View view, AboutUsModel model) {
        // Initializes the view reference with the passed View implementation
        this.view = view;
        // creates the model reference with the passed Model instance
        this.model = model;
    }

    @Override
    public void loadCompanyInfo() {
        // method for retrieving company information from the model
        String companyInfo = model.getCompanyInfo();

        // method for updating the view with the retrieved company information
        view.displayCompanyInfo(companyInfo);
    }
}
