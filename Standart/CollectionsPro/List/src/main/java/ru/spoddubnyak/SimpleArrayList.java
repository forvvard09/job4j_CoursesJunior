package ru.spoddubnyak;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleArrayList describes the actions of the container.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.05.2017
 */
public class SimpleArrayList<E> implements SimpleContainer {
    /**
     * property default size SimpleArrayList.
     */
    private static final int START_SIZE = 10;
    /**
     * property index.
     */
    private int index = 0;
    /**
     * property array Objects.
     */
    private Object[] container;

    /**
     * Constructor it creates a new Object[] container with the specified values.
     *
     * @param size - set zize Object[]
     */
    public SimpleArrayList(int size) {
        this.container = new Object[size];
    }

    /**
     * Constructor it creates a new Object[] container with the specified values.
     *
     */
    public SimpleArrayList() {
        this.container = new Object[START_SIZE];
    }

    @Override
    public void add(Object value) {
        if (this.container.length == this.index) {
            increaseSize();
        }
        this.container[index++] = value;
    }

    /**
     * Auxiliary method return size Object[].
     *
     * @return size Object[]
     */
    public int getLengthArrayList() {
        return this.container.length;
    }

    /**
     * Method increases the size of the Object[] 2 times.
     *
     */
    private void increaseSize() {
        this.container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    @Override
    public Object get(int index) {
        return (E) this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<E>();
    }

    /**
     * Inner Class MyArrayListIterator implement the iterator .
     *
     * @param <E> This describes my type parameter
     */
    private class MyArrayListIterator<E> implements Iterator<E> {
        /**
         * property index in Iterator.
         */
        private int positionIterator = 0;


        @Override
        public boolean hasNext() {
            return this.positionIterator < index;
        }

        @Override
        public E next() {
            E result = null;
            if (this.hasNext()) {
                result = (E) container[positionIterator++];
            } else {
                throw new IndexOutOfBoundsException("Went beyond the collection.");
            }
            return result;
        }
    }
}
