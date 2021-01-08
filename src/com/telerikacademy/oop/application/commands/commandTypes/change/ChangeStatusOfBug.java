package com.telerikacademy.oop.application.commands.commandTypes.change;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ChangeStatusOfBug implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final ApplicationRepository applicationRepository;

    public ChangeStatusOfBug(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String id = parameters.get(0);
        Status status = Status.valueOf(parameters.get(1));

        return changeStatus(id, status);
    }

    private String changeStatus(String id, Status status) {

        if (applicationRepository.getBugs().stream().noneMatch(it -> it.getID().equals(id))) {
            return String.format(CommandConstants.ID_NOT_FOUND_ERROR_MESSAGE, id);
        }

        BugImpl bug = (BugImpl) applicationRepository.getBugID(id);
        if (bug.getStatus().equals(status)) {
            return String.format("Bug ID: %s Status already set to: %s", id, status);
        }

        if (status != Status.Active && status != Status.Fixed) {
            return CommandConstants.INVALID_STATUS_BUG;
        } else bug.changeStatus(status);

        return String.format("Bug ID: %s changed status to: %s", id, status);
    }

    private void validateInput(List<String> parameters) {
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(
                    String.format("%s%n Expected: %d%n Given: %d%n",
                            INVALID_NUMBER_OF_ARGUMENTS,
                            EXPECTED_NUMBER_OF_ARGUMENTS,
                            parameters.size()));
        }
    }
}
