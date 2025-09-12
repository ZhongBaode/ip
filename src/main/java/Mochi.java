import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Mochi {

    public static final String EVENT_CMD_FROM = "/from";
    public static final String DEADLINE_BY_CMD = "/by";
    public static final int CMD_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;
    private static final List<Task> taskList = new ArrayList<Task>();
    public static final String EVENT_CMD_TO = "/to";
    //used to exit program
    private boolean isRunning = true;
    //Java main need to be static, so I cannot call this.isRunning. So I outsourced it
    public static void main(String[] args) {
        new Mochi().runMain();
    }

    private void runMain() {
        welcomeMessage();
        try (Scanner sc = new Scanner(System.in)) {
            while(this.isRunning && sc.hasNextLine()){
                handle(sc.nextLine());
            }
        }
        goodByeMessage();
    }

    private void handle(String raw){
        String input = raw.trim();
        if(input.isEmpty()){
            System.out.println("Input is empty! Please enter a valid command :)");
        }

        String[] splits = input.split("\\s+", 2);
        String command = splits[CMD_INDEX].toLowerCase(Locale.ROOT);

        switch (command){
            case "bye" ->       goodBye();
        case "deadline" ->      insertDeadline(splits[DESCRIPTION_INDEX]);
            case "list" ->      printArrayList(taskList);
            case "mark" ->      markAsDone(input, taskList);
            case "unmark" ->    markAsUndone(input, taskList);
            case "todo" ->      insertTodo(splits[DESCRIPTION_INDEX]);
            case "event" ->     insertEvent(splits[DESCRIPTION_INDEX]);
        }
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

    private  static void addEventSuccess(Task task){
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've added this task for you :3");
        System.out.println(task.toString());
        System.out.println("You now have " + taskList.size() + " tasks in the list");
        System.out.println("____________________________________________________________");
    }
    private static void markAsUndone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - 1;
            inputs.get(listIndex).markAsUndone();
            System.out.println("OK I have unmarked this task for you :3");

        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
        }
    }

    private static void markAsDone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(5)) - 1;
            inputs.get(listIndex).markAsDone();
            System.out.println("OK I have marked this task for you :3");
        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
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

    private static void goodByeMessage() {
        String logo = """
                ____________________________________________________________
                    Noot Noot
                ____________________________________________________________
                """;
        System.out.println(logo);
    }
}
