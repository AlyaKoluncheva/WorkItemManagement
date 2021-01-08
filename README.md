# Work Item Management Console Application
Work Item Management System I created in Java for ***Telerik Academy Alpha Java 2020***. 
 This was the first bigger project I have created from scratch in Java. 
 The idea of it is to be something similar to Trello, but made in as a console app 
 It started as a small application but with time I decided to continue adding different functions and new knowlages.

# Creating Team, Board and Member:

### With the commands from the table below we can create a new Team, Board and Member

- The ***names*** should be between 5 and 15 characters!
- To create a **board** we need to have first created a ***team***!

| Command: | Expected Output: |
|----------|:----------:|
| <code> CREATETEAM NewTeamName </code> | Team NewTeamName created |
| <code> CREATENEWMEMBER Alexandra </code> | Member Alexandra created |
| <code> CREATENEWBOARDINTEAM NewTeamName AwesomeBoard   </code> | Board AwesomeBoard created |

<br>

>The example above shows the outputs if the commands we have entered are correct. In the ones **below** you can see the messages shown when you enter a **wrong command**:

<br>

$ Incorrect Inputs: $

| Command: | Expected Output: |
|----------|:----------:|
| <code> CREATETEAM New </code> | Team name should be between 5 and 15 characters! <br> *(throws **IllegalArgumentException**)* |
| <code> CREATENEWMEMBER Alex </code> | Name should be between 5 and 15 characters! <br> *(throws **IllegalArgumentException**)* |
| <code> CREATENEWBOARDINTEAM UnexistingTeamName NewBoardName | Team UnexistingTeamName doesn't exist <br> *(throws **IllegalArgumentException**)* <br> If we try to create board to a team that doesnt exist
| <code> CREATENEWBOARDINTEAM NewTeamName AwesomeBoard   </code> | Board AwesomeBoard already exists <br> *(throws **IllegalArgumentException**)* <br> If a board with the same name has already been initialized |

# Creating Work Items:

### All work items have a few key elements:
- ***Board name***- each work item **must** be assigned to a board
  - Only the members that are part of the team the boaard belongs to hav access to the elements from it;
  - The Board keeps track of all changes that were made to the items in it
- ***Title*** - the name used to refer to the work item
  - A String that should be **between 10 and 50 symbols**
- ***Description***:
  - String that can have length **between 10 and 500**
- ***Status***:
  - Which is different for the different work items
- ***Unique ID*** - generated with UUID
<br>
<br>

## Crete Feedback:

### In addition to the common fields feedback has some unique ones too:
- Rating 
  - A number, where the bigger the number the more important the rating is

- The accepted **statuses** for feedback are:
  - New
  - Scheduled
  - Uncheduled
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
  - Equivelent to the story
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
| <code> CREATEBUG AwesomeBoard BThisIsTheBugTitle DescriptionIsHere Medium Critical Fixed Alexandra step one step two step step  </code> | Bug with ID ***random id*** was created. |

<br>

# Other Commands:

## Show commands:

  Example commands with their outputs:

| Command: | Expected Output: |
|----------|:----------:|
| <code> SHOWBOARDSACTIVITY AwesomeBoard </code> | List of all the changes made to the board with ***timestamps*** |
| <code> SHOWTEAMACTIVITY TeamAwesome </code> | List of all the changes made to the team with ***timestamps*** |
| <code> SHOWALLPEOPLE </code> | Alexandra (the only member wr have created so far) |
| <code> SHOWALLTEAMS </code> | Team: NewTeamName (names of the created teams) |
| <code> SHOWALLTEAMBOARDS </code> | List of all the team boards |
| <code> SHOWALLTEAMMEMBERS </code> | List of all the team members ( we have to assign member to team with the command ADDMEMBERTOTEAM ) |


<br>

## Sorting commands:

- You can sort the same way like priority:
  - SORTWORKITEMSBYSIZE
  - SORTWORKITEMSBYTITLE
  - SORTWORKITEMSBYSEVERIRY
  - 
| Command: | Expected Output: |
|----------|:----------:|
| <code> SORTWORKITEMSBYRATING </code> | Sorts work items that ***have*** rating, ***ascending***|
| <code> SORTWORKITEMSBYPRIORITY </code> | Sorts work items alphabetically |

<br>

// TODO <br>
// TODO! finish the rest of the Commands! 
// TODO <br>
// TODO <br>
// TODO <br>
// TODO <br>
// TODO <br>
// TODO <br>
// TODO <br>

## Change commands

### Status:

CHANGESTATUSOFSTORY *id* NotDone

CHANGESTATUSOFSTORY *id* InProgress

CHANGESTATUSOFSTORY *id* Done

### Priority:

### Severity:
 
### Rating: 

### Size

## Add:

## Filter:

## List:
 
## Assign/Unassign Work Item:

##  Example input and expeced input:
```java
CREATETEAM NewTeamName
CREATENEWMEMBER Alexandra
CREATENEWBOARDINTEAM NewTeamName AwesomeBoard
CREATEFEEDBACK AwesomeBoard FeedbackTitle FeedbackDescription 10 New
CREATESTORY AwesomeBoard ThisIsTheStoryName ThisIsTheDiscription High Large Done Alexandra
CREATESTORY AwesomeBoard ANewStoryName SomeDescription Medium Small NotDone Alexandra
CREATESTORY AwesomeBoard BeforeYouFinish BeforeYouFinishTheExam Low Small InProgress Alexandra
CREATEBUG AwesomeBoard AThisIsTheBugTitle SomeDescription High Minor Active Alexandra step one step two

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

```
### NB: 
The id's will always be different. The ones in the output are just for illustation purposes!

# Unit Testing:
