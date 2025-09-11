public class Task {
    protected String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
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

    public String toString() {
        return ("[" + getStatusIcon() + "] " + getDescription());
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
}
