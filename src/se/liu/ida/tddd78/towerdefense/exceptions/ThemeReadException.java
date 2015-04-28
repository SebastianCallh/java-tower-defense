package se.liu.ida.tddd78.towerdefense.exceptions;

/**
 * Exception thrown when a theme or part of it fails to load from disk or storage device.
 */
public class ThemeReadException extends ThemeLoadException {

    public ThemeReadException(String message) {
        super(message);
    }

    public ThemeReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
