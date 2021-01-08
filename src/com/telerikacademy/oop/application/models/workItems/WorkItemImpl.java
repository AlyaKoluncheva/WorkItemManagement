package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import com.telerikacademy.oop.application.models.workItems.enums.Status;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;
import com.telerikacademy.oop.application.models.workItems.helperClasses.IdHandler;
import com.telerikacademy.oop.application.models.workItems.helperClasses.Validation;

import java.util.ArrayList;
import java.util.List;

public abstract class WorkItemImpl implements WorkItem {

    private final String id = IdHandler.getNewId();
    private String description;
    private String title;
    private Status status;
    private String boardName;

    private final List<EventLog> comments = new ArrayList<>();

    public final List<EventLog> history = new ArrayList<>();

    public WorkItemImpl(String boardName,
                        String title,
                        String description,
                        Status status) {
        setDescription(description);
        setTitle(title);
        setStatus(status);
        setBoardName(boardName);
    }

    // Getters:
    @Override
    public String getBoardName() {
        return boardName;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public String getComments() {
        StringBuilder commentsBuilder = new StringBuilder();

        for (EventLog event : comments) {
            commentsBuilder.append(event.viewComments()).append(System.lineSeparator());
        }

        return commentsBuilder.toString();
    }

    @Override
    public String getHistory() {
        StringBuilder builder = new StringBuilder();

        for (EventLog event : history) {
            builder.append(event.viewHistory()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    // Setters:
    private void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    private void setTitle(String title) {
        validateTitle(title);

        logHistory(String.format("Title set to: %s%n", title));

        this.title = title;
    }

    private void setDescription(String description) {
        validateDescription(description);

        logHistory(String.format("Description set to: %s%n", description));

        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Validations:

    private void validateTitle(String title) {
        Validation.validateValueInRange(
                title.length(),
                10,
                50,
                "Title should be between 10 and 50 signs");
    }

    private void validateDescription(String description) {
        Validation.validateValueInRange(
                description.length(),
                10,
                500,
                "Description should be between 10 and 500 signs");
    }

    // Event Log:
    @Override
    public void addComment(EventLog comment) {
        comments.add(comment);
        logComments(String.format("Comment: %s", comment.getDescription()));
    }

    public String viewComments() {
        return String.format("Comments: %s", comments);
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", id, title, description);
    }

    public void logHistory(String event) {
        history.add(new EventLog(event));
    }

    protected void logComments(String event) {
        comments.add(new EventLog(event));
    }

    // Additional methods:
    public void changeStatus(Status status) {
        setStatus(status);
    }

    @Override
    public String toString() {
        return String.format(CommandConstants.WORK_ITEM_TO_STRING,
                title, description, status.toString(), boardName);
    }


}
