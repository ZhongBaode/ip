package mochi.task;
/**
 * Deadline task format is [deadline] [Description] [/by] [Date]
 **/
public class Deadline extends Task{

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String getTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + endTime + ")";
    }
}
