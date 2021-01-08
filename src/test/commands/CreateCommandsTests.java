package test.commands;

import com.telerikacademy.oop.application.commands.CommandConstants;
import com.telerikacademy.oop.application.commands.commandTypes.create.*;
import com.telerikacademy.oop.application.core.ApplicationRepositoryImpl;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.models.workItems.BugImpl;
import com.telerikacademy.oop.application.models.workItems.FeedbackImpl;
import com.telerikacademy.oop.application.models.workItems.StoryImpl;
import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Severity;
import com.telerikacademy.oop.application.models.workItems.enums.Size;
import com.telerikacademy.oop.application.models.workItems.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateCommandsTests {
    static ApplicationRepository applicationRepository;
    static ApplicationFactory applicationFactory;

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
        List<String> paramsMember = new ArrayList<>();
        paramsMember.add("BeforeMember");
        createMemberCommand.execute(paramsMember);
    }

    @Test
    public void createTeam_command_correct_arguments() {
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        createTeamCommand.execute(params);
        Assertions.assertTrue(applicationRepository.getTeams().stream().anyMatch(it -> it.getName().equals("testTeamName")));
    }

    @Test
    public void createTeam_command_correct_arguments_returnString() {
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName2");
        Assertions.assertEquals("Team testTeamName2 created", createTeamCommand.execute(params));
    }

    @Test
    public void createTeam_command_incorrect_arguments_duplicateNames() {
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        createTeamCommand.execute(params);
        Assertions.assertEquals("Team testTeamName already exists", createTeamCommand.execute(params));
    }

    @Test
    public void createTeam_command_incorrect_arguments_moreArguments() {
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("something");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createTeamCommand.execute(params);
        });
    }

    //---------------------------------------------------------------

    @Test
    public void createBoard_in_team_correct_arguments() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("testBoardName");
        createNewBoardInTeam.execute(params);
        Assertions.assertTrue(applicationRepository.getBoards().stream().anyMatch(it -> it.getName().equals("testBoardName")));
    }

    @Test
    public void createBoard_in_team_correct_arguments_exist_In_Team() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("boardInTeam");
        createNewBoardInTeam.execute(params);
        Assertions.assertTrue(applicationRepository.getTeams().stream().filter(it -> it.getName().equals("testTeamName"))
                .findFirst().get().getBoards().stream().anyMatch(it -> it.getName().equals("boardInTeam")));
    }

    @Test
    public void createBoard_in_team_correct_arguments_return_string() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("NewTestBoard");
        Assertions.assertEquals("Board NewTestBoard created", createNewBoardInTeam.execute(params));
    }

    @Test
    public void createBoard_in_team_incorrect_arguments_duplicate_board_name() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("testBoardName");
        Assertions.assertEquals("Board testBoardName already exists", createNewBoardInTeam.execute(params));
    }

    @Test
    public void createBoard_in_team_incorrect_arguments_no_such_team_name() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("noSuchName");
        params.add("testBoardName2");
        Assertions.assertEquals("Team noSuchName doesn't exist", createNewBoardInTeam.execute(params));
    }

    @Test
    public void createBoard_in_team_incorrect_arguments_return_more_params() {
        CreateNewBoardInTeam createNewBoardInTeam = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testTeamName");
        params.add("testBoardName2");
        params.add("random");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createNewBoardInTeam.execute(params);
        });
    }

    //---------------------------------------------------------------

    @Test
    public void createMember_command_correct_arguments() {
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testMemberName");
        createMemberCommand.execute(params);
        Assertions.assertTrue(applicationRepository.getMembers().stream().anyMatch(it -> it.getName().equals("testMemberName")));
    }

    @Test
    public void createMember_command_correct_arguments_returnString() {
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testMemberName2");
        Assertions.assertEquals("Member testMemberName2 created", createMemberCommand.execute(params));
    }

    @Test
    public void createMember_command_incorrect_arguments_duplicateNames() {
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testMemberName");
        createMemberCommand.execute(params);
        Assertions.assertEquals("Member testMemberName already exists", createMemberCommand.execute(params));
    }

    @Test
    public void createMember_command_incorrect_arguments_moreArguments() {
        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testMemberName");
        params.add("random");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    createMemberCommand.execute(params);
                });
    }


    //---------------------------------------------------------------


    @Test
    public void createFeedback_command_correct_arguments() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardName");
        params.add("testFeedbackTitle");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Done");
        createFeedbackInBoard.execute(params);
        FeedbackImpl feedbackTest = applicationRepository.getFeedbacks().stream().filter(it -> it.getTitle().equals("testFeedbackTitle")).findFirst().get();
        Assertions.assertTrue(feedbackTest.getRating() == 2 && feedbackTest.getStatus() == Status.Done && feedbackTest.getDescription().equals("testFeedbackDescription"));
    }

    @Test
    public void createFeedback_command_correct_arguments_existsInBoard() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testFeedbackTitleExistsInBoard");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Done");
        createFeedbackInBoard.execute(params);
        Assertions.assertTrue(applicationRepository.getBoards().stream().filter(it -> it.getName().equals("BeforeBoardName"))
                .findFirst().get().getItems().stream().anyMatch(it -> it.getTitle().equals("testFeedbackTitleExistsInBoard")));
    }

    @Test
    public void createFeedback_command_correct_arguments_returnString() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardName");
        params.add("testFeedbackTitle2");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Done");
        String returnString = createFeedbackInBoard.execute(params);
        FeedbackImpl feedbackTest = applicationRepository.getFeedbacks().stream().filter(it -> it.getTitle().equals("testFeedbackTitle2")).findFirst().get();
        Assertions.assertEquals("Feedback with ID " + feedbackTest.getID() + " was created.", returnString);
    }

    @Test
    public void createFeedback_command_incorrect_arguments_noSuchBoard() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardNameNoneExistent");
        params.add("testFeedbackTitle2");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Done");
        Assertions.assertEquals("Board testBoardNameNoneExistent doesn't exist", createFeedbackInBoard.execute(params));
    }

    @Test
    public void createFeedback_command_incorrect_arguments_notFeedbackStatus() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardNameNoneExistent");
        params.add("testFeedbackTitle2");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Active");
        Assertions.assertEquals(CommandConstants.INVALID_STATUS_FEEDBACK, createFeedbackInBoard.execute(params));
    }

    @Test
    public void createFeedback_command_incorrect_arguments_noSuchStatus() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardNameNoneExistent");
        params.add("testFeedbackTitle2");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("InvalidStatus");
        Assertions.assertThrows(IllegalArgumentException.class, () -> createFeedbackInBoard.execute(params));
    }

    @Test
    public void createFeedback_command_incorrect_arguments_moreArguments() {
        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardName");
        params.add("testFeedbackTitle2");
        params.add("testFeedbackDescription");
        params.add("2");
        params.add("Done");
        params.add("random");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    createFeedbackInBoard.execute(params);
                });
    }

    //---------------------------------------------------------------

    @Test
    public void createBug_command_correct_arguments() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        createBugInBoard.execute(params);

        BugImpl bugTest = applicationRepository.getBugs().stream().filter(it -> it.getTitle().equals("testBugTitle")).findFirst().get();

        Assertions.assertTrue(bugTest.getDescription().equals("testBugDescription")
                && bugTest.getStatus() == Status.Active
                && bugTest.getSeverity() == Severity.Critical
                && bugTest.getPriority() == Priority.High
                && bugTest.getAssignee().equals("BeforeMember")
                && bugTest.getSteps().size() == 3);
    }

    @Test
    public void createBug_command_correct_arguments_existsInBoard() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");
        createBugInBoard.execute(params);
        Assertions.assertTrue(applicationRepository.getBoards().stream().filter(it -> it.getName().equals("BeforeBoardName"))
                .findFirst().get().getItems().stream().anyMatch(it -> it.getTitle().equals("testBugTitle")));
    }

    @Test
    public void createBug_command_correct_arguments_returnString() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        String returnString = createBugInBoard.execute(params);

        BugImpl bugTest = applicationRepository.getBugs().stream().filter(it -> it.getTitle().equals("testBugTitle2")).findFirst().get();

        Assertions.assertEquals(returnString,"Bug with ID "+bugTest.getID()+" was created.");
    }

    @Test
    public void createBug_command_nonExistentBoard() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("NonExisting");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertEquals("Board NonExisting doesn't exist",createBugInBoard.execute(params));
    }

    @Test
    public void createBug_command_InvalidPriority() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("NonExistingPriority");
        params.add("Critical");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertThrows(IllegalArgumentException.class,() ->createBugInBoard.execute(params));
    }

    @Test
    public void createBug_command_InvalidSeverity() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("NonExistingSeverity");
        params.add("Active");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertThrows(IllegalArgumentException.class,() ->createBugInBoard.execute(params));
    }

    @Test
    public void createBug_command_NonExistingStatus() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("InvalidStatus");
        params.add("BeforeMember");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertThrows(IllegalArgumentException.class,() ->createBugInBoard.execute(params));
    }

    @Test
    public void createBug_command_NonExistingMemberToBeAssigned() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Active");
        params.add("NonExisting");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertEquals("Member NonExisting doesn't exist", createBugInBoard.execute(params));
    }
    @Test
    public void createBug_command_InvalidStatusForBug() {
        CreateBugInBoard createBugInBoard = new CreateBugInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testBugTitle2");
        params.add("testBugDescription");
        params.add("High");
        params.add("Critical");
        params.add("Done");
        params.add("NonExisting");
        params.add("Step One");
        params.add("Step Two");
        params.add("Step Three");

        Assertions.assertEquals("Invalid Bug Status", createBugInBoard.execute(params));
    }

    //---------------------------------------------------------------

    @Test
    public void createStory_command_correct_arguments() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testStoryTitle");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Done");
        params.add("BeforeMember");
        createStoryInBoard.execute(params);

        StoryImpl storyTest = applicationRepository.getStories()
                .stream().filter(it -> it.getTitle().equals("testStoryTitle")).findFirst().get();

        Assertions.assertTrue(
                storyTest.getDescription().equals("testStoryDescription")
                && storyTest.getStatus() == Status.Done
                && storyTest.getPriority() == Priority.High
                && storyTest.getSize() == Size.Large
                && storyTest.getAssignee().equals("BeforeMember"));
    }

    @Test
    public void createStory_command_correct_arguments_existsInBoard() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testStoryTitle2");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Done");
        params.add("BeforeMember");
        createStoryInBoard.execute(params);
        Assertions.assertTrue(applicationRepository.getBoards().stream().filter(it -> it.getName().equals("BeforeBoardName"))
                .findFirst().get().getItems().stream().anyMatch(it -> it.getTitle().equals("testStoryTitle2")));
    }

    @Test
    public void createStory_command_correct_arguments_returnString() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("BeforeBoardName");
        params.add("testStoryTitle");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Done");
        params.add("BeforeMember");
        String returnString = createStoryInBoard.execute(params);
        StoryImpl storyTest = applicationRepository.getStories().stream().filter(it -> it.getTitle().equals("testStoryTitle")).findFirst().get();
        Assertions.assertEquals("Story with ID " + storyTest.getID() + " was created.", returnString);
    }

    @Test
    public void createStory_command_incorrect_arguments_noSuchBoard() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardNameNoneExistent");
        params.add("testStoryTitle2");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Done");
        params.add("BeforeMember");
        Assertions.assertEquals("Board testBoardNameNoneExistent doesn't exist", createStoryInBoard.execute(params));
    }

    @Test
    public void createStory_command_incorrect_arguments_notStoryStatus() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testBoardName");
        params.add("testStoryTitle2");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Active");
        params.add("BeforeMember");
        Assertions.assertEquals(CommandConstants.INVALID_STATUS_STORY, createStoryInBoard.execute(params));
    }

    @Test
    public void createStory_command_incorrect_arguments_noSuchStatus() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testStoryNameNoneExistent");
        params.add("testStoryTitle2");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("InvalidStatus");
        params.add("BeforeMember");
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryInBoard.execute(params));
    }

    @Test
    public void createStory_command_incorrect_arguments_moreArguments() {
        CreateStoryInBoard createStoryInBoard = new CreateStoryInBoard(applicationFactory, applicationRepository);
        List<String> params = new ArrayList<>();
        params.add("testStoryNameNoneExistent");
        params.add("testStoryTitle2");
        params.add("testStoryDescription");
        params.add("High");
        params.add("Large");
        params.add("Done");
        params.add("BeforeMember");
        params.add("random");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createStoryInBoard.execute(params));
    }
}