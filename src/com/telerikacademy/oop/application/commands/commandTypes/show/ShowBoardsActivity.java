package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Board;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.List;
import java.util.stream.Collectors;

public class ShowBoardsActivity implements Command {

    private final ApplicationRepository applicationRepository;
    private String boardName;
    private final List<Board> boardList;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowBoardsActivity(ApplicationRepository applicationRepository) {
        boardList = applicationRepository.getBoards();
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        // Validation:
        if (boardList.size() == 0) {
            throw new IllegalArgumentException(CommandConstants.BOARD_EMPTY);
        }

        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        boardName = parameters.get(0);

        List<WorkItem> workItemList = applicationRepository.getWorkItems().stream().filter(it -> it.getBoardName()
                .equals(boardName)).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Board name: ").append(boardName).append("\n");

        workItemList.forEach(it -> stringBuilder.append(it.getHistory()));

        return stringBuilder.toString();
    }

}
