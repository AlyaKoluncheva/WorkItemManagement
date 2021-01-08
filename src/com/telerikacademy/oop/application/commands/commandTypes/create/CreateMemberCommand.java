package com.telerikacademy.oop.application.commands.commandTypes.create;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Member;

import java.util.List;

import static com.telerikacademy.oop.application.commands.CommandConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class CreateMemberCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final ApplicationFactory factory;
    private final ApplicationRepository applicationRepository;

    public CreateMemberCommand(ApplicationFactory factory, ApplicationRepository applicationRepository) {
        this.factory = factory;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validateInput(parameters);

        String name = parameters.get(0);
        return createMember(name);
    }

    private String createMember(String name) {

        if (applicationRepository.getMembers().stream().anyMatch(it -> it.getName().equals(name))) {
            return String.format(CommandConstants.MEMBER_EXISTS_ERROR_MESSAGE, name);
        }

        Member member = factory.createMember(name);
        applicationRepository.addMember(member);

        return String.format(CommandConstants.MEMBER_CREATED_SUCCESS_MESSAGE, name);
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
