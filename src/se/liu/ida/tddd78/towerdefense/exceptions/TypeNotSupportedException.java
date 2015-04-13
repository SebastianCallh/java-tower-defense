package se.liu.ida.tddd78.towerdefense.exceptions;

/**
 * Exception indicating there is a missing implementation for the supplied type, or that the supplied type is null
 */
public class TypeNotSupportedException extends Exception {

    public TypeNotSupportedException(String message) {
        super(message);
    }

}
