package ru.spoddubnyak;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class сonverter o traverse iterator iterators .
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 01.05.2017
 */
public class IteratorConvert implements Converter, Iterator {
    /**
     * property iteratorIterator - iterator iterators.
     */
    private Iterator<Iterator<Integer>> iteratorIterator;

    /**
     * property iterator - inner iterator.
     */
    private Iterator<Integer> iterator;

    /**
     * Method converts from several iterators into one iterator.
     *
     * @param it - input iterators
     * @return one iterator
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iteratorIterator = it;
        this.iterator = this.iteratorIterator.next();
        return this;
    }

    /**
     * The method checks to see if the end of the internal iterator or the iterator list is reached.
     *
     */
    @Override
    public boolean hasNext() {
        return  this.iteratorIterator.hasNext() || this.iterator.hasNext();
    }

    /**
     * The method checks to see if the end of the internal iterator or the iterator list is reached iterator iterators.
     * @ return next element by inner iterator
     */
    @Override
    public Object next() {
        Integer resultNext = null;
        if (this.iterator.hasNext()) {
            resultNext = this.iterator.next();
        } else if (this.iteratorIterator.hasNext()) {
            this.iterator = this.iteratorIterator.next();
            resultNext = this.iterator.next();
        }
        if (resultNext == null) {
            throw new NoSuchElementException("Error. Incorrect input.");
        }
        return resultNext;
    }
}
