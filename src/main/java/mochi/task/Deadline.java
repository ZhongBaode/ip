package mochi.task;

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
