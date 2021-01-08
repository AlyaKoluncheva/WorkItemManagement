package com.telerikacademy.oop.application.models.application;

import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.List;

public class MemberImpl extends MemberAndBoardBaseImpl implements Member {

    public MemberImpl(String name,
                      List<WorkItem> listOfWorkItems,
                      List<String> activityHistory) {
        super(name, listOfWorkItems, activityHistory);
    }

    public MemberImpl(String name) {
        super(name);
    }
}
