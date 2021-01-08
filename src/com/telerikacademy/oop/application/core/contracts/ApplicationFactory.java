package com.telerikacademy.oop.application.core.contracts;

import com.telerikacademy.oop.application.models.application.BoardImpl;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.contracts.Team;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Severity;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;
import com.telerikacademy.oop.application.models.workItems.enums.*;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;

import java.util.List;

public interface ApplicationFactory {

    Member createMember(String name);

    Team createTeam(String name);

    StoryImpl createStory(String boardName,
                          String title,
                          String description,
                          Priority priority,
                          Size size,
                          Status status,
                          String assignee);

    BugImpl createBug(
            String boardName,
            String title,
            String description,
            Priority priority,
            Severity severity,
            Status status,
            List<String> steps,
            String assignee
    );

    FeedbackImpl createFeedback(String boardName, String title, String description, int rating, Status status);

    BoardImpl createBoard(String name);

    EventLog createComment(String comment);
}