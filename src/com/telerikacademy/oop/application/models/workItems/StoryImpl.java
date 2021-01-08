package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.models.workItems.contracts.Story;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;


public class StoryImpl extends AssignableImpl implements Story {

    private Size size;

    public StoryImpl(
            String boardName,
            String title,
            String description,
            Priority priority,
            Size size,
            Status status,
            String assignee) {

        super(boardName, title, description, priority, status, assignee);

        setStatus(status);
        setSize(size);
    }

    public StoryImpl(
            String boardName,
            String title,
            String description,
            Priority priority,
            Size size,
            Status status) {

        super(boardName, title, description, priority, status);

        setSize(size);
    }

    // Getters:

    @Override
    public Size getSize() {
        return size;
    }

    // Setters:
    private void setSize(Size size) {
        logHistory("Size set to:" + size);
        this.size = size;
    }

    public void changeSize(Size size) {
        setSize(size);
    }

    // Additional functions:

    @Override
    public String viewInfo() {
        String baseInfo = super.viewInfo();

        return String.format("Feedback: %s, " +
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

    @Override
    public String toString() {
        return String.format(
                CommandConstants.STORY_TO_STRING,
                super.toString(),
                getSize()
        );
    }
}