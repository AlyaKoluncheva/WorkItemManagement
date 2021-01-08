package com.telerikacademy.oop.application.commands.commandTypes.sort;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class SortWorkItemsByTitle implements Command {

    private final List<WorkItem> workItemList;

    public SortWorkItemsByTitle(ApplicationRepository applicationRepository) {
        workItemList = applicationRepository.getWorkItems();
    }

    @Override
    public String execute(List<String> parameters) {
        if (workItemList.size() == 0) {
            return "There are no work items.";
        }

        List<WorkItem> sortedUsers = workItemList.stream()
                .sorted(Comparator.comparing(WorkItem::getTitle))
                .collect(Collectors.toList());

        String sortedList = sortedUsers.toString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), sortedList).trim();
    }
}
