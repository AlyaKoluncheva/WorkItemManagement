package com.telerikacademy.oop.application.commands.commandTypes.list;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugs implements Command {

    private static final String JOIN_DELIMITER = "####################";

    private final List<BugImpl> bugs;

    public ListAllBugs(ApplicationRepository applicationRepository) {
        bugs = applicationRepository.getBugs();
    }

    @Override
    public String execute(List<String> parameters) {
        if (bugs.size() == 0) {
            return "There are no registered bugs.";
        }

        List<String> listBugs = bugsToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listBugs).trim();
    }

    private List<String> bugsToString() {
        List<String> stringifiedBugs = new ArrayList<>();
        for (WorkItem workItem : bugs) {
            stringifiedBugs.add(workItem.toString());
        }
        return stringifiedBugs;
    }
}
