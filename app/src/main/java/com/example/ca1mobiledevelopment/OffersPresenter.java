package com.example.ca1mobiledevelopment;

import java.util.ArrayList;
import java.util.List;

public class OffersPresenter implements OffersContract.Presenter {
    private final OffersContract.View view;

    // constructor for assigning the view parameter to the view instance
    public OffersPresenter(OffersContract.View view) {
        this.view = view;
    }


    @Override
    public void loadOffers() {
        // Load offers from a new arraylist to hold discount codes
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer("1", "Discount 10%", "During checkout type DISCOUNT10 for: ", 10, R.drawable.discount10));
        offers.add(new Offer("2", "Discount 20%", "During checkout type DISCOUNT20 for: ", 20, R.drawable.discount20));

        view.displayOffers(offers);
    }

}

