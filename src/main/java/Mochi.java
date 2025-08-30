import java.util.ArrayList;
import java.util.Scanner;

public class Mochi {

    static ArrayList<Task> inputs = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        while (true) {
            String input = sc.nextLine();
            listenForInputs(input, inputs);
        }
    }

    private static void listenForInputs(String input, ArrayList<Task> inputs) {
        if (input.trim().equalsIgnoreCase("bye")) {
            goodByeMessage();
            System.exit(0);
        }

        if (input.trim().equalsIgnoreCase("list")) {
            printArrayList(inputs);
        }
        else if (input.toLowerCase().contains("unmark")){
            markAsUndone(input, inputs);
        }
        else if (input.toLowerCase().contains("mark")){
            markAsDone(input, inputs);
        }

        else{
            Task task = new Task(input);
            inputs.add(inputs.size(), task);
            System.out.println( "added: " + input);
        }
    }

    private static void markAsUndone(String input, ArrayList<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - 1;
            inputs.get(listIndex).markAsUndone();
        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
        }
    }

    private static void markAsDone(String input, ArrayList<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(5)) - 1;
            inputs.get(listIndex).markAsDone();
        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
        }
    }

    private static void printArrayList(ArrayList<Task> inputs) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < inputs.size(); i++) {
            System.out.print(i + 1 + ":[" + inputs.get(i).getStatusIcon() + "]");
            System.out.println(inputs.get(i).getDescription());
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
