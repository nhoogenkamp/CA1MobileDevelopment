package com.example.ca1mobiledevelopment;

import android.content.Intent; // Importing Intent class from the android.content package to perform intents
import android.os.Bundle; // Importing Bundle class from the android.os package to pass data between components
import android.view.MenuItem; // Importing MenuItem class from the android.view package the representation of a item in a menu
import android.widget.TextView; // Importing TextView class from the android.widget package to display text
import androidx.annotation.NonNull; // Importing NonNull annotation from the androidx.annotation package which is used to indicate that a return value should not be null
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView; // Importing BottomNavigationView class from the com.google.android.material.bottom navigation package

// AboutUsActivity class declaration with inheritance from AppCompatActivity and implementation of AboutUsContract.View interface
public class AboutUsActivity extends AppCompatActivity implements AboutUsContract.View {

    // oncreate method declaration with the bundle parameter, calling superclass oncreate method and setting the content view of the activity_about_us layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Instantiating the presenter with the current activity as the view and a new instance of AboutUsModel
        AboutUsContract.Presenter presenter = new AboutUsPresenter(this, new AboutUsModel());

        // Invoking the loadCompanyInfo method of the presenter to load company information
        presenter.loadCompanyInfo();

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
                    startActivity(new Intent(AboutUsActivity.this, MainActivity.class));
                    return true;
                } else if (itemId == R.id.action_offers) {
                    startActivity(new Intent(AboutUsActivity.this, OffersActivity.class));
                    return true;
                } else if (itemId == R.id.action_feedback) {
                    startActivity(new Intent(AboutUsActivity.this, FeedbackActivity.class));
                    return true;
                }
                // Returning false to indicate that the item selection event has not been handled
                return false;
            }
        });
    }

    //displaying company information method implementation which will update the UI with the
    // retrieved company information which is stored in AboutUsModel via the textview with id.
    @Override
    public void displayCompanyInfo(String companyInfo) {
        TextView textViewCompanyInfo = findViewById(R.id.textViewCompanyInfo);
        textViewCompanyInfo.setText(companyInfo);
    }
}
