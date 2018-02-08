package ru.spoddubnyak.model;

/**
 * Enum for atributes xml.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 07.02.2018
 */
public enum XMLAttributes {
    /**
     * property attributes book.
     */
    NAME_BOOK("book"),
    /**
     * property attributes AddOrder.
     */
    BOOK_ADD("AddOrder"),
    /**
     * property attributes DeleteOrder.
     */
    BOOK_DEL("DeleteOrder"),
    /**
     * property attributes operation.
     */
    OPERATION("operation"),
    /**
     * property attributes price.
     */
    PRICE("price"),
    /**
     * property attributes volume.
     */
    VOLUME("volume"),
    /**
     * property attributes orderId.
     */
    ORDER_ID("orderId"),
    /**
     * property attributes BUY.
     */
    OPERATION_BUY("BUY"),
    /**
     * property attributes SELL.
     */
    OPERATION_SELL("SELL");

    /**
     * property atributes.
     */
    private String atributes;

    /**
     * Constructor it creates a new object XMLAttributes container with the specified values.
     *
     * @param atributes attributes for create
     */
    XMLAttributes(String atributes) {
        this.atributes = atributes;
    }

    /**
     * Getter for property atributes.
     *
     * @return link property bid
     */
    public String getAtributes() {
        return this.atributes;
    }
}
