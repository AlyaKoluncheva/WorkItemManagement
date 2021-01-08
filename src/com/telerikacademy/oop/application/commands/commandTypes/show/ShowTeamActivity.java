
package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.contracts.Team;

import java.util.List;
import java.util.stream.Collectors;

public class ShowTeamActivity implements Command {

    private final ApplicationRepository applicationRepository;
    private String teamName;
    private final List<Board> boardList;
    private final List<Team> teamList;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowTeamActivity(ApplicationRepository applicationRepository) {
        boardList = applicationRepository.getBoards();
        teamList = applicationRepository.getTeams();
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        // Validation:
        if (boardList.size() == 0) {
            throw new IllegalArgumentException(CommandConstants.BOARD_EMPTY);
        }

        if (teamList.size() == 0) {
            throw new IllegalArgumentException(CommandConstants.TEAM_EMPTY);
        }

        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        teamName = parameters.get(0);

        List<Team> teamList = applicationRepository.getTeams().stream().filter(it -> it.getName()
                .equals(teamName)).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Team name: ").append(teamName).append("\n");

        teamList.forEach(it -> stringBuilder.append(it.getHistory()));

        return stringBuilder.toString();
    }

}
