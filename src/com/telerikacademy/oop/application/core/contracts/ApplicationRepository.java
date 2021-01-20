package com.telerikacademy.oop.application.core.contracts;

import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.contracts.Team;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.*;

import java.util.List;

public interface ApplicationRepository {
    // Getters
    List<WorkItem> getWorkItems();

    List<FeedbackImpl> getFeedbacks();

    List<StoryImpl> getStories();

    Assignable getAssignableByName(String name);

    Assignable getAssignableById(String id);

    List<Board> getBoards();

    List<Team> getTeams();

    List<BugImpl> getBugs();

    List<Member> getMembers();

    Member getMemberByName(String name);

    WorkItem getWorkItemByID(String id);

    WorkItem getWorkItemByName(String title);

    // Add items
    void addMember(Member member);

    void addBoard(Board board);

    void addTeam(Team team);

    void addFeedback(FeedbackImpl feedback);

    void addStory(StoryImpl story);

    void addBug(BugImpl bug);

    // Get IDs:

    Bug getBugID(String id);

    Story getStoryID(String id);

    Feedback getFeedbackID(String id);

    List<Assignable> getAssignables();
}