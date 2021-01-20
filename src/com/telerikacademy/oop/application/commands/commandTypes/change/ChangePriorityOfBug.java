package com.telerikacademy.oop.application.commands.commandTypes.change;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ChangePriorityOfBug implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final ApplicationRepository applicationRepository;

    public ChangePriorityOfBug(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String id = parameters.get(0);
        Priority priority = Priority.valueOf(parameters.get(1));

        return changePriority(id, priority);
    }

    private String changePriority(String id, Priority priority) {

        if (applicationRepository.getBugs().stream().noneMatch(it -> it.getID().equals(id))) {
            return String.format(CommandConstants.ID_NOT_FOUND_ERROR_MESSAGE, id);
        }

        BugImpl bug = (BugImpl) applicationRepository.getBugID(id); // TODO
        if (bug.getPriority().equals(priority)) {
            return String.format("Bug ID: %s Priority already set to: %s", id, priority);
        } else bug.changePriority(priority);

        return String.format("Bug ID: %s changed priority to: %s", id, priority);
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
