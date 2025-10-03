package mochi.task;

/**
 * Todo task format is [todo] [Description]
 **/
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
