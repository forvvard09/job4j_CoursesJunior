package ru.spoddubnyak;

import java.util.Iterator;

/**
 * Class implements an iterator prime number.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.04.2017
 */
public class IteratorPrimeNumber implements Iterator {

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
    private int lastPrimeElement = 0;

    /**
     * Constructor it creates a new object IteratorEvenNumber with the specified values.
     *
     * @param values - set property value
     */
    public IteratorPrimeNumber(final int[] values) {
        int[] copyValues = new int[values.length];
        System.arraycopy(values, 0, copyValues, 0, values.length);
        this.values = copyValues;
    }

    @Override
    public boolean hasNext() {
        if (this.lastPrimeElement == 0) {
            findLastPrimeElement();
        }
        return this.index <= this.lastPrimeElement;
    }

    @Override
    public Object next() {
        int result;
        do {
            result = this.values[this.index++];
        } while (!validEvenNumber(result) && hasNext());
        return result;
    }

    /**
     * Helper method that checks an prime number.
     *
     * @param number - number for checks
     * @return true if number prime, false if number not even
     */
    private boolean validEvenNumber(int number) {
        boolean result = true;
        if (number > 1) {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    result = false;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Helper method find in values last prime number.
     */
    private void findLastPrimeElement() {
        for (int i = this.values.length - 1; i >= 0; i--) {
            if (validEvenNumber(this.values[i])) {
                this.lastPrimeElement = i;
                break;
            } else {
                this.lastPrimeElement = -1;
            }
        }
    }
}