package com.telerikacademy.oop.application.commands.commandTypes.sort;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class SortWorkItemsBySeverity implements Command {

    private final List<BugImpl> assignableList;

    public SortWorkItemsBySeverity(ApplicationRepository applicationRepository) {
        assignableList = applicationRepository.getBugs();
    }

    @Override
    public String execute(List<String> parameters) {
        if (assignableList.size() == 0) {
            return "There are no work items.";
        }

        List<BugImpl> sortedUsers = assignableList.stream()
                .sorted(Comparator.comparing(BugImpl::getSeverity))
                .collect(Collectors.toList());

        String sortedList = sortedUsers.toString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), sortedList).trim();
    }
}
