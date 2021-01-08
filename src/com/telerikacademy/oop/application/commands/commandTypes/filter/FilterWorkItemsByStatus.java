package com.telerikacademy.oop.application.commands.commandTypes.filter;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class FilterWorkItemsByStatus implements Command {

    public static final int PARAMETERS = 1;
    private final ApplicationRepository applicationRepository;

    private Status status;

    public FilterWorkItemsByStatus(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        if (parameters.size() != PARAMETERS) {
            throw new IllegalArgumentException(CommandConstants.INVALID_NUMBER_OF_ARGUMENTS);
        }

        try {
            status = Status.valueOf(parameters.get(0));
        } catch (Throwable e) {
            return CommandConstants.INVALID_STATUS;
        }

        List<WorkItem> workItems = applicationRepository.getWorkItems().stream().filter(it -> it.getStatus() == status).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("All workItems with status ").append(status.toString()).append(" \n");
        workItems.forEach(it -> stringBuilder.append(it.getTitle()).append("\n"));

        return stringBuilder.toString();
    }
}
