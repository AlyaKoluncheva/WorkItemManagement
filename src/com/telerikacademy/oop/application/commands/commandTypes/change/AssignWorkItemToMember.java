package com.telerikacademy.oop.application.commands.commandTypes.change;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.contracts.Assignable;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class AssignWorkItemToMember implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final ApplicationRepository applicationRepository;

    public AssignWorkItemToMember(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String name = parameters.get(0);
        String workItemName = parameters.get(1);
        return assignPerson(name, workItemName);
    }


    private String assignPerson(String name, String workItemName) {

        if (applicationRepository.getMembers().stream().noneMatch(it -> it.getName().equals(name))) {
            return String.format(CommandConstants.MEMBER_NOT_FOUND_ERROR_MESSAGE, name);
        }

        Assignable assignableItem;

        try {
            assignableItem = applicationRepository.getAssignableByName(workItemName);
        } catch (Throwable e) {
            return String.format(CommandConstants.NO_SUCH_ASSIGNABLE, workItemName);
        }

        WorkItem workItem = applicationRepository.getWorkItemByName(workItemName);

        assignableItem.assignItemToMember(name);

        applicationRepository.getMemberByName(name).addWorkItem(workItem);

        return String.format(CommandConstants.MEMBER_CREATED_SUCCESS_MESSAGE, name);
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
