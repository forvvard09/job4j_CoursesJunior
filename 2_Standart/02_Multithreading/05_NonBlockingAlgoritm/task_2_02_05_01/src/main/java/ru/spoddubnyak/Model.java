package ru.spoddubnyak;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Model implement object consummer, and put elements out interanl queue and add in external list.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2018
 */
public class Model {
    /**
     * property field - text information.
     */
    private String field;
    /**
     * property version - version of model.
     */
    private final AtomicInteger version;

    /**
     * Constructor of new object Model.
     *
     * @param field  value property field
     */
    public Model(String field) {
        this.field = field;
        version = new AtomicInteger();
    }

    /**
     * Getter for property field.
     *
     * @return value property field
     */
    public String getField() {
        return this.field;
    }

    /**
     * Getter for property version.
     *
     * @return value property version
     */
    public int getVersion() {
        return this.version.get();
    }

    /**
     * Setter for property field.
     *
     * @param field  value property field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Method increment version.
     *
     */
    public void incrementVersion() {
        this.version.getAndIncrement();
    }
}
