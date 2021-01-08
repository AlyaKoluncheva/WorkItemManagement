package com.telerikacademy.oop.application.models.workItems;

import com.telerikacademy.oop.application.models.workItems.contracts.Assignable;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

public abstract class AssignableImpl extends WorkItemImpl implements Assignable {

    private String assignee = "";
    private Priority priority;

    public AssignableImpl(String boardName, String title, String description, Priority priority, Status status, String assignee) {
        super(boardName, title, description, status);

        setPriority(priority);
        assignItemToMember(assignee);
    }

    public AssignableImpl(String boardName, String title, String description, Priority priority, Status status) {
        super(boardName, title, description, status);

        setPriority(priority);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    private void setPriority(Priority priority) {
        logHistory("Priority set to:" + priority);
        this.priority = priority;
    }

    public void changePriority(Priority priority) {
        setPriority(priority);
    }

    @Override
    public void assignItemToMember(String name) {
        logHistory("Assignee set to:" + assignee);
        this.assignee = name;
    }

    @Override
    public void unassign() {
        logHistory("Assignee was deleted from :" + getTitle());
        this.assignee = "";
    }

    @Override
    public String getAssignableTitle() {
        return getTitle();
    }

    @Override
    public String getAssignableID() {
        return getID();
    }
}
