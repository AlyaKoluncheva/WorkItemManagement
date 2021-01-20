package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.models.workItems.contracts.Bug;
import com.telerikacademy.oop.application.models.workItems.enums.*;

import java.util.List;

public class BugImpl extends AssignableBase implements Bug {

    List<String> steps;
    Severity severity;

    public BugImpl(String boardName,
                   String title,
                   String description,
                   Priority priority,
                   Severity severity,
                   Status status,
                   List<String> steps,
                   String assignee) {
        super(boardName, title, description, priority, status, assignee);

        setSeverity(severity);
        setSteps(steps);
    }

    public BugImpl(String boardName,
                   String title,
                   String description,
                   Priority priority,
                   Severity severity,
                   Status status,
                   List<String> steps) {
        super(boardName, title, description, priority, status);

        setSeverity(severity);
        setSteps(steps);
    }

    // Getters:
    @Override
    public List<String> getSteps() {
        return steps;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    // Setters:
    private void setSteps(List<String> steps) {
        logHistory("Steps set to set to:" + steps);
        this.steps = steps;
    }

    private void setSeverity(Severity severity) {
        logHistory("Severity set to:" + severity);
        this.severity = severity;
    }

    // Event Log:
    @Override
    public String viewInfo() {
        String baseInfo = super.viewInfo();

        return String.format("Feedback: %s," +
                        "Description: %s",
                baseInfo,
                getDescription());
    }

    @Override
    public String viewComments() {
        String baseInfo = super.viewComments();

        return String.format("Feedback comments: %s",
                baseInfo);
    }

    // Additional methods:

    public void changeSeverity(Severity severity) {
        setSeverity(severity);
    }

    @Override
    public String toString() {
        return String.format(CommandConstants.BUG_TO_STRING,
                super.toString(),
                getSeverity(),
                getSteps()
        );
    }
}