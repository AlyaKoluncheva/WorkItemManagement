package com.telerikacademy.oop.application.core.factories;

import com.telerikacademy.oop.application.commands.commandTypes.add.AddCommentToWorkItem;
import com.telerikacademy.oop.application.commands.commandTypes.add.AddMemberToTeam;
import com.telerikacademy.oop.application.commands.commandTypes.change.*;
import com.telerikacademy.oop.application.commands.commandTypes.create.*;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByAssignee;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByStatus;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByStatusAndAssignee;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllBugs;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllFeedback;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllStories;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllWorkItems;
import com.telerikacademy.oop.application.commands.commandTypes.show.*;
import com.telerikacademy.oop.application.commands.commandTypes.sort.*;
import com.telerikacademy.oop.application.commands.contracts.Command;
import com.telerikacademy.oop.application.commands.enums.CommandType;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.contracts.CommandFactory;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommand(String commandName, ApplicationFactory applicationFactory,
                                 ApplicationRepository applicationRepository) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case SORTWORKITEMSBYSEVERIRY:
                return new SortWorkItemsBySeverity(applicationRepository);
            case SORTWORKITEMSBYPRIORITY:
                return new SortWorkItemsByPriority(applicationRepository);
            case SORTWORKITEMSBYSIZE:
                return new SortWorkingItemsBySize(applicationRepository);
            case SORTWORKITEMSBYTITLE:
                return new SortWorkItemsByTitle(applicationRepository);
            case SORTWORKITEMSBYRATING:
                return new SortWorkingItemsByRating(applicationRepository);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivity(applicationRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivity(applicationRepository);
            case SHOWBOARDSACTIVITY:
                return new ShowBoardsActivity(applicationRepository);
            case CHANGESTATUSOFSTORY:
                return new ChangeStatusOfStory(applicationRepository);
            case CHANGESTATUSOFFEEDBACK:
                return new ChangeStatusOfFeedback(applicationRepository);
            case CHANGESTATUSOFBUG:
                return new ChangeStatusOfBug(applicationRepository);
            case CHANGESIZEOFSTORY:
                return new ChangeSizeOfStory(applicationRepository);
            case CHANGESEVERITYOFBUG:
                return new ChangeSeverityOfBug(applicationRepository);
            case CHANGERATINGOFFEEDBACK:
                return new ChangeRatingOfFeedback(applicationRepository);
            case CHANGEPRIORITYOFSTORY:
                return new ChangePriorityOfStory(applicationRepository);
            case CHANGEPRIORITYOFBUG:
                return new ChangePriorityOfBug(applicationRepository);
            case CREATEFEEDBACK:
                return new CreateFeedbackInBoard(applicationFactory, applicationRepository);
            case CREATEBUG:
                return new CreateBugInBoard(applicationFactory, applicationRepository);
            case CREATESTORY:
                return new CreateStoryInBoard(applicationFactory, applicationRepository);
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeam(applicationFactory, applicationRepository);
            case CREATETEAM:
                return new CreateTeamCommand(applicationFactory, applicationRepository);
            case LISTALLWORKITEMS:
                return new ListAllWorkItems(applicationRepository);
            case LISTALLBUGS:
                return new ListAllBugs(applicationRepository);
            case LISTALLSTORIES:
                return new ListAllStories(applicationRepository);
            case LISTALLFEEDBACK:
                return new ListAllFeedback(applicationRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeople(applicationRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeams(applicationRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoards(applicationRepository);
            case CREATENEWMEMBER:
                return new CreateMemberCommand(applicationFactory, applicationRepository);
            case ASSIGNWORKITEMTOMEMBER:
                return new AssignWorkItemToMember(applicationRepository);
            case UNASSIGNWORKITEM:
                return new UnassignWorkItem(applicationRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembers(applicationRepository);
            case FILTERWORKITEMBYASSIGNEE:
                return new FilterWorkItemsByAssignee(applicationRepository);
            case FILTERWORKITEMBYSTATUS:
                return new FilterWorkItemsByStatus(applicationRepository);
            case FILTERWORKITEMBYSTATUSANDASSIGNEE:
                return new FilterWorkItemsByStatusAndAssignee(applicationRepository);
            case ADDCOMMENTTOWORKITEM:
                return new AddCommentToWorkItem(applicationRepository, applicationFactory);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeam(applicationRepository);

        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));

    }
}