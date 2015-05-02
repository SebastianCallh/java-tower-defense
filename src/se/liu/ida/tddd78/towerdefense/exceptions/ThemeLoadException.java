package se.liu.ida.tddd78.towerdefense.exceptions;

/**
 * Base exception for all faults related to loading or parsing a theme.
 */
public class ThemeLoadException extends Exception {

    public ThemeLoadException(String message) {
        super(message);
    }

    public ThemeLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
