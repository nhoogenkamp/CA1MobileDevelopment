package com.example.ca1mobiledevelopment;

import android.content.Intent; // Importing Intent class from the android.content package to perform intents
import android.os.Bundle; // Importing Bundle class from the android.os package to pass data between components
import android.view.LayoutInflater; // Importing LayoutInflater class to instantiate layout XML file into its corresponding View objects
import android.view.MenuItem; // Importing MenuItem class from the android.view package the representation of an item in a menu
import android.view.View; // Importing View class to represent the basic building block for user interface components
import android.view.ViewGroup; // Importing ViewGroup class to provide a ViewGroup that can contain other ViewGroups or Views
import android.widget.Button; // Importing Button class to create a clickable button
import android.widget.ImageView; // Importing ImageView class to display images
import android.widget.Spinner; // Importing Spinner class to create a dropdown menu
import android.widget.TextView; // Importing TextView class to display text
import android.widget.Toast; // Importing Toast class to show a message
import androidx.appcompat.app.AlertDialog; // Importing AlertDialog class from the androidx.appcompat.app package to create alert dialogs
import androidx.appcompat.app.AppCompatActivity; // Importing AppCompatActivity class from the androidx.appcompat.app package to create activity
import androidx.core.graphics.Insets; // Importing Insets class from the androidx.core.graphics package to work with insets
import androidx.core.view.ViewCompat; // Importing ViewCompat class from the androidx.core.view package to support compatible features
import androidx.core.view.WindowInsetsCompat; // Importing WindowInsetsCompat class from the androidx.core.view package to represent a set of insets for window content
import java.util.Locale; // Importing Locale class to represent a specific geographical, political, or cultural region
import java.util.List; // Importing List interface to represent an ordered collection
import android.widget.EditText; // Importing EditText class to create a text field
import com.google.android.material.bottomnavigation.BottomNavigationView; // Importing BottomNavigationView class from the com.google.android.material.bottom navigation package

// you can find my references in a file called readme

// class declaration with inheritance from AppCompatActivity and implementation of the mainview interface
public class MainActivity extends AppCompatActivity implements MainView {
    //declarations for main presenter instance, list to hold my pizza products, button for about us.
    private MainPresenter presenter;
    private List<Product> productList;


    // Setting the content view of the activity to activity_main layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // starting mainpresenter with this view and loading the pizza products
        presenter = new MainPresenter(this);
        presenter.loadProducts();

        // Find the BottomNavigationView
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        // Set up menu
        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            // navigation method with an if else statement, while I tried switch case before it didn't seem to work so I went back to if else and Its done through item ID.
            // When the button is clicked and ID is called you will be taken to the right page.
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle home action
                    // Example: startActivity(new Intent(MainActivity.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.action_offers) {
                    // Handle offers action
                    startActivity(new Intent(MainActivity.this, OffersActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.action_feedback) {
                    // Handle feedback action
                    startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Set up padding for ScrollView
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scroll_view), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the "About Us" button
        Button buttonAboutUs = findViewById(R.id.button_about_us);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the About Us activity
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });
        // Initialize the "FAQ" button
        Button buttonFAQ = findViewById(R.id.button_faq);
        buttonFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the FAQ activity
                startActivity(new Intent(MainActivity.this, FAQActivity.class));
            }
        });
    }

    // method to display the products in the activity
    // Assign the product list
    @Override
    public void displayProducts(List<Product> products) {
        // Assign the product list
        productList = products;
        // Find the ViewGroup where products will be displayed
        ViewGroup productsLayout = findViewById(R.id.products_layout);
        // Create a layout inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        // Clear existing views in the layout
        productsLayout.removeAllViews();

        // Iterate through the list of products and create a view for each product
        for (Product product : products) {
            // Inflate the product item layout
            View productView = inflater.inflate(R.layout.product_item, productsLayout, false);

            // Find views inside the product item layout
            TextView productName = productView.findViewById(R.id.product_name);
            TextView productDescription = productView.findViewById(R.id.product_description);
            TextView productPrice = productView.findViewById(R.id.product_price);
            ImageView productImage = productView.findViewById(R.id.product_image);

            // Set data to views
            productName.setText(product.getName());
            productDescription.setText(product.getDescription());
            productPrice.setText(String.format(Locale.getDefault(), "€%.2f", product.getPrice()));
            productImage.setImageResource(product.getImageResource());

            // Add click listener to handle product purchase
            Button buyButton = productView.findViewById(R.id.buy_button);
            buyButton.setOnClickListener(v -> presenter.onBuyProduct(product.getName()));

            // Add the product view to the products layout
            productsLayout.addView(productView);
        }
    }

    // method to pop a purchase dialog for a product

    @Override
    public void showPurchaseDialog(String productName) {
        // Find the product based on the name
        Product product = getProductByName(productName);
        if (product != null) {
            // Create a dialog to prompt the user to enter the quantity and discount code
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Purchase " + productName);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_purchase, null);
            builder.setView(dialogView);

            // finding a quantity spinner, discount codeinput, name input, address input and email input
            Spinner quantitySpinner = dialogView.findViewById(R.id.spinner_quantity);
            EditText discountCodeInput = dialogView.findViewById(R.id.discount_code_input);
            EditText nameInput = dialogView.findViewById(R.id.name_input);
            EditText addressInput = dialogView.findViewById(R.id.address_input);
            EditText emailInput = dialogView.findViewById(R.id.email_input);

            // setting button click listener
            builder.setPositiveButton("Buy", (dialog, which) -> {
                // Validate name, address, and email
                String name = nameInput.getText().toString().trim();
                String address = addressInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();

                if (name.isEmpty() || address.isEmpty() || email.isEmpty()) {
                    // If any field is empty, show a toast message and return
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate email format using a regular expression
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.matches(emailPattern)) {
                    // If email format is invalid, show a toast message and return
                    Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get the selected quantity
                int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());

                // Get the entered discount code
                String discountCode = discountCodeInput.getText().toString().trim();

                // Calculate the discount
                double discountAmount = calculateDiscount(product, discountCode);

                if (discountAmount > 0) {
                    // Discount applied, proceed with purchase and display the purchase confirmation toast message
                    double totalPrice = product.getPrice() * quantity - discountAmount;
                    String message = "You have purchased " + quantity + " " + productName + "(s) for a total of €" + String.format(Locale.getDefault(), "%.2f", totalPrice);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    // Discount code is incorrect, show an error message
                    Toast.makeText(MainActivity.this, "Incorrect discount code", Toast.LENGTH_SHORT).show();
                }
            });


            builder.setNegativeButton("Cancel", null);
            builder.create().show();
        }
    }


    // method to calculate discount based on code provided

    private double calculateDiscount(Product product, String discountCode) {
        // Check if the discount code is empty
        if (discountCode.isEmpty()) {
            // No discount if the discount code is empty
            return 0;
        } else if (discountCode.equals("DISCOUNT10")) {
            // 10% discount for DISCOUNT10
            return product.getPrice() * 0.10;
        } else if (discountCode.equals("DISCOUNT20")) {
            // 20% discount for DISCOUNT20
            return product.getPrice() * 0.20;
        } else {
            // Invalid discount code, show error message
            Toast.makeText(MainActivity.this, "Invalid discount code", Toast.LENGTH_SHORT).show();
            // Return no discount
            return 0;
        }
    }

    // method to get a product by name (for my message when they have purchased something at the moment only)
    private Product getProductByName(String productName) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

}
