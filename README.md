# WorkItemManagement
Console! Work Item Management System I created in Java for Telerik Academy Alpha Java 2020. 


## Creating Team, Board and Person (Member)

CREATETEAM TeamAwesome

CREATEPERSON Alexandra

CREATENEWBOARDINTEAM TeamAwesome AwesomeBoard

## CreateStory:

CREATESTORY AwesomeBoard ThisIsTheStoryName ThisIsTheDiscription High Large Done Alexandra

CREATESTORY AwesomeBoard ANewStoryName SomeDescription Medium Small NotDone Alexandra

CREATESTORY AwesomeBoard BeforeYouFinish BeforeYouFinishTheExam Low Small InProgress Alexandra


## Wrong Output:

CREATESTORY AwesomeBoard CreateANiceFolder ARandomDescription Small Large Done Alexandra

## CreateBug:
CREATEBUG AwesomeBoard AThisIsTheBugTitle SomeDescription High Minor Active Alexandra step one step two

CREATEBUG AwesomeBoard BThisIsTheBugTitle DescriptionIsHere Medium Critical Fixed Alexandra step one step two step step

CREATEBUG AwesomeBoard CThisIsTheBugTitle MayIHelpYou Low Major Fixed Alexandra step one step two three four


## Wrong Output:

CREATEBUG AwesomeBoard DThisIsTheBugTitle SomeDescription High Critical LALA Alexandra step one step two one


## SHow commands:

SHOWBOARDSACTIVITY AwesomeBoard

SHOWTEAMACTIVITY TeamAwesome



## Sorts Alphabetically 

SORTWORKITEMSBYSEVERIRY

SORTWORKITEMSBYPRIORITY

SORTWORKITEMSBYSIZE

SORTWORKITEMSBYTITLE


## Sorts by value
SORTWORKITEMSBYRATING


CHANGESTATUSOFSTORY *id* NotDone

CHANGESTATUSOFSTORY *id* InProgress

CHANGESTATUSOFSTORY *id* Done
