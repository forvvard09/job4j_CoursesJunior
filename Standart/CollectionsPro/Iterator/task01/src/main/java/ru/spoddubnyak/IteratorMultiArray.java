package ru.spoddubnyak;

import java.util.Iterator;

/**
 * Class implements an iterator for a two-dimensional array.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 24.04.2017
 */
public class IteratorMultiArray implements Iterator {

    /**
     * property values - array with input parameters for iteration.
     */
    private final int[][] values;

    /**
     * property column - column in values.
     */
    private int column = 0;

    /**
     * property row - row in values.
     */
    private int row = 0;

    /**
     * Constructor it creates a new object IteratorMultiArray with the specified values.
     *
     * @param values - set property value
     */
    public IteratorMultiArray(final int[][] values) {
        int[][] copyValues = values.clone();
        this.values = copyValues;
    }

    @Override
    public boolean hasNext() {
        return this.column < values.length && this.row < values[this.column].length;
    }

    @Override
    public Object next() {
        int result = values[this.column][this.row++];
        if (this.row == values[this.column].length && this.column < values.length) {
            this.column++;
            this.row = 0;
        }
        return result;
    }
}