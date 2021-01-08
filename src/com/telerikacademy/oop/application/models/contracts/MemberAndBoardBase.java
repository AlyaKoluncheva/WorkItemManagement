package com.telerikacademy.oop.application.models.contracts;

import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.List;

public interface MemberAndBoardBase {

    List<String> getActivityHistory();

    List<WorkItem> getItems();

    String getName();

    String toString();

    void addWorkItem(WorkItem workItem);

    void removeWorkItem(String id);
}
