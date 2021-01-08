package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.models.workItems.contracts.Feedback;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class FeedbackImpl extends WorkItemImpl implements Feedback {
    private int rating;

    public FeedbackImpl(String boardName,
                        String title,
                        String description,
                        int rating,
                        Status status) {

        super(boardName, title, description, status);

        setRating(rating);
    }

    @Override
    public int getRating() {
        logHistory("Rating set to:" + rating);
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    public void changeRating(int rating) {
        setRating(rating);
    }

    @Override
    public String viewInfo() {
        String baseInfo = super.viewInfo();

        return String.format("Feedback: %s, " +
                        "Description: %s",
                baseInfo,
                getDescription());
    }

    @Override
    public String viewComments() {
        String baseInfo = super.viewComments();

        return String.format("Feedback comments: %s",
                baseInfo);
    }

    public String toString() {
        return String.format(
                CommandConstants.FEEDBACK_TO_STRING,
                super.toString(),
                getRating()
        );
    }
}
