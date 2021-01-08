package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;

public class CreateFeedbackInBoard implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments!";

    private final ApplicationFactory factory;
    private final ApplicationRepository applicationRepository;

    private String title;
    private String description;
    private int rating;
    private Status status;
    private String boardName;

    public CreateFeedbackInBoard(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);
        parseParameters(parameters);

        if (status != Status.New && status != Status.Scheduled && status != Status.Unscheduled && status != Status.Done) {
            return CommandConstants.INVALID_STATUS_FEEDBACK;
        }

        if (applicationRepository.getBoards().stream().noneMatch(it -> it.getName().equals(boardName))) {
            return String.format(CommandConstants.BOARD_DOES_NOT_EXIST, boardName);
        }

        FeedbackImpl feedback = factory.createFeedback(boardName, title, description, rating, status);
        applicationRepository.addFeedback(feedback);
        applicationRepository.getBoards().stream().filter(it -> it.getName().equals(boardName)).findFirst().get().addWorkItem(feedback);

        return String.format("Feedback with ID %s was created.", feedback.getID());
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(
                    String.format("%s%n, Expected: %d%n, Given: %d%n",
                            INVALID_NUMBER_OF_ARGUMENTS,
                            EXPECTED_NUMBER_OF_ARGUMENTS,
                            parameters.size()));
        }
    }

    private void parseParameters(List<String> parameters) {
        try {

            boardName = parameters.get(0);
            title = parameters.get(1);
            description = parameters.get(2);
            rating = Integer.parseInt(parameters.get(3));
            status = Status.valueOf(parameters.get(4));

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CREATEFEEDBACK command parameters.");
        }
    }
}
