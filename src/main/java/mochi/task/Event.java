package mochi.task;
/**
 * Event task format is [event] [Description] [/from] [Date1] [/to] [Date2]
 **/
public class Event extends Task{
    private final String startTime;
    private final String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + startTime + ", to:" + endTime + ")";
    }
}
