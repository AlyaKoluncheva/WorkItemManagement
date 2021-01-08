package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Severity;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;

public class CreateBugInBoard implements Command {

    private final ApplicationFactory factory;
    private final ApplicationRepository applicationRepository;

    private String title;
    private String description;
    List<String> steps;
    private Priority priority;
    private Severity severity;
    private Status status;
    private String assignee;

    private String boardName;

    public CreateBugInBoard(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        parseParameters(parameters);

        if (status != Status.Active && status != Status.Fixed) {
            return CommandConstants.INVALID_STATUS_BUG;
        }

        if (applicationRepository.getBoards().stream().noneMatch(it -> it.getName().equals(boardName))) {
            return String.format(CommandConstants.BOARD_DOES_NOT_EXIST, boardName);
        }

        if (applicationRepository.getMembers().stream().noneMatch(it -> it.getName().equals(assignee))) {
            return String.format(CommandConstants.MEMBER_NOT_FOUND, assignee);
        }

        BugImpl bug = factory.createBug(boardName, title, description, priority, severity, status, steps, assignee);
        applicationRepository.addBug(bug);
        applicationRepository.getBoards().stream().filter(it -> it.getName().equals(boardName)).findFirst().get().addWorkItem(bug);
        return String.format("Bug with ID %s was created.", bug.getID());
    }

    private void parseParameters(List<String> parameters) {
        try {
            boardName = parameters.get(0);
            title = parameters.get(1);
            description = parameters.get(2);
            priority = Priority.valueOf(parameters.get(3));
            severity = Severity.valueOf(parameters.get(4));
            status = Status.valueOf(parameters.get(5));
            assignee = parameters.get(6);
            steps = parameters.subList(7, parameters.size());

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CREATEBUG command parameters.");
        }
    }
}
