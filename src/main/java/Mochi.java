import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Mochi {

    private static List<Task> inputs = new ArrayList<Task>();   //Apparently linked list can be translated when using List<Task> instead of ArrayList<Task> so it is preferred
    private boolean isRunning = true;                           //Used to exit the program.

    public static void main(String[] args) {                //Java main need to be static, so i cannot call this.isRunning. So i outsource
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

    private static void markAsUndone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(7)) - 1;
            inputs.get(listIndex).markAsUndone();
        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
        }
    }

    private static void markAsDone(String input, List<Task> inputs) {
        try {
            int listIndex = Integer.parseInt(input.substring(5)) - 1;
            inputs.get(listIndex).markAsDone();
        }
        catch (Exception e) {
            System.out.println("(“index out of range”, “please enter a number”");
        }
    }

    private static void printArrayList(List<Task> inputs) {
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
