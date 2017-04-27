package ru.spoddubnyak;

import java.util.Iterator;

/**
 * Class implements an iterator for a two-dimensional array.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 24.04.2017
 */
public class IteratorEvenNumber implements Iterator {
    /**
     * property values - array with input parameters for iteration.
     */
    private final int[] values;

    /**
     * property index - position in values.
     */
    private int index = 0;

    /**
     * property lastEvenElement - last position even number in values.
     */
    private int lastEvenElement = 0;

    /**
     * Constructor it creates a new object IteratorMultiArray with the specified values.
     *
     * @param values - set property value
     */
    public IteratorEvenNumber(final int[] values) {
        int[] copyValues = new int[values.length];
        System.arraycopy(values, 0, copyValues, 0, values.length);
        this.values = copyValues;
    }

    @Override
    public boolean hasNext() {
        if (this.lastEvenElement == 0) {
                 findLastEvenElement();
        }
        return this.index <= this.lastEvenElement;
    }

    @Override
    public Object next() {
        int result = 0;
        do {
            result = this.values[this.index++];
        } while (!validEvenNumber(result) && hasNext());
        return result;
    }

    /**
     * Helper method that checks an even number.
     *
     * @param number - number for checks
     * @return true if number even, false if number not even
     */
    private boolean validEvenNumber(int number) {
        return 0 == number % 2;
    }

    /**
     * Helper method find in values last even number.
     */
    private void findLastEvenElement() {
        for (int i = this.values.length - 1; i >= 0; i--) {
            if (validEvenNumber(this.values[i])) {
                this.lastEvenElement = i;
                break;
            } else {
                this.lastEvenElement = -1;
            }
        }
    }
}

