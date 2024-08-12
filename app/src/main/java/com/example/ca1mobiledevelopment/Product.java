// Product.java
package com.example.ca1mobiledevelopment;

import java.util.ArrayList;
import java.util.List;

public class Product {
    // class declaration for name, description, price and image
    private final String name;
    private final String description;
    private final double price;
    private final int imageResource;

    // method to generate and hold list of pizza names and ingredients to
    public static List<Product> generateProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Marinara", "Ingredients: - Tomato sugo, garlic, basil & oregano", 8.99, R.drawable.marina));
        products.add(new Product("Margherita ", "Ingredients: Fresh mozzarella, basil & tomato sugo ", 10.99, R.drawable.margherita));
        products.add(new Product("Diavola ", "Ingredients: - Spicy salami from Calabria, fresh mozzarella,\n" +
                "basil & tomato sugo", 12.99, R.drawable.diavola));
        products.add(new Product("Prosciutto E Funghi ", "Ingredients: Cooked Italian ham,\n" +
                "mushrooms, fresh mozzarella & tomato sugo ", 13.99, R.drawable.prosciutto));
        products.add(new Product("Sapori Del Sud ", "Ingredients: Italian fennel sausage, nduja,\n" +
                "friarielli & fresh mozzarella ", 14.99, R.drawable.sapori));
        products.add(new Product("Vegetarian ", "Ingredients: - Aubergine, courgette, peppers,\n" +
                "fresh mozzarella & tomato sugo ", 13.99, R.drawable.vegetarian));
        products.add(new Product("Rucola ", "Ingredients: Parma ham, rocket, Parmigiano-Reggiano,\n" +
                "fresh mozzarella & tomato sugo ", 14.99, R.drawable.rucola));
        products.add(new Product("Capricciosa ", "Ingredients: Italian ham, olives, mushrooms,\n" +
                "artichokes, fresh mozzarella & tomato sugo ", 15.99, R.drawable.capricciosa));
        products.add(new Product("Vesuvius ", "Ingredients: Spicy salami from Calabria, nduja,\n" +
                "fresh chilli, fresh mozzarella, basil & tomato sugo ", 16.99, R.drawable.vesuvius));
        products.add(new Product("Maradona ", "Ingredients: Anchovies, capers, oregano, olives, basil,\n" +
                "garlic, fresh mozzarella & tomato sugo ", 14.99, R.drawable.maradona));
        return products;
    }

    // constructor to assign name, description, price and image to instance variable
    public Product(String name, String description, double price, int imageResource) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
    }


    // getter methods for name, description, price and image
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
