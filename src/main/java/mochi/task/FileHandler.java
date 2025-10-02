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

//#TODO Handle when file is corrupted or not there
public class FileHandler extends Parser {

    private static final Path FILE_PATH = Paths.get("data", "save.txt");

    private static void ensureFile() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH); // empty file
        }
    }

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
                 fw.write("deadline " + task.getDescription() + "/by" + ((Deadline) task).getBy() + System.lineSeparator());
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
