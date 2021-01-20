package com.telerikacademy.oop.application.commands.commandTypes.filter;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class FilterWorkItemsByStatusAndAssignee implements Command {

    public static final int PARAMETERS = 2;
    private final ApplicationRepository applicationRepository;

    private Status status;
    private String assigneeName;

    public FilterWorkItemsByStatusAndAssignee(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        if (parameters.size() != PARAMETERS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        try {
            status = Status.valueOf(parameters.get(0));
        } catch (Throwable e) {
            return CommandConstants.INVALID_STATUS;
        }

        assigneeName = parameters.get(1);

        if (applicationRepository.getMembers()
                .stream()
                .noneMatch(it -> it.getName()
                        .equals(assigneeName))) {
            return String.format(CommandConstants.MEMBER_NOT_FOUND, assigneeName);
        }

        List<BugImpl> bugs = applicationRepository
                .getBugs()
                .stream()
                .filter(it -> it.getAssignee().equals(assigneeName))
                .filter(it -> it.getStatus().equals(status))
                .collect(Collectors.toList());

        List<StoryImpl> stories = applicationRepository
                .getStories()
                .stream()
                .filter(it -> it.getAssignee().equals(assigneeName))
                .filter(it -> it.getStatus().equals(status))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Bugs assigned to ")
                .append(assigneeName)
                .append(" with status ")
                .append(status.toString())
                .append("\n");

        bugs.forEach(it -> stringBuilder.append(it.getTitle()).append(" "));

        stringBuilder.append("\n");

        stringBuilder.append("Stories assigned to ")
                .append(assigneeName)
                .append(" with status ")
                .append(status.toString())
                .append("\n");

        stories.forEach(it -> stringBuilder.append(it.getTitle()).append(" "));

        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
