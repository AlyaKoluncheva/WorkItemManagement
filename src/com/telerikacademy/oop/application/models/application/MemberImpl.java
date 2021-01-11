package com.telerikacademy.oop.application.models.application;

import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl extends MemberAndBoardBaseImpl implements Member {

    public final List<EventLog> history = new ArrayList<>();

    public MemberImpl(String name,
                      List<WorkItem> listOfWorkItems,
                      List<String> activityHistory) {
        super(name, listOfWorkItems, activityHistory);
    }

    public MemberImpl(String name) {
        super(name);
    }

    @Override
    public String getHistory() {
        StringBuilder builder = new StringBuilder();

        for (EventLog event : history) {
            builder.append(event.viewHistory()).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
