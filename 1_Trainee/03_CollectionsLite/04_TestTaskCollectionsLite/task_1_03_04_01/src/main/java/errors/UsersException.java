package errors;

/**
 * Class User errors of a user.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class UsersException extends Exception {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public UsersException(String msg) {
        super(msg);
    }
}


