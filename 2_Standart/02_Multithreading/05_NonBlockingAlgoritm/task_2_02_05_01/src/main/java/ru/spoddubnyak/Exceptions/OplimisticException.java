package ru.spoddubnyak.Exceptions;

/**
 * Class OplimisticException errors while trying to make changes Model in class CacheNonBlock.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2018
 */
public class OplimisticException extends RuntimeException {
    /**
     * Method error processing.
     */
    public OplimisticException() {
        super("Error. Oplimistic exception.");
    }
}