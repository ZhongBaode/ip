package mochi;

import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;
import mochi.task.Commands;
import mochi.task.FileHandler;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the user input data. Data should be formatted as such [Action] [Description]
 * Actions are defined in Commands Class
 * Data is throughly checked to have both Action and Description and loops in here till user
 * cancels or types "bye"
 *
 */

public class Parser {
    public static final int CMD_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;
    public static boolean isPrinting = true;
    //used to exit program
    private boolean isRunning = true;
    private static UI ui = new UI();
    private TaskList taskList = new TaskList();

    /**
     * main method that loads and saves the taskslists and keeps the programming running continuously
     * @throws MissingArgumentException if handle method has no missing argument for other methods.
     * @throws IOException when handle method fails to save the file
     * @throws  MissingDescription if handle method has missing description
     */

    void runMain() throws IOException, MissingArgumentException, MissingDescription {
        FileHandler fileHandler = new FileHandler();
        fileHandler.returnFileContentArray(taskList.getTaskList());
        ui.welcomeMessage();
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
        ui.goodByeMessage();
    }

    /**
     * handles data input and executes the command
     * @param raw Entire user input
     * @throws MissingArgumentException if handle method has no missing argument for other methods.
     * @throws IOException when handle method fails to save the file
     * @throws  MissingDescription if handle method has missing description
     */

    protected void handle(String raw) throws MissingArgumentException, MissingDescription, IOException {
        if(raw.isEmpty()){
            throw new MissingArgumentException();
        }
        String input = raw.trim();
        String[] splits = input.split("\\s+", 2);
        Commands command = null;
        command = Commands.fromString(splits[CMD_INDEX]);
        switch (command){
        case BYE -> {
            this.isRunning = false;
            FileHandler fh = new FileHandler();
            fh.saveFile(taskList.getTaskList());
            return;
        }
        case LIST -> {
            taskList.printArrayList();
            return;
        }
        }

        if(splits.length == 1 && command != Commands.UNKNOWN){
            throw new MissingDescription();
        }

        switch (command){
        case DEADLINE -> taskList.insertDeadline(splits[DESCRIPTION_INDEX]); //#TODO Error handle when deadline description is empty
        case MARK ->  taskList.markAsDone(input);
        case UNMARK -> taskList.markAsUndone(input);
        case TODO -> taskList.insertTodo(splits[DESCRIPTION_INDEX]);
        case EVENT -> taskList.insertEvent(splits[DESCRIPTION_INDEX]);
        case DELETE -> taskList.deleteTask(splits[DESCRIPTION_INDEX]);
        case FIND -> taskList.findTask(splits[DESCRIPTION_INDEX]);
        default -> System.out.println("Sorry command not recognized! Try again :) Type help to see list of commands!");
        }
    }
}
