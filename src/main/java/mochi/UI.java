package mochi;

import java.io.IOException;

import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;
import mochi.task.FileHandler;
import mochi.task.Task;

import static mochi.Parser.isPrinting;

/**
 * Handles most UI interactions like welcome screen and goodbye string
 **/

public class UI {

    public void welcomeMessage() {
        String logo = """
                ____________________________________________________________
                Hello! I'm Mochi
                What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(logo);
    }

    /**
     * Prints current number of items in list when adding a event
     * @param task task to be printed out
     * @param taskListSize tasklist size to be printed
     */
    public void addEventSuccess(Task task, int taskListSize) {
        if (isPrinting) {
            System.out.println("____________________________________________________________");
            System.out.println("Got it! I've added this task for you :3");
            System.out.println(task.toString());
            System.out.println("You now have " + taskListSize + " tasks in the list");
            System.out.println("____________________________________________________________");
        }
    }


    /**
     * Prints current number of items in list when deleting an event
     * @param task task to be printed out
     * @param taskListSize tasklist size to be printed
     */
    public void deleteEventSuccess(String task, int taskListSize) {
        if (isPrinting) {
            System.out.println("____________________________________________________________");
            System.out.println("Ooooke I have deleted this task for you");
            System.out.println(task);
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
