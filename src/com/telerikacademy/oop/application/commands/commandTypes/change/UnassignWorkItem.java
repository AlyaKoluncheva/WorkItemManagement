package com.telerikacademy.oop.application.commands.commandTypes.change;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Member;
import com.telerikacademy.oop.application.models.workItems.contracts.Assignable;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class UnassignWorkItem implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final ApplicationRepository applicationRepository;

    public UnassignWorkItem(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String workItemId = parameters.get(0);
        return unassignMember(workItemId);
    }

    private String unassignMember(String workItemId) {
        Assignable workItem;
        try {
            workItem = applicationRepository.getAssignableById(workItemId);
        } catch (Throwable e) {
            return String.format(CommandConstants.NO_SUCH_ASSIGNABLE, workItemId);
        }

        if (!workItem.getAssignee().equals("")) {
            Member member = applicationRepository.getMemberByName(workItem.getAssignee());
            member.removeWorkItem(workItemId);

            workItem.unassign();
        }

        return String.format(CommandConstants.WORKITEM_UNASSIGNED_SUCCESS_MESSAGE, workItemId);
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
