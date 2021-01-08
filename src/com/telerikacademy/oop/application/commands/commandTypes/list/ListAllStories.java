package com.telerikacademy.oop.application.commands.commandTypes.list;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class ListAllStories implements Command {

    private static final String JOIN_DELIMITER = "####################";
    private final List<StoryImpl> stories;

    public ListAllStories(ApplicationRepository applicationRepository) {
        stories = applicationRepository.getStories();
    }

    @Override
    public String execute(List<String> parameters) {
        if (stories.size() == 0) {
            return "There are no registered stories.";
        }

        List<String> listStories = storiesToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listStories).trim();
    }

    private List<String> storiesToString() {
        List<String> stringifiedStories = new ArrayList<>();
        for (WorkItem workItem : stories) {
            stringifiedStories.add(workItem.toString());
        }
        return stringifiedStories;
    }
}
