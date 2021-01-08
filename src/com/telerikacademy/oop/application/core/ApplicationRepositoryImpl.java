package com.telerikacademy.oop.application.core;

import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.contracts.Team;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final List<Member> ratings = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Team> teamList = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<WorkItem> workItems = new ArrayList<>();
    private final List<Assignable> assignableWorkItems = new ArrayList<>();
    private final List<StoryImpl> stories = new ArrayList<>();
    private final List<FeedbackImpl> feedbacks = new ArrayList<>();
    private final List<BugImpl> bugs = new ArrayList<>();

    @Override
    public void addTeam(Team team) {
        teamList.add(team);
    }

    @Override
    public void addFeedback(FeedbackImpl feedback) {
        workItems.add(feedback);
        feedbacks.add(feedback);
    }

    @Override
    public void addStory(StoryImpl story) {
        workItems.add(story);
        assignableWorkItems.add(story);
        stories.add(story);
    }

    @Override
    public void addBug(BugImpl bug) {
        workItems.add(bug);
        assignableWorkItems.add(bug);
        bugs.add(bug);
    }

    @Override
    public Bug getBugID(String id) {
        return this.getBugs().stream()
                .filter(it -> it.getID().equals(id))
                .findFirst().get();
    }

    @Override
    public Story getStoryID(String id) {
        return this.getStories().stream()
                .filter(it -> it.getID().equals(id))
                .findFirst().get();
    }

    @Override
    public Feedback getFeedbackID(String id) {
        return this.getFeedbacks().stream()
                .filter(it -> it.getID().equals(id))
                .findFirst().get();
    }

    @Override
    public List<Assignable> getAssignables() {

        var result = new ArrayList<Assignable>();

        result.addAll(getBugs());
        result.addAll(getStories());

        return result;
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<WorkItem> getWorkItems() {
        return new ArrayList<>(workItems);
    }

    @Override
    public List<StoryImpl> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public Assignable getAssignableByName(String name) {
        if (assignableWorkItems.stream().noneMatch(it -> it.getAssignableTitle().equals(name))) {
            throw new IllegalArgumentException("No assignable item with this name");
        }

        return assignableWorkItems.stream().filter(it -> it.getAssignableTitle().equals(name)).findFirst().get();
    }

    @Override
    public Assignable getAssignableById(String id) {
        if (assignableWorkItems.stream().noneMatch(it -> it.getAssignableID().equals(id))) {
            throw new IllegalArgumentException("No assignable item with this id");
        }

        return assignableWorkItems.stream().filter(it -> it.getAssignableID().equals(id)).findFirst().get();
    }

    @Override
    public List<FeedbackImpl> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<BugImpl> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public Member getMemberByName(String name) {
        return members.stream().filter(it -> it.getName().equals(name)).findFirst().get();
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teamList);
    }

    @Override
    public WorkItem getWorkItemByID(String id) {
        return workItems.stream()
                .filter(it -> it.getID().equals(id))
                .findFirst().get();
    }

    @Override
    public WorkItem getWorkItemByName(String title) {
        return workItems.stream()
                .filter(it -> it.getTitle().equals(title))
                .findFirst().get();
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
    }
}