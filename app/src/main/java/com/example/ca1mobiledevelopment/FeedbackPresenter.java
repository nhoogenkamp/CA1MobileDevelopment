// FeedbackPresenter.java
package com.example.ca1mobiledevelopment;
// This class acts as the presenter in the MVP architecture for the "Feedback" feature
//The presenter handles the presentation logic and acts between view interface and model
public class FeedbackPresenter implements FeedbackContract.Presenter {
    //referencing to the view interface and model
    private final FeedbackContract.View view;
    private final Feedback model;

    // constructor to start the view and model
    public FeedbackPresenter(FeedbackContract.View view, Feedback model) {
        this.view = view;
        this.model = model;
    }

    //method implementation of the submit feedback
    @Override
    public void submitFeedback(String userName, String comments, int rating) {
        // Submit feedback using the model
        boolean submissionSuccessful = model.submitFeedback();

        // Notify the view about the submission status (eventhough currently it will submit message)
        if (submissionSuccessful) {
            view.showFeedbackSubmittedMessage();
        } else {
            view.showFeedbackSubmissionError();
        }
    }
}
