package ru.spoddubnyak;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleArrayList thread-safe describes the actions of an array-based container.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 20.01.2018
 */
@ThreadSafe
public class SimpleArrayList<E> implements SimpleContainer<E> {
    /**
     * property default size SimpleArrayList.
     */
    private static final int START_SIZE = 10;
    /**
     * property index.
     */
    @GuardedBy("this")
    private int index = 0;
    /**
     * property array Objects.
     */
    @GuardedBy("this")
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
     */
    public SimpleArrayList() {
        this.container = new Object[START_SIZE];
    }

    @Override
    public void add(Object value) {
        synchronized (this) {

            if (this.container.length == this.index) {
                increaseSize();
            }
            this.container[index++] = value;
        }
    }


    /**
     * Auxiliary method return size Object[].
     *
     * @return size Object[]
     */
    public synchronized int getLengthArrayList() {
        return this.container.length;
    }

    /**
     * Auxiliary method return count elemnt's in Object[].
     *
     * @return count elements in Object[]
     */
    public int getCount() {
        synchronized (this) {
            return this.index;
        }
    }

    /**
     * Method increases the size of the Object[] 2 times.
     */
    private void increaseSize() {
        synchronized (this) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
    }

    @Override
    public E get(int index) {
        synchronized (this) {
            return (E) this.container[index];
        }
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
            synchronized (SimpleArrayList.this) {
                return this.positionIterator < index;
            }
        }

        @Override
        public E next() {
            Object result = null;
            synchronized (SimpleArrayList.this) {
                if (this.hasNext()) {
                    result = container[positionIterator++];

                } else {
                    throw new IndexOutOfBoundsException("Went beyond the collection.");
                }
                return (E) result;
            }
        }
    }
}
