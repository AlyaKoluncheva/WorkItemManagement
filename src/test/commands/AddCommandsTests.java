package test.commands;

import com.telerikacademy.oop.application.commands.commandTypes.add.AddCommentToWorkItem;
import com.telerikacademy.oop.application.commands.commandTypes.add.AddMemberToTeam;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateFeedbackInBoard;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateMemberCommand;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateNewBoardInTeam;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateTeamCommand;
import com.telerikacademy.oop.application.core.ApplicationRepositoryImpl;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.models.contracts.Team;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddCommandsTests {

    static ApplicationRepository applicationRepository;
    static ApplicationFactory applicationFactory;

    static WorkItem workItem;
    static Team team;


    @BeforeAll
    static void beforeAll() {
        applicationRepository = new ApplicationRepositoryImpl();
        applicationFactory = new ApplicationFactoryImpl();

        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> teamParams = new ArrayList<>();
        teamParams.add("beforeTeam");
        createTeamCommand.execute(teamParams);

        CreateNewBoardInTeam createNewBoardInTeamCommand = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> boardParams = new ArrayList<>();
        boardParams.add("beforeTeam");
        boardParams.add("beforeBoard");
        createNewBoardInTeamCommand.execute(boardParams);

        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> feedbackParams = new ArrayList<>();
        feedbackParams.add("beforeBoard");
        feedbackParams.add("testWorkItem");
        feedbackParams.add("testDescription");
        feedbackParams.add("2");
        feedbackParams.add("Scheduled");
        createFeedbackInBoard.execute(feedbackParams);

        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory,applicationRepository);
        List<String> memberParams = new ArrayList<>();
        memberParams.add("memberName");
        createMemberCommand.execute(memberParams);

        workItem = applicationRepository.getWorkItems().stream().filter(it -> it.getTitle().equals("testWorkItem")).findFirst().get();
        team = applicationRepository.getTeams().stream().filter(it->it.getName().equals("beforeTeam")).findFirst().get();
    }

    @Test
    void addCommentToWorkItem_command_correct_arguments() {
        AddCommentToWorkItem addCommentToWorkItem = new AddCommentToWorkItem(applicationRepository, applicationFactory);
        List<String> params = new ArrayList<>();
        params.add("testWorkItem");
        params.add("commentDescription");
        addCommentToWorkItem.execute(params);
        Assertions.assertTrue(workItem.getComments().contains("commentDescription"));
    }

    @Test
    void addCommentToWorkItem_command_correct_returnString(){
        AddCommentToWorkItem addCommentToWorkItem = new AddCommentToWorkItem(applicationRepository,applicationFactory);
        List<String> params = new ArrayList<>();
        params.add("testWorkItem");
        params.add("commentDescription");
        Assertions.assertEquals("Comment commentDescription was added to work item with title testWorkItem \n",addCommentToWorkItem.execute(params));
    }

    @Test
    void addCommentToWorkItem_command_incorrect_more_arguments(){
        AddCommentToWorkItem addCommentToWorkItem = new AddCommentToWorkItem(applicationRepository,applicationFactory);
        List<String> params = new ArrayList<>();
        params.add("testWorkItem");
        params.add("commentDescription");
        params.add("invalidComment");
        Assertions.assertThrows(IllegalArgumentException.class, ()->addCommentToWorkItem.execute(params));
    }

    @Test
    void addMemberToTeam_command_correct_arguments(){
        AddMemberToTeam addMemberToTeam = new AddMemberToTeam(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("beforeTeam");
        params.add("memberName");
        addMemberToTeam.execute(params);
        Assertions.assertTrue(team.getMembers().stream().anyMatch(it->it.getName().equals("memberName")));
    }

    @Test
    void addMemberToTeam_command_returnString(){
        AddMemberToTeam addMemberToTeam = new AddMemberToTeam(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("beforeTeam");
        params.add("memberName");
        Assertions.assertEquals("Member memberName was added to team beforeTeam \n",addMemberToTeam.execute(params));
    }

    @Test
    void addMemberToTeam_command_more_arguments(){
        AddMemberToTeam addMemberToTeam = new AddMemberToTeam(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("beforeTeam");
        params.add("memberName");
        params.add("invalidName");
        Assertions.assertThrows(IllegalArgumentException.class,() ->addMemberToTeam.execute(params));
    }
}