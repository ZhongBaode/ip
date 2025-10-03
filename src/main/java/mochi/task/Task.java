package mochi.task;

public abstract class Task {
    protected String description;
    private boolean isDone = false;
    protected String startTime;
    protected String endTime;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task(String description, String endTime) {
        this.description = description;
        this.endTime = endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return "";
    }

    public String toString() {
        return ("[" + getStatusIcon() + "] " + getDescription());
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
}
