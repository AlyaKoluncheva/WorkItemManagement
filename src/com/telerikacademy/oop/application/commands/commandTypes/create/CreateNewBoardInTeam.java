package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateNewBoardInTeam implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final ApplicationFactory factory;
    private final ApplicationRepository applicationRepository;

    public CreateNewBoardInTeam(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        return createBoard(teamName, boardName);
    }

    private String createBoard(String teamName, String boardName) {

        if (applicationRepository.getBoards().stream().anyMatch(it -> it.getName().equals(boardName))) {
            return String.format(CommandConstants.BOARD_EXISTS_ERROR_MESSAGE, boardName);
        }

        if (applicationRepository.getTeams().stream().noneMatch(it -> it.getName().equals(teamName))) {
            return String.format(CommandConstants.TEAM_DOES_NOT_EXIST, teamName);
        }

        Board board = factory.createBoard(boardName);
        applicationRepository.addBoard(board);
        applicationRepository.getTeams().stream().filter(it -> it.getName().equals(teamName)).findFirst().get().addBoard(board);

        return String.format(CommandConstants.BOARD_CREATED_SUCCESS_MESSAGE, boardName);
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
