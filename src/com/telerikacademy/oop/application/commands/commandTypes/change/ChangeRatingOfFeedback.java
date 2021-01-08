package com.telerikacademy.oop.application.commands.commandTypes.change;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ChangeRatingOfFeedback implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final ApplicationRepository applicationRepository;

    public ChangeRatingOfFeedback(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String id = parameters.get(0);
        int rating = Integer.parseInt(parameters.get(1));

        return changePriority(id, rating);
    }

    private String changePriority(String id, int rating) {

        if (applicationRepository.getFeedbacks().stream().noneMatch(it -> it.getID().equals(id))) {
            return String.format(CommandConstants.ID_NOT_FOUND_ERROR_MESSAGE, id);
        }

        FeedbackImpl feedback = (FeedbackImpl) applicationRepository.getFeedbackID(id); // TODO
        if (feedback.getRating() == rating) {
            return String.format("Feedback ID: %s Rating already set to: %s", id, rating);
        } else feedback.changeRating(rating);

        return String.format("Feedback ID: %s changed rating to: %s", id, rating);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(
                    String.format("%s%n Expected: %d%n Given: %d%n",
                            INVALID_NUMBER_OF_ARGUMENTS,
                            EXPECTED_NUMBER_OF_ARGUMENTS,
                            parameters.size()));
        }
    }
}
