package com.telerikacademy.oop.application.core;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.*;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.core.factories.CommandFactoryImpl;
import com.telerikacademy.oop.application.core.providers.CommandParserImpl;
import com.telerikacademy.oop.application.core.providers.ConsoleReader;
import com.telerikacademy.oop.application.core.providers.ConsoleWriter;

import java.util.List;

public class ApplicationEngineImpl implements Engine {

    private static final String TERMINATION_COMMAND = "Exit";

    private final ApplicationRepository applicationRepository;
    private final ApplicationFactory applicationFactory;
    private final CommandFactory commandFactory;
    private final CommandParser commandParser;
    private final Reader reader;
    private final Writer writer;

    public ApplicationEngineImpl() {
        this.applicationRepository = new ApplicationRepositoryImpl();
        this.applicationFactory = new ApplicationFactoryImpl();
        this.commandFactory = new CommandFactoryImpl();
        this.commandParser = new CommandParserImpl();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);

            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage()
                        .isEmpty() ? ex.getMessage() : ex.toString());
            }
        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }

        String commandName = commandParser.parseCommand(commandAsString);
        Command command = commandFactory.createCommand(commandName, applicationFactory, applicationRepository);
        List<String> parameters = commandParser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
    }

}