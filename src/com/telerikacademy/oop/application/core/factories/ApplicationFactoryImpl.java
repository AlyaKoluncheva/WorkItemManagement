package com.telerikacademy.oop.application.core.factories;

import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.models.application.BoardImpl;
import com.telerikacademy.oop.application.models.application.MemberImpl;
import com.telerikacademy.oop.application.models.application.TeamImpl;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Severity;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;

import java.util.List;

public class ApplicationFactoryImpl implements ApplicationFactory {

    @Override
    public Member createMember(String name) {
        return new MemberImpl(name);
    }

    @Override
    public TeamImpl createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public StoryImpl createStory(String boardName, String title, String description, Priority priority,
                                 Size size, Status status, String assignee) {
        return new StoryImpl(boardName, title, description, priority, size, status, assignee);
    }

    @Override
    public BugImpl createBug(String boardName, String title, String description,
                             Priority priority, Severity severity,
                             Status status, List<String> steps,
                             String assignee
    ) {
        return new BugImpl(boardName, title, description, priority, severity, status, steps, assignee);
    }

    @Override
    public FeedbackImpl createFeedback(String boardName, String title, String description, int rating, Status status) {
        return new FeedbackImpl(boardName, title, description, rating, status);
    }

    @Override
    public BoardImpl createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public EventLog createComment(String comment) {
        return new EventLog(comment);
    }
}
