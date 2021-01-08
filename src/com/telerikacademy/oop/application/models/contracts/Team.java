package com.telerikacademy.oop.application.models.contracts;

import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    void addMemberToTeam(Member member);

    void addBoard(Board board);

    void changeName(String name);

    String getHistory();
}
