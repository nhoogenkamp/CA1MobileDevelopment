// FAQPresenter.java
package com.example.ca1mobiledevelopment;
// This class acts as the presenter in the MVP architecture for the "FAQ" feature
//The presenter handles the presentation logic and acts between view interface and model

public class FAQPresenter implements FAQContract.Presenter {
    private final FAQContract.View view;
    private final FAQModel model;

    // Constructor for FAQPresenter - Takes a View and a Model as parameters
    public FAQPresenter(FAQContract.View view, FAQModel model) {
        // Initializes the view reference with the passed View implementation
        this.view = view;
        // Initializes the model reference with the passed Model instance
        this.model = model;
    }

    @Override
    public void loadFAQContent() {
        // method for retrieving FAQ content from the model
        String faqContent = model.getFAQContent();

        // method for updating the view with the retrieved FAQ content
        view.displayFAQContent(faqContent);
    }
}
