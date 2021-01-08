package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeams implements Command {
    private final List<Team> teams;

    public ShowAllTeams(ApplicationRepository agencyRepository) {
        teams = agencyRepository.getTeams();
    }

    public String execute(List<String> parameters) {
        if (teams.size() == 0) {
            return "There are no teams";
        }

        List<String> showTeams = teamsToString();

        return String.join(System.lineSeparator(), showTeams).trim();
    }

    private List<String> teamsToString() {
        List<String> stringifiedTeams = new ArrayList<>();
        for (Team teams : teams) {
            stringifiedTeams.add(teams.toString());
        }
        return stringifiedTeams;
    }
}
