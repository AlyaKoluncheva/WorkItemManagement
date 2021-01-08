package com.telerikacademy.oop.application.commands.commandTypes.filter;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class FilterWorkItemsByAssignee implements Command {

    public static final int NUMBER_OF_ARGUMENTS = 1;

    private ApplicationRepository applicationRepository;

    private String assigneeName;

    public FilterWorkItemsByAssignee(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        assigneeName = parameters.get(0);

        if (applicationRepository.getMembers().stream().noneMatch(it -> it.getName().equals(assigneeName))) {
            return String.format(CommandConstants.MEMBER_NOT_FOUND, assigneeName);
        }

        List<BugImpl> bugs = applicationRepository.getBugs().stream()
                .filter(it -> it.getAssignee().equals(assigneeName)).collect(Collectors.toList());

        List<StoryImpl> stories = applicationRepository.getStories().stream()
                .filter(it -> it.getAssignee().equals(assigneeName)).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Bugs assigned to ").append(assigneeName).append("\n");

        bugs.forEach(it -> stringBuilder.append(it.getTitle()).append(" "));

        stringBuilder.append("\n");

        stringBuilder.append("Stories assigned to ").append(assigneeName).append("\n");

        stories.forEach(it -> stringBuilder.append(it.getTitle()).append(" "));

        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
