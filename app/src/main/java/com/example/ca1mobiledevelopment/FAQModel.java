// FAQModel.java
package com.example.ca1mobiledevelopment;
// This class acts as the model in the MVP architecture for the "About Us" feature where all the data is coming from.

public class FAQModel {
    //method to retrieve FAQ content
    public String getFAQContent() {
// In a real application, I would retrieve company information from a
        // database or server but I don't know how yet and due to time constraints and other CA's have decided not to do it.
        return "Frequently Asked Questions:\n\n1. How do I place an order?\nGo to the homepage and click buy\n2. What payment methods do you accept?\nAt the moment everything is payable in cash upon delivery\n3. How long does delivery take?\nUnfortunately forever";
    }
}
