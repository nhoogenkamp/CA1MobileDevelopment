// FAQContract.java
package com.example.ca1mobiledevelopment;

// This class defines the contract between view and the presenter for the FAQ feature
public interface FAQContract {

    // This defines the responsibilities of the View and method to display FAQ in the view
    interface View {
        void displayFAQContent(String faqContent);
    }
    //This defines the responsibilities of the Presenter and method to load FAQ  that will be used by the presenter
    interface Presenter {
        void loadFAQContent();
    }
}
