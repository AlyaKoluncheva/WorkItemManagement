package com.telerikacademy.oop.application.commands.commandTypes.sort;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class SortWorkingItemsByRating implements Command {

    private final List<FeedbackImpl> feedbackList;
    String sortedList;

    public SortWorkingItemsByRating(ApplicationRepository applicationRepository) {
        feedbackList = applicationRepository.getFeedbacks();
    }

    @Override
    public String execute(List<String> parameters) {
        if (feedbackList.size() == 0) {
            return "There are no work items with rating.";
        }

        List<FeedbackImpl> sortedUsers = feedbackList.stream()
                .sorted(Comparator.comparing(FeedbackImpl::getRating))
                .collect(Collectors.toList());

        sortedList = sortedUsers.toString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), sortedList).trim();
    }
}
