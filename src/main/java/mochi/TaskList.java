package mochi;

import mochi.task.Deadline;
import mochi.task.Event;
import mochi.task.Task;
import mochi.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mochi.Parser.isPrinting;
/**
 * Dedicated class for storing the arraylist of Tasks and all its relevant methods
 */
public class TaskList {

    private static final int ARRAY_OFFSET = 1;
    static private final List<Task> taskList = new ArrayList<Task>();
    public static final String EVENT_CMD_FROM = "/from";
    public static final String DEADLINE_BY_CMD = "/by";
    public static final String EVENT_CMD_TO = "/to";
    public static UI ui = new UI();

   public List<Task> getTaskList(){
       return taskList;
   }

    /**
     * Prints entire taskList,
     * Method overloaded by additional Argument
     */
    public void printArrayList() {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ":" + taskList.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void printArrayList(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ":" + taskList.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * delete specific task in arrayList
     * @param deleteIndexString index of the string to be deleted. Shown Index = index + index offset
     */
    public void deleteTask(String deleteIndexString) {
        int deleteIndex = 0;
        try{
            deleteIndex = Integer.parseInt(deleteIndexString);
        }catch(NumberFormatException e){
            System.out.println("Input needs to be integer!");
            return;
        }

        deleteIndex -= ARRAY_OFFSET;
        if(deleteIndex >= taskList.size() || deleteIndex < 0){
            System.out.println("You need to specify at least one task!");
            return;
        }
        String deletedString = taskList.get(deleteIndex).toString();
        taskList.remove(deleteIndex);
        ui.deleteEventSuccess(deletedString, taskList.size());
    }

     public static void insertEvent(String uncleanedString) {
        String description;
        String startDate;
        String endDate;
        try {
             description = uncleanedString.substring(0, uncleanedString.indexOf(EVENT_CMD_FROM));
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("Hello you missed your /to and /from >:(");
            return;
        }

        try {
             startDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_FROM) + EVENT_CMD_FROM.length(), uncleanedString.indexOf(EVENT_CMD_TO));
             endDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_TO) + EVENT_CMD_TO.length());
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("Hello you can you fill in your /to and /from properly");
            return;
        }
        Event event = new Event(description, startDate, endDate);
        taskList.add(taskList.size(), event);
        ui.addEventSuccess(event, taskList.size());
    }

    public static void insertTodo(String input) {
        Todo todo = new Todo(input);
        taskList.add(taskList.size(), todo);
        ui.addEventSuccess(todo, taskList.size());
    }

     public static void insertDeadline(String uncleanedString) {
        String description;
        String date;
        try {
             description = uncleanedString.substring(0, uncleanedString.indexOf(DEADLINE_BY_CMD));
        }catch (StringIndexOutOfBoundsException e) {
            System.out.println("Bro you forgot your /by");
            return;
        }

        try {
            date = uncleanedString.substring(uncleanedString.indexOf(DEADLINE_BY_CMD) + DEADLINE_BY_CMD.length());
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("Bro by when??");
            return;
        }
        Deadline task = new Deadline(description, date);
        taskList.add(taskList.size(),task);
        ui.addEventSuccess(task, taskList.size());
    }

    public static void markAsUndone(String input) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - ARRAY_OFFSET;
            taskList.get(listIndex).markAsUndone();
            if(isPrinting) {
                System.out.println("OK I have unmarked " + taskList.get(listIndex).getDescription() + " for you :3");
            }
        }
        catch (Exception e) {
            System.out.println("index out of range,please enter a valid number");
        }
    }

    public static void markAsDone(String input) {
        try {
            int listIndex = Integer.parseInt(input.substring(5)) - ARRAY_OFFSET;
            taskList.get(listIndex).markAsDone();
            if(isPrinting) {
                System.out.println("OK I have marked " + taskList.get(listIndex).getDescription() + " for you :3");
            }
        }
        catch (Exception e) {
            System.out.println("index out of range,please enter a valid number");
        }
    }
    /**
     * Compile the list of all the relevant description
     * @param findSubStrings substring of the description to search all the task in arraylist
     */
    public void findTask(String findSubStrings) {
        List<Task> matches = taskList.stream()
                .filter(taskAtIndex -> taskAtIndex.getDescription().contains(findSubStrings))
                .toList();
        printArrayList(matches);
   }

    /**
     * Compile the list of all the relevant date and time
     * @param findSubStrings substring of the time/day/date to search all the task in arraylist
     */
    public void findTime(String findSubStrings) {
        List<Task> matches = taskList.stream()
                .filter(t -> (t instanceof Deadline || t instanceof  Event))
                .filter(t -> t.getTime().contains(findSubStrings))
                .toList();
        printArrayList(matches);
    }

    public void clearAllTask() {
        taskList.clear();
        System.out.println("____________________________________________________________");
        System.out.println("I have deleted all your tasks i hope you didnt regret it :(");
        System.out.println("____________________________________________________________");
    }
}
