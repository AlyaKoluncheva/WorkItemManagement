package com.telerikacademy.oop.application.models.workItems.contracts;

import com.telerikacademy.oop.application.models.workItems.enums.Status;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;

public interface WorkItem {
    String getDescription();

    String getID();

    String getComments();

    String getHistory();

    String getTitle();

    Status getStatus();

    void changeStatus(Status status);

    String getBoardName();

    void addComment(EventLog comment);

    String toString();
}
