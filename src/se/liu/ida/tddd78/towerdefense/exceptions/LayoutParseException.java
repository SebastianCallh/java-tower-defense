package se.liu.ida.tddd78.towerdefense.exceptions;

/**
 * Exception thrown when a parsed layout does not meet the format requirements specified by the parser
 */
public class LayoutParseException extends Exception {

    public LayoutParseException(String message) {
        super(message);
    }

}
