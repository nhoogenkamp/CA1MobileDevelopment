// FeedbackActivity.java
package com.example.ca1mobiledevelopment;

import android.content.Intent; // Importing Intent class from the android.content package to perform intents
import android.os.Bundle; // Importing Bundle class from the android.os package to pass data between components
import android.view.MenuItem; // Importing MenuItem class from the android.view package the representation of a item in a menu
import android.view.View; // Importing View class from the android.view package to work with UI components
import android.widget.Button; // Importing Button class from the android.widget package to create button UI components
import android.widget.EditText; // Importing EditText class from the android.widget package to create editable text fields
import android.widget.RatingBar; // Importing RatingBar class from the android.widget package to create rating bar UI components
import android.widget.Toast; // Importing Toast class from the android.widget package to show short-duration messages
import androidx.annotation.NonNull; // Importing NonNull annotation from the androidx.annotation package which is used to indicate that a return value should not be null
import androidx.appcompat.app.AppCompatActivity; // Importing AppCompatActivity class from the androidx.appcompat.app package to create activities
import com.google.android.material.bottomnavigation.BottomNavigationView; // Importing BottomNavigationView class from the com.google.android.material.bottom navigation package to create bottom navigation bars

// FeedbackActivity class declaration with inheritance from AppCompatActivity and implementation of the interface
public class FeedbackActivity extends AppCompatActivity implements FeedbackContract.View {

    //declarations for entering a name, comment, rating feedback and presenter field for handling the logic
    private EditText editTextName;
    private EditText editTextComments;
    private RatingBar ratingBar;
    private FeedbackContract.Presenter presenter;


    // oncreate method declaration with the bundle parameter,
    // calling superclass oncreate method and setting the content view of the activity_feedback  layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize views and assigning the fields by their id
        editTextName = findViewById(R.id.editTextName);
        editTextComments = findViewById(R.id.editTextComments);
        ratingBar = findViewById(R.id.ratingBar);
        Button buttonSubmitFeedback = findViewById(R.id.buttonSubmitFeedback);
        BottomNavigationView navigationView = findViewById(R.id.navigation);

        // Initialize presenter with this view and a new instance of the model
        presenter = new FeedbackPresenter(this, new Feedback());

        // Set onClickListener for the submit button
        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Get the values from the input fields
                String name = editTextName.getText().toString().trim();
                String comments = editTextComments.getText().toString().trim();
                int rating = (int) ratingBar.getRating();

                // Check if name and comments are not empty
                if (!name.isEmpty() && !comments.isEmpty()) {
                    // Submit feedback
                    presenter.submitFeedback(name, comments, rating);
                } else {
                    // Show a toast message indicating that all fields are required
                    Toast.makeText(FeedbackActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up bottom navigation even though it's located at the top of the layout via ID navigation
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            // navigation method with an if else statement, while I tried switch case before it didn't seem to work so I went back to if else and Its done through item ID.
            // When the button is clicked and ID is called you will be taken to the right page.
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle home action
                    startActivity(new Intent(FeedbackActivity.this, MainActivity.class));
                    return true;
                } else // Handle feedback action (we're already in FeedbackActivity)
                    if (item.getItemId() == R.id.action_offers) {
                    // Handle offers action
                    startActivity(new Intent(FeedbackActivity.this, OffersActivity.class));
                    return true;
                } else return item.getItemId() == R.id.action_feedback;
            }
        });
    }

    //method to show a message when the feedback is been send succesfully
    @Override
    public void showFeedbackSubmittedMessage() {
        // Show a toast message indicating that the feedback has been sent
        Toast.makeText(this, "Your feedback has been sent", Toast.LENGTH_SHORT).show();

        // Clear the form fields and automatic rating starting at 1 star
        editTextName.setText("");
        editTextComments.setText("");
        ratingBar.setRating(1);
    }

    //method indicating that the message was not send (even though it's set to always true as I don't have a database setup
    @Override
    public void showFeedbackSubmissionError() {
        // Show a toast message indicating that there was an error submitting the feedback
        Toast.makeText(this, "Failed to submit feedback. Please try again later", Toast.LENGTH_SHORT).show();
    }
}
