package com.telerikacademy.oop.application.commands.commandTypes.list;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class ListAllWorkItems implements Command {

    private final List<WorkItem> workItems;

    public ListAllWorkItems(ApplicationRepository applicationRepository) {
        workItems = applicationRepository.getWorkItems();
    }

    @Override
    public String execute(List<String> parameters) {
        if (workItems.size() == 0) {
            return "There are no registered Work Items.";
        }

        List<String> listItems = workItemsToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listItems).trim();
    }

    private List<String> workItemsToString() {
        List<String> stringifiedItems = new ArrayList<>();
        for (WorkItem workItem : workItems) {
            stringifiedItems.add(workItem.toString());
        }
        return stringifiedItems;
    }
}
