package ru.spoddubnyak.guide.errors;

/**
 * Class NotElementsInCollections errors not elements in collections.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.09.2017
 */
public class NotFindsElementsInCollections extends RuntimeException {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public NotFindsElementsInCollections(String msg) {
        super(msg);
    }
}
