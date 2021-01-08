package test.commands;

import com.telerikacademy.oop.application.commands.commandTypes.create.*;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllBugs;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllFeedback;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllStories;
import com.telerikacademy.oop.application.commands.commandTypes.list.ListAllWorkItems;
import com.telerikacademy.oop.application.core.ApplicationRepositoryImpl;
import com.telerikacademy.oop.application.core.contracts.ApplicationFactory;
import com.telerikacademy.oop.application.core.contracts.ApplicationRepository;
import com.telerikacademy.oop.application.core.factories.ApplicationFactoryImpl;
import com.telerikacademy.oop.application.models.workItems.contracts.WorkItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListCommandsTests {

    private static ApplicationRepository applicationRepository;
    private static ApplicationRepository emptyApplicationRepository;

    static WorkItem bug1;
    static WorkItem bug2;
    static WorkItem story1;
    static WorkItem story2;
    static WorkItem feedback1;
    static WorkItem feedback2;

    @BeforeAll
    static void beforeAll() {
        applicationRepository = new ApplicationRepositoryImpl();
        emptyApplicationRepository = new ApplicationRepositoryImpl();
        ApplicationFactory applicationFactory = new ApplicationFactoryImpl();

        CreateTeamCommand createTeamCommand = new CreateTeamCommand(applicationFactory, applicationRepository);
        List<String> teamParams = new ArrayList<>();
        teamParams.add("testTeam");
        createTeamCommand.execute(teamParams);

        CreateNewBoardInTeam createNewBoardInTeamCommand = new CreateNewBoardInTeam(applicationFactory, applicationRepository);
        List<String> boardParams = new ArrayList<>();
        boardParams.add("testTeam");
        boardParams.add("testBoard");
        createNewBoardInTeamCommand.execute(boardParams);

        CreateMemberCommand createMemberCommand = new CreateMemberCommand(applicationFactory, applicationRepository);
        List<String> memberParams = new ArrayList<>();
        memberParams.add("Gosho");
        createMemberCommand.execute(memberParams);

        List<String> memberParams2 = new ArrayList<>();
        memberParams2.add("Ivana");
        createMemberCommand.execute(memberParams2);

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

        bug1 = applicationRepository.getWorkItemByName("BugNaIvana");

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

        bug2 = applicationRepository.getWorkItemByName("BugNaGosho");

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

        story1 = applicationRepository.getWorkItemByName("StoryNaIvana");

        List<String> paramsStory2 = new ArrayList<>();
        paramsStory2.add("testBoard");
        paramsStory2.add("StoryNaGosho");
        paramsStory2.add("testStoryDescription");
        paramsStory2.add("Low");
        paramsStory2.add("Large");
        paramsStory2.add("InProgress");
        paramsStory2.add("Gosho");
        createStoryInBoard.execute(paramsStory2);

        story2 = applicationRepository.getWorkItemByName("StoryNaGosho");

        CreateFeedbackInBoard createFeedbackInBoard = new CreateFeedbackInBoard(applicationFactory, applicationRepository);
        List<String> paramsFeedback1 = new ArrayList<>();
        paramsFeedback1.add("testBoard");
        paramsFeedback1.add("testFeedbackTitle");
        paramsFeedback1.add("testFeedbackDescription");
        paramsFeedback1.add("2");
        paramsFeedback1.add("Done");
        createFeedbackInBoard.execute(paramsFeedback1);

        feedback1 = applicationRepository.getWorkItemByName("testFeedbackTitle");

        List<String> paramsFeedback2 = new ArrayList<>();
        paramsFeedback2.add("testBoard");
        paramsFeedback2.add("testFeedback2");
        paramsFeedback2.add("testFeedbackDescription");
        paramsFeedback2.add("2");
        paramsFeedback2.add("Done");
        createFeedbackInBoard.execute(paramsFeedback2);

        feedback2 = applicationRepository.getWorkItemByName("testFeedback2");
    }

    @Test
    void ListAllWorkItems_withWorkItems() {
        ListAllWorkItems listAllWorkItems = new ListAllWorkItems(applicationRepository);
        String returnString = listAllWorkItems.execute(new ArrayList<>());
        String actualString =
                "Title: BugNaIvana\n" +
                "Description: testBugDescription\n" +
                "Status: Active\n" +
                "In Board: testBoard\n" +
                "Severity: Critical\n" +
                "Step: [Step One, Step Two, Step Three]\n" +
                "####################\n" +
                "Title: BugNaGosho\n" +
                "Description: testBugDescriptionSecond\n" +
                "Status: Fixed\n" +
                "In Board: testBoard\n" +
                "Severity: Critical\n" +
                "Step: [Step Two, Step One, Step Three]\n" +
                "####################\n" +
                "Title: StoryNaIvana\n" +
                "Description: testStoryDescription\n" +
                "Status: In Progress\n" +
                "In Board: testBoard\n" +
                "Size: Large\n" +
                "####################\n" +
                "Title: StoryNaGosho\n" +
                "Description: testStoryDescription\n" +
                "Status: In Progress\n" +
                "In Board: testBoard\n" +
                "Size: Large\n" +
                "####################\n" +
                "Title: testFeedbackTitle\n" +
                "Description: testFeedbackDescription\n" +
                "Status: Done\n" +
                "In Board: testBoard\n" +
                "Rating: 2\n" +
                "####################\n" +
                "Title: testFeedback2\n" +
                "Description: testFeedbackDescription\n" +
                "Status: Done\n" +
                "In Board: testBoard\n" +
                "Rating: 2";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllBugs_withWorkItems() {
        ListAllBugs listAllBugs = new ListAllBugs(applicationRepository);
        String returnString = listAllBugs.execute(new ArrayList<>());
        String actualString =
                "Title: BugNaIvana\n" +
                "Description: testBugDescription\n" +
                "Status: Active\n" +
                "In Board: testBoard\n" +
                "Severity: Critical\n" +
                "Step: [Step One, Step Two, Step Three]\n" +
                "####################\n" +
                "Title: BugNaGosho\n" +
                "Description: testBugDescriptionSecond\n" +
                "Status: Fixed\n" +
                "In Board: testBoard\n" +
                "Severity: Critical\n" +
                "Step: [Step Two, Step One, Step Three]";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllStories_withWorkItems() {
        ListAllStories listAllStories = new ListAllStories(applicationRepository);
        String returnString = listAllStories.execute(new ArrayList<>());
        String actualString ="Title: StoryNaIvana\n" +
                "Description: testStoryDescription\n" +
                "Status: In Progress\n" +
                "In Board: testBoard\n" +
                "Size: Large\n" +
                "####################\n" +
                "Title: StoryNaGosho\n" +
                "Description: testStoryDescription\n" +
                "Status: In Progress\n" +
                "In Board: testBoard\n" +
                "Size: Large";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllFeedback_withWorkItems() {
        ListAllFeedback listAllFeedback = new ListAllFeedback(applicationRepository);
        String returnString = listAllFeedback.execute(new ArrayList<>());
        String actualString = "Title: testFeedbackTitle\n"  +
                "Description: testFeedbackDescription\n" +
                "Status: Done\n" +
                "In Board: testBoard\n" +
                "Rating: 2\n" +
                "####################\n" +
                "Title: testFeedback2\n" +
                "Description: testFeedbackDescription\n" +
                "Status: Done\n" +
                "In Board: testBoard\n" +
                "Rating: 2";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllWorkItems_empty() {
        ListAllWorkItems listAllWorkItems = new ListAllWorkItems(emptyApplicationRepository);
        String returnString = listAllWorkItems.execute(new ArrayList<>());
        String actualString = "There are no registered Work Items.";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllBugs_empty() {
        ListAllBugs listAllBugs = new ListAllBugs(emptyApplicationRepository);
        String returnString = listAllBugs.execute(new ArrayList<>());
        String actualString = "There are no registered bugs.";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllStories_empty() {
        ListAllStories listAllStories = new ListAllStories(emptyApplicationRepository);
        String returnString = listAllStories.execute(new ArrayList<>());
        String actualString = "There are no registered stories.";
        Assertions.assertEquals(returnString, actualString);
    }

    @Test
    void ListAllFeedback_empty() {
        ListAllFeedback listAllFeedback = new ListAllFeedback(emptyApplicationRepository);
        String returnString = listAllFeedback.execute(new ArrayList<>());
        String actualString = "There is no registered feedback.";
        Assertions.assertEquals(returnString, actualString);
    }
}
