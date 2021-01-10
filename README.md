# Work Item Management Console Application
Work Item Management System I created in Java for ***Telerik Academy Alpha Java 2020***. 
 This was the first bigger project I have created from scratch in Java. 
 The idea of it is to be something similar to Trello, but made in as a console app 
 It started as a small application but with time I decided to continue adding different functions and new knowledge.

- [Creating Team, Board and Member:](#creating-team--board-and-member-)
    + [With the commands from the table below we can create a new Team, Board and Member](#with-the-commands-from-the-table-below-we-can-create-a-new-team--board-and-member)
- [Creating Work Items:](#creating-work-items-)
    + [All work items have a few key elements:](#all-work-items-have-a-few-key-elements-)
  * [Crete Feedback:](#crete-feedback-)
  * [CreateStory:](#createstory-)
    + [Unique properties of Story:](#unique-properties-of-story-)
  * [CreateBug:](#createbug-)
    + [Unique properties:](#unique-properties-)
  * [Table with example commands on Work Items:](#table-with-example-commands-on-work-items-)
- [Other Commands:](#other-commands-)
  * [Show commands:](#show-commands-)
  * [Sorting commands:](#sorting-commands-)
  * [Change commands](#change-commands)
    + [Status:](#status-)
    + [Priority:](#priority-)
    + [Severity:](#severity-)
    + [Rating:](#rating-)
    + [Size](#size)
  * [Add:](#add-)
    + [Add Comment:](#add-comment-)
    + [Add Member to Team:](#add-member-to-team-)
  * [Filter:](#filter-)
  * [List:](#list-)
  * [Assign/Unassign Work Item:](#assign-unassign-work-item-)
  * [Example input and expected output:](#example-input-and-expected-output-)
    + [Output:](#output-)
- [Unit Testing:](#unit-testing-)
    + [So far I have created Unit testing (using JUnit) for:](#so-far-i-have-created-unit-testing--using-junit--for-)


# Creating Team, Board and Member:

### With the commands from the table below we can create a new Team, Board and Member

- The ***names*** should be between 5 and 15 characters
- To create a **board** we need to have first created a ***team***

| Command: | Expected Output: |
|----------|:----------:|
| <code> CREATETEAM NewTeamName </code> | Team NewTeamName created |
| <code> CREATENEWMEMBER Alexandra </code> | Member Alexandra created |
| <code> CREATENEWBOARDINTEAM NewTeamName AwesomeBoard   </code> | Board AwesomeBoard created |

<br>

>The example above shows the outputs if the commands we have entered are correct. In the ones **below** you can see the messages shown when you enter a **wrong command**:

<br>

***Incorrect Inputs:***

| Command: | Expected Output: |
|----------|:----------:|
| <code> CREATETEAM New </code> | Team name should be between 5 and 15 characters! <br> *(throws **IllegalArgumentException**)* |
| <code> CREATENEWMEMBER Alex </code> | Name should be between 5 and 15 characters! <br> *(throws **IllegalArgumentException**)* |
| <code> CREATENEWBOARDINTEAM UnexistingTeamName NewBoardName | Team UnexistingTeamName doesn't exist <br> *(throws **IllegalArgumentException**)* <br> If we try to create board to a team that doesn't exist
| <code> CREATENEWBOARDINTEAM NewTeamName AwesomeBoard   </code> | Board AwesomeBoard already exists <br> *(throws **IllegalArgumentException**)* <br> If a board with the same name has already been initialized |

# Creating Work Items:

### All work items have a few key elements:
- ***Board name***- each work item **must** be assigned to a board
  - Only the members that are part of the team the board belongs to have access to the elements from it;
  - The Board keeps track of all changes that were made to the items in it
- ***Title*** - the name used to refer to the work item
  - A String that should be **between 10 and 50 symbols**
- ***Description***:
  - String that can have a length **between 10 and 500**
- ***Status***:
  - Which is different for the different work items
- ***Unique ID*** - generated with UUID

## Crete Feedback:

### In addition to the common fields feedback has some unique ones too:
- Rating 
  - A number, where the bigger the number the more important the rating is

- The accepted **statuses** for feedback are:
  - New
  - Scheduled
  - Unscheduled
  - Done
  - If you put a status that belongs to another Work Item, the program will ***throw an exception*** <br>
  
```java
CREATEFEEDBACK <boardName> <title> <description> <rating> <status>
```

## CreateStory:

### Unique properties of Story:
- Priority
- Size
- Status:
  - NotDone
  - InProgress
  - Done
  - If the status is different from the ones above it ***throws an exception***
- Assignee:
  - Doesn't need to be always added
  - Unique property of Bug and Story not found in Feedback
  - Needs to be a member if the member hasn't been created ***throws an exception***

```java 
CREATESTORY <boardName> <title> <description> <priority> <size> <status> <assignee>
```

## CreateBug:

### Unique properties:
- Priority
  - The same as the story priority
- Severity
- Status
  - Active
  - Fixed
- Assignee
  - Equivalent to the story
- Steps
  - A list of strings

```java
CREATEBUG <boardName> <title> <description> <priority> <severity> <status> <assignee> <List steps>
```

## Table with example commands on Work Items:

| Command: | Expected Output: |
|----------|:----------:|
| <code> CREATEFEEDBACK AwesomeBoard FeedbackTitle FeedbackDescription 10 New </code> | Feedback with ID ***random id*** was created. |
| <code> CREATESTORY AwesomeBoard ThisIsTheStoryName ThisIsTheDiscription High Large Done Alexandra </code> | Story with ID ***random id*** was created. |
| <code> CREATEBUG AwesomeBoard BThisIsTheBugTitle DescriptionIsHere Medium Critical Fixed Alexandra step one two three  </code> | Bug with ID ***random id*** was created. |


# Other Commands:

## Show commands:

  Example commands with their outputs:

| Command: | Expected Output: |
|----------|:----------:|
| <code> SHOWBOARDSACTIVITY AwesomeBoard </code> | List of all the changes made to the board with ***timestamps*** |
| <code> SHOWTEAMACTIVITY TeamAwesome </code> | List of all the changes made to the team with ***timestamps*** |
| <code> SHOWALLPEOPLE </code> | Alexandra (the only member we have created so far) |
| <code> SHOWALLTEAMS </code> | Team: NewTeamName (names of the created teams) |
| <code> SHOWALLTEAMBOARDS </code> | List of all the team boards |
| <code> SHOWALLTEAMMEMBERS </code> | List of all the team members ( we have to assign a member to the team with the command ADDMEMBERTOTEAM ) |


## Sorting commands:

- You can sort the same way like priority:
  - SORTWORKITEMSBYSIZE
  - SORTWORKITEMSBYTITLE
  - SORTWORKITEMSBYSEVERIRY
  
| Command: | Expected Output: |
|----------|:----------:|
| <code> SORTWORKITEMSBYRATING </code> | Sorts work items that ***have*** rating, ***ascending***|
| <code> SORTWORKITEMSBYPRIORITY </code> | Sorts work items alphabetically |


## Change commands

### Status:

On the table below I have shown the example commands for changing the status of a Story. The same way we can change the satuses of bugs and feedbacks.
- *id* is the unique id given by the console when creating the Work Item

| Command: | Expected Output: |
|----------|:----------:|
| <code> CHANGESTATUSOFSTORY *id* Done </code> | Story ID: *id* Status already set to: Done |
| <code> CHANGESTATUSOFSTORY *id* NotDone </code> | Story ID: *id* changed status to: Not Done |
| <code> CHANGESTATUSOFSTORY *id* InProgress </code> | Story ID: *id* changed status to: In Progress |


```java
CHANGESTATUSOFFEEDBACK <id> <status>
```


### Priority:

Examples below show how to change the priority of stories. It's done the same way for bugs.

| Command: | Expected Output: |
|----------|:----------:|
| <code> CHANGEPRIORITYOFSTORY *id* High </code> | Story ID: *id* Priority already set to: High |
| <code> CHANGEPRIORITYOFSTORY *id* Medium </code> | Story ID: *id* priority status to: Medium |
| <code> CHANGEPRIORITYOFSTORY *id* Low </code> |  Story ID: *id* priority status to: Low |


```java
CHANGEPRIORITYOFSTORY <id> <priority>
CHANGEPRIORITYOFBug <id> <priority>
```

### Severity:

- The message format is the same as status and priority
```java
CHANGESEVERITYOFBUG <id> <severity>
 ```

### Rating: 
- The message format is the same as status and priority
```java
CHANGERATINGOFFEEDBACK <id> <number/rating>
 ```

### Size
- The message format is the same as status and priority
```java
CHANGESIZEOFSTORY <id> <size>
 ```

## Add:

### Add Comment:

  - Comments can be a single string only!
  - An exception is being thrown when you put more than 1 String!

| Command: | Expected Output: |
|----------|:----------:|
| <code> ADDCOMMENTTOWORKITEM ThisIsTheStoryName hi </code> | Comment hi was added to work item with title ThisIsTheStoryName |
| <code> ADDCOMMENTTOWORKITEM ThisIsTheStoryName this is another comment </code> | Expected: 2 <br> Given: 5 |


```java
ADDCOMMENTTOWORKITEM <Work Item Name> <Comment>
 ```

### Add Member to Team:

- This command add a member to an already existing team
- If the member or the team don't exist an error messages are being displayed

| Command: | Expected Output: |
|----------|:----------:|
| <code> ADDMEMBERTOTEAM NewTeamName Alexandra </code> | Member Alexandra was added to team NewTeamName |
 | <code> ADDMEMBERTOTEAM NewTeamName NoUserName </code> |Member NoUserName doesn't exist  |
| <code> ADDMEMBERTOTEAM noTeamName Alexandra </code> | Team noTeamName doesn't exist   |

```java
ADDMEMBERTOTEAM <Team name> <Member name>
 ```

## Filter:

| Command: | Expected Output: |
|----------|:----------:|
| <code> FILTERWORKITEMBYASSIGNEE Alexandra </code> |Bugs assigned to Alexandra: <br>   <br> Stories assigned to Alexandra: <br> ThisIsTheStoryName  |
| <code> FILTERWORKITEMBYASSIGNEE NoName </code> | Member NoName doesn't exist  |


- Only Bugs and Stories have assignees so far;
- It returns the name of the Bugs or Stories this assignee has;
- If there isn't a Bug or Story an empty line is left;
- If the member doesn't exist a message is being outputted;

## List:

| Command: | Expected Output: |
|----------|:----------:|
| <code> LISTALLWORKITEMS </code> | Lists all the information about all created work items  |
| <code> LISTALLBUGS </code> | Lists all the information about all created bugs  |
| <code> LISTALLSTORIES </code> | Lists all the information about all created stories  |
| <code> LISTALLFEEDBACK </code> | Lists all the information about all created feedbacks  |

<br>

Lists the information about given work item type:
- Title
- Description
- Status
- In Board: Board Name
- And the unique ones for each category of work tuems

```java
LISTALLWORKITEMS
LISTALLBUGS
LISTALLSTORIES
LISTALLFEEDBACK
 ```

## Assign/Unassign Work Item:

- For this example we create a new Story with title newStoryName
- If member or item with this name don't exist an error message will be printed


| Command: | Expected Output: |
|----------|:----------:|
| <code> ASSIGNWORKITEMTOMEMBER Alexandra newStoryName </code> | Item newStoryName assigned to Alexandra  |
| <code> ASSIGNWORKITEMTOMEMBER Alexandra newStoryName123 </code> | There is no assignable item with name newStoryName123   
| <code> ASSIGNWORKITEMTOMEMBER Alexandra1234 newStoryName </code> | Member Alexandra1234 not found  |


##  Example input and expected output:
```java
CREATETEAM NewTeamName
CREATENEWMEMBER Alexandra
CREATENEWBOARDINTEAM NewTeamName AwesomeBoard
CREATEFEEDBACK AwesomeBoard FeedbackTitle FeedbackDescription 10 New
CREATESTORY AwesomeBoard ThisIsTheStoryName ThisIsTheDiscription High Large Done Alexandra
CREATESTORY AwesomeBoard ANewStoryName SomeDescription Medium Small NotDone Alexandra
CREATESTORY AwesomeBoard BeforeYouFinish BeforeYouFinishTheExam Low Small InProgress Alexandra
CREATEBUG AwesomeBoard AThisIsTheBugTitle SomeDescription High Minor Active Alexandra step one step two


CHANGEPRIORITYOFSTORY *id* High
CHANGEPRIORITYOFSTORY *id* Low
CHANGESTATUSOFSTORY *id* NotDone

```

### Output:
```
Team NewTeamName created 
Member Alexandra created
Board AwesomeBoard created 
Feedback with ID e247714d-59c7-4ef9-a834-f7672832408a was created.
Story with ID ca3611fa-61f3-42fa-988b-5c07a2b1e46f was created.
Story with ID b4bc2881-890a-4675-bb45-d391cb4ceab2 was created.
Story with ID 0c45aaf8-bb6a-4301-8de0-ab5314b62058 was created.
Bug with ID 0067e308-c796-4fa7-850a-b10984e0f543 was created.
Story ID: 0c45aaf8-bb6a-4301-8de0-ab5314b62058 Priority already set to: High
Story ID: 0c45aaf8-bb6a-4301-8de0-ab5314b62058 Priority set to: Low
Story ID: 0c45aaf8-bb6a-4301-8de0-ab5314b62058 Status set to: NotDone

```
### NB: 
The id's will always be different. The ones in the output are just for illustration purposes!

# Unit Testing:

### So far I have created Unit testing (using JUnit) for:

- Add Commands
- Change Work Item Commands
- Create Commands
- Filter Commands
- List Commands
