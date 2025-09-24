package mochi.task;

import mochi.Exceptions.MissingArgumentException;
import mochi.Exceptions.MissingDescription;
import mochi.Mochi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileHandler extends Mochi {
     public void returnFileContentArray(String filePath, List<Task> taskLists) throws FileNotFoundException, MissingArgumentException, MissingDescription {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        isPrinting = false;
        while (s.hasNext()) {
            handle(s.nextLine());
        }
        isPrinting = true;
    }

    public void saveFile(String filePath, List<Task> taskLists) throws IOException{
         FileWriter fw = new FileWriter(filePath);
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
