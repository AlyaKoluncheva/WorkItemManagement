package com.telerikacademy.oop.application.commands;

public class CommandConstants {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";
    public static final String TEAM_EXISTS_ERROR_MESSAGE = "Team %s already exists";
    public static final String MEMBER_EXISTS_ERROR_MESSAGE = "Member %s already exists";
    public static final String BOARD_EXISTS_ERROR_MESSAGE = "Board %s already exists";
    public static final String MEMBER_NOT_FOUND_ERROR_MESSAGE = "Member %s not found";
    public static final String ID_NOT_FOUND_ERROR_MESSAGE = "ID %s not found";
    public static final String TEAM_DOES_NOT_EXIST = "Team %s doesn't exist";
    public static final String BOARD_DOES_NOT_EXIST = "Board %s doesn't exist";
    public static final String INVALID_STATUS_FEEDBACK = "Invalid Feedback Status";
    public static final String INVALID_STATUS_BUG = "Invalid Bug Status";
    public static final String MEMBER_NOT_FOUND = "Member %s doesn't exist";
    public static final String INVALID_STATUS_STORY = "Invalid Story Status";
    public static final String INVALID_STATUS = "Invalid Status";
    public static final String BOARD_EMPTY = "There are no work items in board";
    public static final String WORK_ITEM_TO_STRING = "Title: %s\nDescription: %s\nStatus: %s\nIn Board: %s\n";
    public static final String BUG_TO_STRING = "%sSeverity: %s\nStep: %s\n";
    public static final String FEEDBACK_TO_STRING = "%sRating: %s\n";
    public static final String STORY_TO_STRING = "%sSize: %s\n";

    public static final String NO_SUCH_ASSIGNABLE = "There is no assignable item with name %s";

    public static final String JOIN_DELIMITER = "####################";

    // Success messages
    public static final String MEMBER_CREATED_SUCCESS_MESSAGE = "Member %s created";
    public static final String WORKITEM_UNASSIGNED_SUCCESS_MESSAGE = "WorkItem with title %s does not have assignee.";
    public static final String BOARD_CREATED_SUCCESS_MESSAGE = "Board %s created";
    public static final String TEAM_CREATED_SUCCESS_MESSAGE = "Team %s created";

    public static final String TEAM_EMPTY = "No Teams created";
    public static final String MEMBER_ADDED_TO_TEAM_CREATED_SUCCESS_MESSAGE = "Member %s was added to team %s";
    public static final String COMMENT_ADDED_TO_WORK_ITEM_CREATED_SUCCESS_MESSAGE = "Comment %s was added to work item with title %s";
}
