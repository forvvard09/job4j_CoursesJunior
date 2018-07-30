package ru.spoddubnyak.errors;

/**
 * Class MenuOutException outside the menu exit error.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 09.02.2017
 */
public class MenuOutException extends RuntimeException {

    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
