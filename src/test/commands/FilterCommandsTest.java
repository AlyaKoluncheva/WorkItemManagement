package test.commands;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.commandTypes.create.*;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByAssignee;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByStatus;
import com.telerikacademy.oop.application.commands.commandTypes.filter.FilterWorkItemsByStatusAndAssignee;
import com.telerikacademy.oop.application.core.ApplicationRepositoryImpl;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterCommandsTest {

    private static ApplicationRepository applicationRepository;
    private static ApplicationFactory applicationFactory;

    @BeforeAll
    static void beforeAll() {
        applicationRepository = new ApplicationRepositoryImpl();
        applicationFactory = new ApplicationFactoryImpl();

        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> teamParams = new ArrayList<>();
        teamParams.add("testTeam");
        createTeamCommand.execute(teamParams);

        CreateNewBoardInTeam createNewBoardInTeamCommand = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> boardParams = new ArrayList<>();
        boardParams.add("testTeam");
        boardParams.add("testBoard");
        createNewBoardInTeamCommand.execute(boardParams);

        CreateMemberCommand createMemberCommand1 = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> memberParams1 = new ArrayList<>();
        memberParams1.add("Gosho");
        createMemberCommand1.execute(memberParams1);

        CreateMemberCommand createMemberCommand2 = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> memberParams2 = new ArrayList<>();
        memberParams2.add("Ivana");
        createMemberCommand2.execute(memberParams2);

        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> paramsBug1 = new ArrayList<>();
        paramsBug1.add("testBoard");
        paramsBug1.add("BugNaIvana");
        paramsBug1.add("testBugDescription");
        paramsBug1.add("High");
        paramsBug1.add("Critical");
        paramsBug1.add("Active");
        paramsBug1.add("Ivana");
        paramsBug1.add("Step One");
        paramsBug1.add("Step Two");
        paramsBug1.add("Step Three");
        createBugInBoard.execute(paramsBug1);

        List<String> paramsBug2 = new ArrayList<>();
        paramsBug2.add("testBoard");
        paramsBug2.add("BugNaGosho");
        paramsBug2.add("testBugDescriptionSecond");
        paramsBug2.add("High");
        paramsBug2.add("Critical");
        paramsBug2.add("Fixed");
        paramsBug2.add("Gosho");
        paramsBug2.add("Step Two");
        paramsBug2.add("Step One");
        paramsBug2.add("Step Three");
        createBugInBoard.execute(paramsBug2);

        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> paramsStory1 = new ArrayList<>();
        paramsStory1.add("testBoard");
        paramsStory1.add("StoryNaIvana");
        paramsStory1.add("testStoryDescription");
        paramsStory1.add("High");
        paramsStory1.add("Large");
        paramsStory1.add("InProgress");
        paramsStory1.add("Ivana");
        createStoryInBoard.execute(paramsStory1);

        List<String> paramsStory2 = new ArrayList<>();
        paramsStory2.add("testBoard");
        paramsStory2.add("StoryNaGosho");
        paramsStory2.add("testStoryDescription");
        paramsStory2.add("Low");
        paramsStory2.add("Large");
        paramsStory2.add("InProgress");
        paramsStory2.add("Gosho");
        createStoryInBoard.execute(paramsStory2);
    }

    @Test
    void filterWorkItemsByAssignee_command_correct_arguments_returnString() {
        FilterWorkItemsByAssignee filterWorkItemsByAssignee = new FilterWorkItemsByAssignee(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("Ivana");
        String returnString = filterWorkItemsByAssignee.execute(params);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bugs assigned to ").append("Ivana").append("\n");
        stringBuilder.append("BugNaIvana").append(" ").append("\n");
        stringBuilder.append("Stories assigned to ").append("Ivana").append("\n");
        stringBuilder.append("StoryNaIvana").append(" ").append("\n");
        Assertions.assertEquals(stringBuilder.toString(), returnString);
    }

    @Test
    void filterWorkItemsByAssignee_command_incorrect() {
        FilterWorkItemsByAssignee filterWorkItemsByAssignee = new FilterWorkItemsByAssignee(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("NonExistent");
        Assertions.assertEquals(String.format(CommandConstants.MEMBER_NOT_FOUND, "NonExistent"), filterWorkItemsByAssignee.execute(params));
    }

    @Test
    void filterWorkItemsByStatus_command_correct_arguments_returnString() {
        FilterWorkItemsByStatus filterWorkItemsByStatus = new FilterWorkItemsByStatus(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("InProgress");
        String returnString = filterWorkItemsByStatus.execute(params);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("All workItems with status ").append(Status.InProgress.toString()).append(" \n");
        stringBuilder.append("StoryNaIvana\n").append("StoryNaGosho\n");
        Assertions.assertEquals(stringBuilder.toString(), returnString);
    }

    @Test
    void filterWorkItemsByStatus_command_incorrect() {
        FilterWorkItemsByStatus filterWorkItemsByStatus = new FilterWorkItemsByStatus(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("NonExistentStatus");
        Assertions.assertEquals(CommandConstants.INVALID_STATUS, filterWorkItemsByStatus.execute(params));
    }

    @Test
    void filterWorkItemsByAssigneeAndStatus_command_incorrectAssignee() {
        FilterWorkItemsByStatusAndAssignee filterWorkItemsByStatusAndAssignee = new FilterWorkItemsByStatusAndAssignee(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("Active");
        params.add("NonExistent");
        Assertions.assertEquals(String.format(CommandConstants.MEMBER_NOT_FOUND, "NonExistent"), filterWorkItemsByStatusAndAssignee.execute(params));
    }

    @Test
    void filterWorkItemsByStatusAndAssignee_command_correct_arguments_returnString() {
        FilterWorkItemsByStatusAndAssignee filterWorkItemsByStatusAndAssignee = new FilterWorkItemsByStatusAndAssignee(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("Active");
        params.add("Ivana");
        String returnString = filterWorkItemsByStatusAndAssignee.execute(params);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Bugs assigned to ").append("Ivana").append(" with status ").append(Status.Active.toString()).append("\n");

        stringBuilder.append("BugNaIvana ");

        stringBuilder.append("\n");

        stringBuilder.append("Stories assigned to ").append("Ivana").append(" with status ").append(Status.Active.toString()).append("\n");

        stringBuilder.append("\n");

        Assertions.assertEquals(stringBuilder.toString(), returnString);
    }

    @Test
    void filterWorkItemsByStatusAndAssignee_command_incorrect_Status() {
        FilterWorkItemsByStatusAndAssignee filterWorkItemsByStatusAndAssignee = new FilterWorkItemsByStatusAndAssignee(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("NonExistentStatus");
        params.add("Ivana");
        Assertions.assertEquals(CommandConstants.INVALID_STATUS, filterWorkItemsByStatusAndAssignee.execute(params));
    }
}