package com.example.ca1mobiledevelopment;

import android.content.Intent; // Importing Intent class from the android.content package to perform intents
import android.os.Bundle; // Importing Bundle class from the android.os package to pass data between components
import android.view.MenuItem; // Importing MenuItem class from the android.view package the representation of a item in a menu
import android.widget.TextView; // Importing TextView class from the android.widget package to display text
import androidx.annotation.NonNull; // Importing NonNull annotation from the androidx.annotation package which is used to indicate that a return value should not be null
import androidx.appcompat.app.AppCompatActivity;// Importing AppCompatActivity class to provide compatibility support for older Android versions
import com.google.android.material.bottomnavigation.BottomNavigationView; // Importing BottomNavigationView class from the com.google.android.material.bottom navigation package

// Declaring the class which extends AppCompatActivity and implements the interface
public class FAQActivity extends AppCompatActivity implements FAQContract.View {

    // Instantiating the presenter with the current activity as the view and a new instance of FAQModel
    private FAQContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        presenter = new FAQPresenter(this, new FAQModel());



        // using the loadFAQContent method of the presenter to load FAQ content
        presenter.loadFAQContent();

        // Set up bottom navigation even though it's located at the top of the layout via ID navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            // navigation method with an if else statement, while I tried switch case before it didn't seem to work so I went back to if else and Its done through item ID.
            // When the button is clicked and ID is called you will be taken to the right page.
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle home action
                    startActivity(new Intent(FAQActivity.this, MainActivity.class));
                    return true;
                } else if (itemId == R.id.action_offers) {
                    // Handle offers action
                    startActivity(new Intent(FAQActivity.this, OffersActivity.class));
                    return true;
                } else if (itemId == R.id.action_feedback) {
                    // Handle feedback action
                    startActivity(new Intent(FAQActivity.this, FeedbackActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    // displayFAQContent method implementation with faqContent parameter which will update the UI
    // Finding the textview with ID and setting content
    @Override
    public void displayFAQContent(String faqContent) {
        // Update UI with the retrieved FAQ content
        TextView textViewFAQ = findViewById(R.id.textViewFAQ);
        textViewFAQ.setText(faqContent);
    }
}
