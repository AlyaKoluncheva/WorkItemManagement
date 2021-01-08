package com.telerikacademy.oop.application.core.contracts;

import com.telerikacademy.oop.application.commands.contracts.Command;

public interface CommandFactory {

    Command createCommand(String commandTypeAsString, ApplicationFactory factory, ApplicationRepository applicationRepository);

}