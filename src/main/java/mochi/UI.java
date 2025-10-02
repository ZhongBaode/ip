package mochi;

import java.io.IOException;
import mochi.task.FileHandler;
import mochi.task.Task;

import static mochi.Parser.isPrinting;

public class UI {
    public UI() {
    }

    public void welcomeMessage() {
        String logo = """
                ____________________________________________________________
                Hello! I'm Mochi
                What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(logo);
    }


    public void addEventSuccess(Task task, int taskListSize) {
        if (isPrinting) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it! I've added this task for you :3");
            System.out.println(task.toString());
            System.out.println("You now have " + taskListSize + " tasks in the list");
            System.out.println("____________________________________________________________");
        }
    }

    public void goodByeMessage() throws IOException {
        String logo = """
                ____________________________________________________________
                    Noot Noot
                ____________________________________________________________
                """;

        System.out.println(logo);
    }
}
