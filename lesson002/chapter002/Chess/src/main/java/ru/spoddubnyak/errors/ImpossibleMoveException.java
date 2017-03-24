package ru.spoddubnyak.errors;

/**
 * Class ChessBoardException errors of a chessboard.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.03.2017
 */
public class ImpossibleMoveException extends Exception {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
