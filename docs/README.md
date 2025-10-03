# Mochi User Guide

Mochi is a simple Task management chatbot that helps you keep track of todos, events, and deadlines
It also supports saving of Tasks, so that you don't lose track of your saved items when restarting the chatbot.

# Features

## Adding deadlines

Deadlines are Tasks that have to be completed before a specified Date/Time.

Format: deadline [Task] /by [Date/Time]

Example: deadline submitting CS2113 assignment /by 4th October 2359

____________________________________________________________
Got it! I've added this task for you :3
[D][ ] submitting CS2113 assignment  (by: 4th October 2359)
You now have 1 tasks in the list
____________________________________________________________


## Adding Todos

Todos are Tasks that do not have a specific Date/Time for completion.

Format: todo [Task]

Example: todo do tp project 

____________________________________________________________
Got it! I've added this task for you :3
[T][ ] do tp project
You now have 1 tasks in the list
____________________________________________________________

## Adding Events

Events are Tasks that have a specified Date/Time of happening.

Format: event [Task] /from [Date/Time] /to [Date/Time]

Example: event CS2107 exam /from 6pm /to 8pm

____________________________________________________________
Got it! I've added this task for you :3
[E][ ] CS2107 exam  (from: 6pm , to: 8pm)
You now have 5 tasks in the list
____________________________________________________________


## Mark/Unmark Tasks

Tasks can be marked/unmarked with an 'X' to denote completion.

Format: mark [idx], unmark [idx]

Example: mark 1

OK I have marked go to gym for you :3


## Delete Tasks

Tasks can be deleted from Mochi as easily as they are added.

Format: delete [idx]

Example: delete 2

____________________________________________________________
Ooooke I have deleted this task for you
[T][X] go to gym
You now have 0 tasks in the list
____________________________________________________________

## Delete ALL Tasks

All task can be deleted at once, careful theres no turning back.

Format: deleteall

Example: deleteall
____________________________________________________________
I have deleted all your tasks i hope you didnt regret it :(
____________________________________________________________


## List Tasks

Tasks added to Mochi so far can be viewed using the list command.

Format: list

____________________________________________________________
1:[E][ ] CS2107 exam  (from: 6pm , to: 8pm)
2:[T][ ] do tp project
____________________________________________________________


## Find Tasks

Returns all the Task that contains keyword you specify using find command.

Format: find [keyword]

Example: 
find project
____________________________________________________________
1:[T][ ] do tp project
2:[T][ ] cs1007 project
3:[D][ ] cg2118 project  (by: 08 oct)
____________________________________________________________

## Find time 

Returns all the Task that contains the time you specify using findtime command.

Format: findtime [keyword]

Example:
find 6
____________________________________________________________
1:[E][ ] do tp project  (from: 6pm , to: 8pm)
1:[E][ ] CS2107 exam  (from: 6pm , to: 8pm)
3:[D][ ] cg2118 project  (by: 6 oct)
____________________________________________________________