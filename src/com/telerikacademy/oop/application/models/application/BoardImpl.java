package com.telerikacademy.oop.application.models.application;

import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.List;

public class BoardImpl extends MemberAndBoardBaseImpl implements Board {
    public BoardImpl(String name,
                     List<WorkItem> listOfWorkItems,
                     List<String> activityHistory) {
        super(name, listOfWorkItems, activityHistory);
    }

    public BoardImpl(String name) {
        super(name);
    }
}
