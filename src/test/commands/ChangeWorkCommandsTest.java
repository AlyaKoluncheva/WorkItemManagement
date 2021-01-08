package test.commands;

import com.telerikacademy.oop.application.commands.commandTypes.change.AssignWorkItemToMember;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateBugInBoard;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateMemberCommand;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateNewBoardInTeam;
import com.telerikacademy.oop.application.commands.commandTypes.create.CreateTeamCommand;
import com.telerikacademy.oop.application.core.ApplicationRepositoryImpl;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeWorkCommandsTest {

    static ApplicationRepository applicationRepository;
    static ApplicationFactory applicationFactory;

    static BugImpl bug;

    @BeforeAll
    static void before() {

        applicationRepository = new ApplicationRepositoryImpl();
        applicationFactory = new ApplicationFactoryImpl();

        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> paramsTeam = new ArrayList<>();
        paramsTeam.add("BeforeTeamName");
        createTeamCommand.execute(paramsTeam);

        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> paramsBoard = new ArrayList<>();
        paramsBoard.add("BeforeTeamName");
        paramsBoard.add("BeforeBoardName");
        createNewBoardInTeam.execute(paramsBoard);

        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> memberParams = new ArrayList<>();
        memberParams.add("memberName");
        createMemberCommand.execute(memberParams);

        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("BeforeBugTitle");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("memberName");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        createBugInBoard.execute(params);

        bug = applicationRepository.getBugs().stream().filter(it -> it.getTitle().equals("BeforeBugTitle")).findFirst().get();
    }

    @Test
    void AssignWorkItemTest_correct_arguments() {
        AssignWorkItemToMember assignWorkItemToMember = new AssignWorkItemToMember(applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("memberName");
        params.add(bug.getTitle());
        assignWorkItemToMember.execute(params);
        Assertions.assertTrue(applicationRepository.getMembers().stream().filter(it -> it.getName().equals("memberName"))
                .findFirst().get().getItems().stream().anyMatch(it -> it.getID().equals(bug.getID())));
    }
}
