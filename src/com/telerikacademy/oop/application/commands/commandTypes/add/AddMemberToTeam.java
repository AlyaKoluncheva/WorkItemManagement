package com.telerikacademy.oop.application.commands.commandTypes.add;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.application.MemberImpl;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.contracts.Team;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class AddMemberToTeam implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final ApplicationRepository applicationRepository;

    String teamName;
    String memberName;

    public AddMemberToTeam(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);
        parseParameters(parameters);

        return addMemberToTeam(teamName, memberName);
    }

    private String addMemberToTeam(String teamName, String memberName) {

        if (applicationRepository.getTeams().stream().noneMatch(it -> it.getName().equals(teamName))) {
            return String.format(CommandConstants.TEAM_DOES_NOT_EXIST, teamName);
        }
        if (applicationRepository.getMembers().stream().noneMatch(it -> it.getName().equals(memberName))) {
            return String.format(CommandConstants.MEMBER_NOT_FOUND, memberName);
        }

        Member member = applicationRepository.getMembers().stream().filter(it -> it.getName().equals(memberName)).findFirst().get();
        applicationRepository.getTeams().stream().filter(it -> it.getName().equals(teamName)).findFirst().get().addMemberToTeam(member);

        return String.format(CommandConstants.MEMBER_ADDED_TO_TEAM_CREATED_SUCCESS_MESSAGE, memberName, teamName);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(
                    String.format("%s%n, Expected: %d%n, Given: %d%n",
                            INVALID_NUMBER_OF_ARGUMENTS,
                            EXPECTED_NUMBER_OF_ARGUMENTS,
                            parameters.size()));
        }
    }

    void parseParameters(List<String> parameters) {
        teamName = parameters.get(0);
        memberName = parameters.get(1);
    }
}
