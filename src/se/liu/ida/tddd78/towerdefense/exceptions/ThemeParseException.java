package se.liu.ida.tddd78.towerdefense.exceptions;

/**
 * Exception thrown when the parsing of a theme fails for some reason, due to for example an incorrect tag or constant.
 */
public class ThemeParseException extends ThemeLoadException {

    public ThemeParseException(String message) {
        super(message);
    }

    public ThemeParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
