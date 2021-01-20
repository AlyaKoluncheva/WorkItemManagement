package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.models.workItems.contracts.Story;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;


public class StoryImpl extends AssignableBase implements Story {

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


    // Event Log:
    @Override
    public String viewInfo() {
        String baseInfo = super.viewInfo();

        return String.format("Feedback: %s, " +
                        "Description: %s",
                baseInfo,
                getDescription());
    }

    // Additional functions:
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