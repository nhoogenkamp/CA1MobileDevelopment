// FeedbackContract.java
package com.example.ca1mobiledevelopment;

// This class defines the contract between view and the presenter for the FAQ feature

public interface FeedbackContract {

    interface View {
        // Methods for displaying a message or error when submitting feedback
        void showFeedbackSubmittedMessage();
        void showFeedbackSubmissionError();
    }

    interface Presenter {
        // Method for handling feedback submission
        void submitFeedback(String userName, String comments, int rating);
    }
}
