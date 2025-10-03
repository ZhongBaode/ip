package mochi.task;

import mochi.Parser;
import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *  Handles the permanent storage of the data
 *  Can store and retrieve data stored under data folder -> save.txt
 *  if folder doesnt exist it will create it in order for safe relative path
 *
 *  save.txt is rewritten every time it saves and not appended, save format follows how
 *  the file should be typed into CMT
 *
 *  for example if a todo task was present it will save it as
 *  todo [Description]
 *  marked/unmarked [task position]
 */

public class FileHandler extends Parser {

    private static final Path FILE_PATH = Paths.get("data", "save.txt");

    /**
     * Ensures a directory relative to the save file is created
     * Creates the save file too
     * @throws IOException when handle method fails to create the file
     */

    private static void ensureFile() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH); // empty file
        }
    }

    /**
     * main method that loads taskslists from the save.txt
     * @param taskLists takes arraylist and populate with save data
     * @throws MissingArgumentException if handle method has no missing argument for other methods.
     * @throws IOException when handle method fails to save the file
     * @throws  MissingDescription if handle method has missing description
     */
    public void returnFileContentArray(List<Task> taskLists) throws IOException, MissingArgumentException, MissingDescription {
        try{
          ensureFile();
        }catch(Exception e){
            System.out.println("Error in FileHandler");
        }
        File f = new File(FILE_PATH.toUri()); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        isPrinting = false;
        while (s.hasNext()) {
            handle(s.nextLine());
        }
        isPrinting = true;
    }


    /**
     * Saves current tasklist into save.txt
     * @param taskLists takes arraylist and populate with save data
     * @throws IOException when handle method fails to save the file
     */
    public void saveFile(List<Task> taskLists) throws IOException{
        try{
            ensureFile();
        }catch(Exception e){
            System.out.println("Error in FileHandler");
        }
        FileWriter fw = new FileWriter(FILE_PATH.toFile()); // create a File for the given file path
        int i = 0;
         for (Task task : taskLists) {
             if(task instanceof Todo){
                 fw.write("todo " + task.getDescription() + System.lineSeparator());
             }else if(task instanceof Event){
                 fw.write("event " + task.getDescription() + "/from" + ((Event) task).getStartTime()
                         + "/to" + ((Event) task).getEndTime() + System.lineSeparator());
             }else if(task instanceof Deadline){
                 fw.write("deadline " + task.getDescription() + "/by" + ((Deadline) task).getTime() + System.lineSeparator());
             }else{
                 throw new RuntimeException();
             }

             if(task.getStatusIcon().equals("X")){
                 fw.write("mark " + i + System.lineSeparator());
             } else{
                 fw.write("unmark " + i + System.lineSeparator());
             }
             i++;
         }
         fw.close();
    }
}
