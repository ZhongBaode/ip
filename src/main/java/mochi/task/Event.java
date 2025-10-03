package mochi.task;
/**
 * Event task format is [event] [Description] [/from] [Date1] [/to] [Date2]
 **/
public class Event extends Task{


    public Event(String description, String startTime, String endTime) {
        super(description, startTime, endTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    
    @Override
    public String getTime() {
        return startTime + " - " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + startTime + ", to:" + endTime + ")";
    }
}
