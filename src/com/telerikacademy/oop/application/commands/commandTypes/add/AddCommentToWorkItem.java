package com.telerikacademy.oop.application.commands.commandTypes.add;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import com.telerikacademy.oop.application.models.workItems.helperClasses.EventLog;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class AddCommentToWorkItem implements Command {

    ApplicationRepository applicationRepository;
    ApplicationFactory applicationFactory;

    String commentDescription;
    String workItemTitle;

    public AddCommentToWorkItem(ApplicationRepository applicationRepository, ApplicationFactory applicationFactory) {
        this.applicationRepository = applicationRepository;
        this.applicationFactory = applicationFactory;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);
        parseParameters(parameters);

        WorkItem workItem = applicationRepository.getWorkItems().stream().filter(it -> it.getTitle().equals(workItemTitle)).findFirst().get();
        EventLog comment = applicationFactory.createComment(commentDescription);
        workItem.addComment(comment);

        return String.format(CommandConstants.COMMENT_ADDED_TO_WORK_ITEM_CREATED_SUCCESS_MESSAGE, commentDescription, workItemTitle);
    }

    private void validateInput(List<String> parameters) {
        int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
        if (parameters.size() != EXPECTED_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(
                    String.format("%s%n Expected: %d%n Given: %d%n",
                            INVALID_NUMBER_OF_ARGUMENTS,
                            EXPECTED_NUMBER_OF_ARGUMENTS,
                            parameters.size()));
        }
    }

    void parseParameters(List<String> parameters) {
        workItemTitle = parameters.get(0);
        commentDescription = parameters.get(1);
    }
}
