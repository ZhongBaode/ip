package mochi;

import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;
import mochi.task.Commands;
import mochi.task.Deadline;
import mochi.task.Event;
import mochi.task.FileHandler;
import mochi.task.Task;
import mochi.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Mochi {

    public static final String EVENT_CMD_FROM = "/from";
    public static final String DEADLINE_BY_CMD = "/by";
    public static final int CMD_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;
    private static final List<Task> taskList = new ArrayList<Task>();
    public static final String EVENT_CMD_TO = "/to";
    private static final int ARRAY_OFFSET = 1;
    public static final String SAVE_FILE_PATH = "src/main/java/mochi/task/MochiData.txt";
    public static boolean isPrinting = true;
    //used to exit program
    private boolean isRunning = true;

    void runMain() throws IOException, MissingArgumentException, MissingDescription {
        FileHandler fileHandler = new FileHandler();
        fileHandler.returnFileContentArray(SAVE_FILE_PATH, taskList);
        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while(this.isRunning && sc.hasNextLine()){
            try{
                handle(sc.nextLine());
            }catch(MissingArgumentException e){
                System.out.println("Input is empty! Please enter a valid command :)");
            }catch(MissingDescription e){
                System.out.println("Hey! You forgot to add a description :(");
            }
        }
        goodByeMessage();
    }

    protected void handle(String raw) throws MissingArgumentException, MissingDescription {
        if(raw.isEmpty()){
            throw new MissingArgumentException();
        }
        String input = raw.trim();
        String[] splits = input.split("\\s+", 2);
        Commands command = null;
        command = Commands.fromString(splits[CMD_INDEX]);
        switch (command){
        case BYE -> {
            goodBye();
            return;
        }
        case LIST -> {
            printArrayList(taskList);
            return;
        }
        }

        if(splits.length == 1 && command != Commands.UNKNOWN){
            throw new MissingDescription();
        }

        switch (command){
        case DEADLINE -> insertDeadline(splits[DESCRIPTION_INDEX]); //#TODO Error handle when deadline description is empty
        case MARK -> markAsDone(input, taskList);
        case UNMARK -> markAsUndone(input, taskList);
        case TODO -> insertTodo(splits[DESCRIPTION_INDEX]);
        case EVENT -> insertEvent(splits[DESCRIPTION_INDEX]);
        case DELETE -> {
            try{
                deleteTask(Integer.parseInt(splits[DESCRIPTION_INDEX]));
            }catch(NumberFormatException e){
                System.out.println("Input needs to be integer!");
            }catch (IndexOutOfBoundsException e){
                System.out.println("You need to specify at least one task!");
            }
        }
        default -> System.out.println("Sorry command not recognized! Try again :) Type help to see list of commands!");
        }
    }

    private void deleteTask(int deleteIndex) {
        deleteIndex -= ARRAY_OFFSET;
        if(deleteIndex >= taskList.size() || deleteIndex < 0){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("Deleting task: " + taskList.get(deleteIndex).getDescription());
        taskList.remove(deleteIndex);
    }

    private void goodBye() {
        System.out.println("Goodbye!");
        this.isRunning = false;
    }

    private static void insertEvent(String uncleanedString) {
        String description = uncleanedString.substring(0, uncleanedString.indexOf(EVENT_CMD_FROM));
        String startDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_FROM) + EVENT_CMD_FROM.length(), uncleanedString.indexOf(EVENT_CMD_TO));
        String endDate = uncleanedString.substring(uncleanedString.indexOf(EVENT_CMD_TO) + EVENT_CMD_TO.length());
        Event event = new Event(description, startDate, endDate);
        taskList.add(taskList.size(), event);
        addEventSuccess(event);
    }

    private static void insertTodo(String input) {
        Todo todo = new Todo(input);
        taskList.add(taskList.size(), todo);
        addEventSuccess(todo);
    }

    private static void insertDeadline(String uncleanedString) {
        String description = uncleanedString.substring(0, uncleanedString.indexOf(DEADLINE_BY_CMD));
        String date = uncleanedString.substring(uncleanedString.indexOf(DEADLINE_BY_CMD) + DEADLINE_BY_CMD.length());
        Deadline task = new Deadline(description, date);
        taskList.add(taskList.size(),task);
        addEventSuccess(task);
    }

    private  static void addEventSuccess(Task task) {
        if (isPrinting) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it! I've added this task for you :3");
            System.out.println(task.toString());
            System.out.println("You now have " + taskList.size() + " tasks in the list");
            System.out.println("____________________________________________________________");
        }
    }

    private static void markAsUndone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - ARRAY_OFFSET;
            inputs.get(listIndex).markAsUndone();
            if(isPrinting) {
                System.out.println("OK I have unmarked this task for you :3");
            }
        }
        catch (Exception e) {
            System.out.println("index out of range,please enter a valid number");
        }
    }

    private static void markAsDone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(5)) - ARRAY_OFFSET;
            inputs.get(listIndex).markAsDone();
            if(isPrinting) {
                System.out.println("OK I have marked this task for you :3");
            }
        }
        catch (Exception e) {
            System.out.println("index out of range,please enter a valid number");
        }
    }

    private static void printArrayList(List<Task> inputs) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < inputs.size(); i++) {
            System.out.println(i + 1 + ":" + inputs.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMessage() {
        String logo = """
                ____________________________________________________________
                Hello! I'm Mochi
                What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(logo);
    }

    private static void goodByeMessage() throws IOException {
        String logo = """
                ____________________________________________________________
                    Noot Noot
                ____________________________________________________________
                """;
        FileHandler fh = new FileHandler();
        fh.saveFile(SAVE_FILE_PATH,taskList);
        System.out.println(logo);
    }
}
