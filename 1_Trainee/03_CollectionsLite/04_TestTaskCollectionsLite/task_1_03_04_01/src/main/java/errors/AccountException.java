package errors;

/**
 * Class AccountException errors of a accounts.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class AccountException extends Exception {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public AccountException(String msg) {
        super(msg);
    }
}


