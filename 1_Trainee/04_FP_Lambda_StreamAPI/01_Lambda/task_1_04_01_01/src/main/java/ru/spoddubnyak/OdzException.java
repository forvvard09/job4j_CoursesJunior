package ru.spoddubnyak;

/**
 * OdzException - error with nonconformance.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.12.2018
 */
public class OdzException extends Exception {

    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public OdzException(String msg) {
        super(msg);
    }
}
