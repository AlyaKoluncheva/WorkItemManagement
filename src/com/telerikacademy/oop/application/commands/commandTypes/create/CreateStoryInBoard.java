package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;

public class CreateStoryInBoard implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments!";

    private final ApplicationFactory factory;
    private final ApplicationRepository agencyRepository;

    private String title;
    private String description;
    private Priority priority;
    private Size size;
    private Status status;
    private String assignee;
    private String boardName;

    public CreateStoryInBoard(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.agencyRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);
        parseParameters(parameters);

        if (status != Status.NotDone && status != Status.InProgress && status != Status.Done) {
            return CommandConstants.INVALID_STATUS_STORY;
        }

        if (agencyRepository.getBoards().stream().noneMatch(it -> it.getName().equals(boardName))) {
            return String.format(CommandConstants.BOARD_DOES_NOT_EXIST, boardName);
        }

        //StoryImpl story = factory.createStory(title, description, priority, size, status, assignee);
        StoryImpl story = factory.createStory(boardName, title, description, priority, size, status, assignee);
        agencyRepository.addStory(story);

        agencyRepository.getBoards().stream().filter(it -> it.getName().equals(boardName)).findFirst().get().addWorkItem(story);

        return String.format("Story with ID %s was created.", story.getID());
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
            priority = Priority.valueOf(parameters.get(3));
            size = Size.valueOf(parameters.get(4));
            status = Status.valueOf(parameters.get(5));
            assignee = parameters.get(6);

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CREATESTORY command parameters.");
        }
    }
}
