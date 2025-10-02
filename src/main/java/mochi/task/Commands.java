package mochi.task;

import mochi.exceptions.MissingArgumentException;
import mochi.exceptions.MissingDescription;

import java.io.IOException;

/**
 * Enum class that contains all possible commands.
 **/
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
    ;


    //Converts a string into a Command, or UNKNOWN if invalid.
    /**
     * Checks if input is valid
     * @param input checks for this specific input
     */

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
