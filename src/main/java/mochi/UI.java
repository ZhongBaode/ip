package mochi;

import java.io.IOException;
import mochi.task.FileHandler;
import static mochi.Mochi.SAVE_FILE_PATH;
import static mochi.Mochi.taskList;

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


    public void goodByeMessage() throws IOException {
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
