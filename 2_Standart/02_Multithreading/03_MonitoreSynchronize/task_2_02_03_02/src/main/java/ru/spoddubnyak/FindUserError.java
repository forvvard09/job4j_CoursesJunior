package ru.spoddubnyak;

/**
 * Class FindUserError errors of a finds Usesrs in Storage.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.01.2018
 */
public class FindUserError extends Exception {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public FindUserError(String msg) {
        super(msg);
    }
}
