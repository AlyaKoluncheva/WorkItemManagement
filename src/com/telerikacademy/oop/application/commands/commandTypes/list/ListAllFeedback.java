package com.telerikacademy.oop.application.commands.commandTypes.list;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class ListAllFeedback implements Command {
    private static final String JOIN_DELIMITER = "####################";
    private final List<FeedbackImpl> feedback;

    public ListAllFeedback(ApplicationRepository applicationRepository) {
        feedback = applicationRepository.getFeedbacks();
    }

    @Override
    public String execute(List<String> parameters) {
        if (feedback.size() == 0) {
            return "There is no registered feedback.";
        }

        List<String> listFeedback = feedbackToString();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), listFeedback).trim();
    }

    private List<String> feedbackToString() {
        List<String> stringifiedFeedback = new ArrayList<>();
        for (WorkItem workItem : feedback) {
            stringifiedFeedback.add(workItem.toString());
        }
        return stringifiedFeedback;
    }
}
