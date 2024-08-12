// MainPresenter.java
package com.example.ca1mobiledevelopment;

// This class acts as the presenter in the MVP architecture for the "FAQ" feature
//The presenter handles the presentation logic and acts between view interface and model
import java.util.List;

public class MainPresenter {
    private final MainView view;

    public MainPresenter(MainView view) {

        this.view = view;
    }

    // method to load the products for Product.java and pass the list to the view for display
    public void loadProducts() {
        List<Product> products = Product.generateProductList();
        view.displayProducts(products);
    }

    //method for when a product is bought with th message dialog
    public void onBuyProduct(String productName) {

        view.showPurchaseDialog(productName);
    }
}
