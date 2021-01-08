package com.telerikacademy.oop.application.models.application;

import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.contracts.Team;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;
import com.telerikacademy.oop.application.models.workItems.helperClasses.Validation;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    public final List<EventLog> history = new ArrayList<>();

    private List<Member> members = new ArrayList<>();

    private List<Board> boards = new ArrayList<>();

    private String name;

    public TeamImpl(String name) {
        setName(name);
    }


    // Getters:
    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public String getHistory() {
        StringBuilder builder = new StringBuilder();

        for (EventLog event : history) {
            builder.append(event.viewHistory()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    // Setters:
    private void setName(String name) {
        Validation.validateNotNull(name);
        Validation.validateValue(name, 15, 5, "Invalid Team name");
        this.name = name;
    }

    // Add:
    @Override
    public void addMemberToTeam(Member member) {
        members.add(member);
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
    }

    // Additional methods:
    @Override
    public void changeName(String name) {
        setName(name);
    }

    public String toString() {
        return String.format(
                "Team: %s",
                name);
    }
}
