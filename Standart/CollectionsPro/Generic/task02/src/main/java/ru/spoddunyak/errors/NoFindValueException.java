package ru.spoddunyak.errors;

/**
 * Class NoFindValueException errors of a not find value in array in class SimoleArray.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.05.2017
 */
public class NoFindValueException extends Exception {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public NoFindValueException(String msg) {
        super(msg);
    }
}
