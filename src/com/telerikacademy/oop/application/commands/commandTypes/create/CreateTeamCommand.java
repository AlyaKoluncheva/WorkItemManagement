package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Team;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateTeamCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final ApplicationFactory factory;
    private final ApplicationRepository applicationRepository;

    private String name;

    public CreateTeamCommand(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        name = parameters.get(0);
        return createTeam(name);
    }

    private String createTeam(String name) {

        if (applicationRepository.getTeams().stream().anyMatch(it -> it.getName().equals(name))) {
            return String.format(CommandConstants.TEAM_EXISTS_ERROR_MESSAGE, name);
        }

        Team team = factory.createTeam(name);
        applicationRepository.addTeam(team);

        return String.format(CommandConstants.TEAM_CREATED_SUCCESS_MESSAGE, name);
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
}
