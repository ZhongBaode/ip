package mochi;

import mochi.task.Deadline;
import mochi.task.Event;
import mochi.task.Task;
import mochi.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mochi.Parser.isPrinting;

public class TaskList {

    private static final int ARRAY_OFFSET = 1;
    static final List<Task> taskList = new ArrayList<Task>();
    public static final String EVENT_CMD_FROM = "/from";
    public static final String DEADLINE_BY_CMD = "/by";
    public static final String EVENT_CMD_TO = "/to";
    public static UI ui = new UI();

   public List<Task> getTaskList(){
       return taskList;
   }
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

        System.out.println("Deleting task: " + taskList.get(deleteIndex).getDescription());
        taskList.remove(deleteIndex);
    }

     public static void insertEvent(String uncleanedString) {
        String description = uncleanedString.substring(0, uncleanedString.indexOf(EVENT_CMD_FROM));
        String startDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_FROM) + EVENT_CMD_FROM.length(), uncleanedString.indexOf(EVENT_CMD_TO));
        String endDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_TO) + EVENT_CMD_TO.length());
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
        String description = uncleanedString.substring(0, uncleanedString.indexOf(DEADLINE_BY_CMD));
        String date = uncleanedString.substring(uncleanedString.indexOf(DEADLINE_BY_CMD) + DEADLINE_BY_CMD.length());
        Deadline task = new Deadline(description, date);
        taskList.add(taskList.size(),task);
        ui.addEventSuccess(task, taskList.size());
    }

    public static void markAsUndone(String input) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - ARRAY_OFFSET;
            taskList.get(listIndex).markAsUndone();
            if(isPrinting) {
                System.out.println("OK I have unmarked this task for you :3");
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
                System.out.println("OK I have marked this task for you :3");
            }
        }
        catch (Exception e) {
            System.out.println("index out of range,please enter a valid number");
        }
    }

    public void findTask(String findSubStrings) {
        List<Task> matches = taskList.stream()
                .filter(taskAtIndex -> taskAtIndex.getDescription().contains(findSubStrings))
                .toList();
        printArrayList(matches);
    }
}
