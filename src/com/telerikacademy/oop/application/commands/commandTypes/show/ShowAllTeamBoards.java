package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.JOIN_DELIMITER;

public class ShowAllTeamBoards implements Command {

    private final List<Board> boardsList;

    public ShowAllTeamBoards(ApplicationRepository applicationRepository) {
        boardsList = applicationRepository.getBoards();
    }

    @Override
    public String execute(List<String> parameters) {
        if (boardsList.size() == 0) {
            return "There are no boards.";
        }

        List<String> showAllBoardsList = showAllBoardsList();

        return String.join(JOIN_DELIMITER + System.lineSeparator(), showAllBoardsList).trim();
    }

    private List<String> showAllBoardsList() {
        List<String> showAllBoards = new ArrayList<>();
        for (Board board : boardsList) {
            showAllBoards.add(showAllBoards.toString());
        }
        return showAllBoards;
    }
}
