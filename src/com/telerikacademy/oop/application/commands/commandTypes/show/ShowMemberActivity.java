package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.contracts.Member;

import java.util.List;
import java.util.stream.Collectors;

public class ShowMemberActivity implements Command {
    private final ApplicationRepository applicationRepository;
    private String memberName;
    private final List<Board> boardList;
    private final List<Member> memberList;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowMemberActivity(ApplicationRepository applicationRepository) {
        boardList = applicationRepository.getBoards();
        memberList = applicationRepository.getMembers();
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        // Validation:
        if (boardList.size() == 0) {
            throw new IllegalArgumentException(CommandConstants.BOARD_EMPTY);
        }

        if (memberList.size() == 0) {
            throw new IllegalArgumentException(CommandConstants.MEMBER_NOT_FOUND);
        }

        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        memberName = parameters.get(0);

        List<Member> memberList = applicationRepository.getMembers().stream().filter(it -> it.getName()
                .equals(memberName)).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Member name: ").append(memberName).append("\n");

        memberList.forEach(it -> stringBuilder.append(it.getHistory()));

        return stringBuilder.toString();
    }
}
