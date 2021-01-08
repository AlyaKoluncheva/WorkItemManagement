package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Member;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class ShowAllTeamMembers implements Command {

    private final List<Member> members;

    public ShowAllTeamMembers(ApplicationRepository applicationRepository) {
        members = applicationRepository.getMembers();
    }

    @Override
    public String execute(List<String> parameters) {
        if (members.size() == 0) {
            return "There are no people.";
        }

        List<String> showAllMembersList = showAllMembersList();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), showAllMembersList).trim();
    }

    private List<String> showAllMembersList() {
        List<String> showAllMembers = new ArrayList<>();
        for (Member member : members) {
            showAllMembers.add(showAllMembers.toString());
        }
        return showAllMembers;
    }
}

