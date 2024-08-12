package com.example.ca1mobiledevelopment;

// This class defines the contract between view and the presenter for the FAQ feature

import java.util.List;

public interface OffersContract {
    // method for displaying the display offer promotions
    interface View {
        void displayOffers(List<Offer> offers);
    }
// method for using the discount codes
    interface Presenter {
        void loadOffers();
    }
}

