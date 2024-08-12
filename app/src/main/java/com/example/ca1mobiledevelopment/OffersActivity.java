package com.example.ca1mobiledevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import java.util.Locale;

public class OffersActivity extends AppCompatActivity implements OffersContract.View {

    // set the content view for the activity to activity_offer layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // start view and loading offer by using presenter
        OffersPresenter presenter = new OffersPresenter(this);
        presenter.loadOffers();

        // Set up bottom navigation even though it's located at the top of the layout via ID navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            // navigation method with an if else statement, while I tried switch case before it didn't seem to work so I went back to if else and Its done through item ID.
            // When the button is clicked and ID is called you will be taken to the right page.
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    startActivity(new Intent(OffersActivity.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.action_offers) {
                    return true;
                } else if (item.getItemId() == R.id.action_feedback) {
                    startActivity(new Intent(OffersActivity.this, FeedbackActivity.class));
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.action_offers);
    }

    // method to display discount offer received from the presenter
    @Override
    public void displayOffers(List<Offer> offers) {
        LinearLayout offersLayout = findViewById(R.id.offers_layout);
        LayoutInflater inflater = LayoutInflater.from(this);

        // iterate through each offer and create a view for each one
        for (Offer offer : offers) {
            View offerView = inflater.inflate(R.layout.offer_item, offersLayout, false);

            TextView offerName = offerView.findViewById(R.id.offer_name);
            TextView offerDescription = offerView.findViewById(R.id.offer_description);
            TextView offerDiscount = offerView.findViewById(R.id.offer_discount);
            ImageView offerImage = offerView.findViewById(R.id.offer_image);

            // set the text and image for each promo offer
            offerName.setText(offer.getName());
            offerDescription.setText(offer.getDescription());
            offerDiscount.setText(String.format(Locale.getDefault(), "%s%% off", offer.getDiscount()));
            offerImage.setImageResource(offer.getImageResourceId());

            // add the offer view to the layout
            offersLayout.addView(offerView);
        }
    }
}
