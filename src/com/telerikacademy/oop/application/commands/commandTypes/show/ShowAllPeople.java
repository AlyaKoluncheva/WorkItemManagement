package com.telerikacademy.oop.application.commands.commandTypes.show;

import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.models.contracts.Member;

import java.util.ArrayList;
import java.util.List;


public class ShowAllPeople implements Command {
    private final List<Member> people;

    public ShowAllPeople(ApplicationRepository applicationRepository) {
        people = applicationRepository.getMembers();
    }

    public String execute(List<String> parameters) {
        if (people.size() == 0) {
            return "There are no people";
        }

        List<String> showPeople = peopleToString();

        return String.join(System.lineSeparator(), showPeople).trim();
    }

    private List<String> peopleToString() {
        List<String> stringifyPeople = new ArrayList<>();
        for (Member person : people) {
            stringifyPeople.add(person.toString());
        }
        return stringifyPeople;
    }
}