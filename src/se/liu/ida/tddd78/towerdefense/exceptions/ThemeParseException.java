package se.liu.ida.tddd78.towerdefense.exceptions;

public class ThemeParseException extends ThemeLoadException {

    public ThemeParseException(String message) {
        super(message);
    }

    public ThemeParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
