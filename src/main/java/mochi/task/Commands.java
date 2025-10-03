package mochi.task;

public enum Commands {
    BYE,
    DEADLINE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    EVENT,
    UNKNOWN,
    DELETE,
    FIND,
    FINDTIME,
    ;


    //Converts a string into a Command, or UNKNOWN if invalid.

    public static Commands fromString(String input){
        if (input == null) {
            return UNKNOWN;
        }
        try {
            return Commands.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
