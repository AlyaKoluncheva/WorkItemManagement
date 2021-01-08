package com.telerikacademy.oop.application.models.workItems.contracts;

import com.telerikacademy.oop.application.models.workItems.enums.Priority;

public interface Assignable {

    Priority getPriority();

    String getAssignee();

    void assignItemToMember(String name);

    void unassign();

    String getAssignableTitle();

    String getAssignableID();
}
