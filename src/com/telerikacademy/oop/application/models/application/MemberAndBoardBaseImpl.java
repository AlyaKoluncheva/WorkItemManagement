package com.telerikacademy.oop.application.models.application;

import com.telerikacademy.oop.application.models.contracts.MemberAndBoardBase;
import com.telerikacademy.oop.application.models.workItems.helperClasses.Validation;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MemberAndBoardBaseImpl implements MemberAndBoardBase {

    private String name;
    private List<WorkItem> listOfWorkItems = new ArrayList<>();
    private List<String> activityHistory = new ArrayList<>();

    public MemberAndBoardBaseImpl(String name, List<WorkItem> listOfWorkItems, List<String> activityHistory) {
        setName(name);
        setListOfWorkItems(listOfWorkItems);
        setActivityHistory(activityHistory);
    }

    public MemberAndBoardBaseImpl(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<WorkItem> getItems() {
        return new ArrayList<>(listOfWorkItems);
    }

    @Override
    public List<String> getActivityHistory() {
        return activityHistory;
    }

    private void setName(String name) {
        Validation.validateNotNull(name);
        Validation.validateValue(name, 15, 5, "Invalid name");
        this.name = name;
    }

    private void setListOfWorkItems(List<WorkItem> listOfWorkItems) {
        this.listOfWorkItems = new ArrayList<>(listOfWorkItems);
    }

    private void setActivityHistory(List<String> activityHistory) {
        this.activityHistory = new ArrayList<>(activityHistory);
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %s",
                getClass().toString(),
                name);
    }

    @Override
    public void addWorkItem(WorkItem workItem) {
        this.listOfWorkItems.add(workItem);
    }

    @Override
    public void removeWorkItem(String id) {
        listOfWorkItems = listOfWorkItems.stream().filter(it -> !it.getID().equals(id)).collect(Collectors.toList());
    }
}