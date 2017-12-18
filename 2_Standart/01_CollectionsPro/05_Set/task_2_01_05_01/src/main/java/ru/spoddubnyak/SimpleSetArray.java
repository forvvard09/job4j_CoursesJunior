package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollectionsSet;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleArraySet describes the actions of an array-based container.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.07.2017
 */
public class SimpleSetArray<E> implements ISet<E> {
    /**
     * property default size SimpleSetArray.
     */
    private static  final int DEFOULT_SIZE = 10;
    /**
     * property array Objects.
     */
    private Object[] container;
    /**
     * property index.
     */
    private int index = 0;

    /**
     * Constructor it creates a new Object[] container with the specified values.
     *
     * @param size - set zize Object[]
     */
    public SimpleSetArray(int size) {
        this.container = new Object[size];
    }
    /**
     * Constructor it creates a new Object[] container with the specified values.
     *
     */
    public SimpleSetArray() {
        this.container = new Object[this.DEFOULT_SIZE];
    }
    /**
     * Method return Object in collections of index.
     *@param i index in collections SimleSetArray
     *@return value element in collections
     *
     */
    public Object getElement(int i) {
        return this.container[i];
    }
    /**
     * Method checks if there is a collection in the collection of the same object.
     *@param e object
     *@return true - if not dublicates, false - if dublicates is
     *
     */
    private boolean checkedDublicates(E e) {
        boolean valid = true;
        int i = 0;
        while (valid && i <= this.index) {
            if (e.equals(this.container[i])) {
                valid = false;
            }
            i++;
        }
        return valid;
    }
    /**
     * Method search Object in collections SimleSetArray.
     *@param valueSearch object for search
     *@return true - index in collections
     *
     */
    private int search(E valueSearch) {
        int result = -1;
        for (int i = 0; i < this.index; i++) {
            if (this.container[i].equals(valueSearch)) {
                result = i;
                break;
            }
            i++;
        }
        return result;
    }
    /**
     * Method increase size container SimleSetArray.
     *
     */
    private void increaseSize() {
        this.container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    /**
     * Method decrease size container SimleSetArray.
     *@param indexElement - index Object in collections for remove
     */
    private void decreaseSize(int indexElement) {
        for (int i = indexElement; i < this.index; i++) {
            this.container[i] = this.container[i + 1];
        }
        this.index--;
        if (this.container.length != this.DEFOULT_SIZE && this.index < (getSizeSet() / 2)) {
            this.container = Arrays.copyOf(this.container, this.container.length / 2);
        }
    }

    @Override
    public void add(E e) {
        if (checkedDublicates(e)) {
            if (this.index == this.container.length) {
                increaseSize();
            }
            this.container[this.index] = e;
            this.index++;
        }
    }

    @Override
    public int getSizeSet() {
        return this.index;
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.index ? false : true;
    }

    @Override
    public boolean remove(E e) {
        if (0 == getSizeSet()) {
            throw new NotElementsInCollectionsSet("An attempt to delete from an empty collection Set.");
        }
        boolean resultRemove = false;
        int indexElement = search(e);
        if (indexElement != -1) {
            resultRemove = true;
            decreaseSize(indexElement);
        }
        return resultRemove;
    }

    @Override
    public Iterator<E> iterator() {
        return new MySetArrayIterator();
    }

    /**
     * Iterator class for SimpleSetArray.
     */
    private class MySetArrayIterator implements Iterator<E> {
        /**
         * property current position.
         */
        private int positionIterator = 0;

        @Override
        public boolean hasNext() {
            return this.positionIterator < getSizeSet();
        }

        @Override
        public E next() {
            E result = null;
            if (hasNext()) {
                result = (E) getElement(this.positionIterator++);
            } else {
                throw new IndexOutOfBoundsException("Went beyond the collection.");
            }
            return result;
        }
    }
}
