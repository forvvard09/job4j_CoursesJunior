package ru.spoddubnyak.errors;

/**
 * Class NotElementsInCollections errors not elements in collections.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.07.2017
 */
public class NotElementsInCollectionsSet extends RuntimeException {
    /**
     * Method error processing.
     *
     * @param msg - message text
     */
    public NotElementsInCollectionsSet(String msg) {
        super(msg);
    }
}