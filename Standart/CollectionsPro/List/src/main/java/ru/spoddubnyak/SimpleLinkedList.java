package ru.spoddubnyak;
import java.util.Iterator;

/**
 * Class SimpleLinkedList describes the actions of the container on the basis of a bidirectional list.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.07.2017
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * property for a pointer to the first element.
     */

    private Element<E> firstElement;
    /**
     * property for a pointer to the last element.
     */
    private Element<E> lastElement;
    /**
     * property size collection, number of items in the SimpleLinkedList.
     */
    private int size = 0;

    /**
     * Getter for first element.
     * @return link first element
     */
    public Element<E> getFirstElement() {
        return this.firstElement;
    }

    /**
     * Constructor it creates a new object SimpleLinkedList container with the specified values.
     */
    SimpleLinkedList() {
        this.lastElement = new Element<E>();
        this.firstElement = new Element<E>();
        this.firstElement.nextElement = this.lastElement;
        this.firstElement.previusElement = this.lastElement;
        this.lastElement.previusElement = this.firstElement;
        this.lastElement.nextElement = this.firstElement;
    }

    /**
     * Getter for property size.
     * @return property size, number of items in the SimpleLinkedList
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Method removeLastElement last element in collections SimpleLinledList<E>.
     * @throws IndexOutOfBoundsException - if, not elements in collections for removing
     */
    public void removeLastElement() throws IndexOutOfBoundsException {
        if (0 == getSize()) {
            throw new IndexOutOfBoundsException("There are no objects to delete in the collection.");
        }
        Element<E> temporaryLink = this.lastElement.previusElement.previusElement;
        this.lastElement.previusElement.previusElement.nextElement = null;
        this.lastElement.previusElement.previusElement = null;
        this.lastElement.previusElement.nextElement = null;
        this.lastElement.previusElement = temporaryLink;
        this.lastElement.previusElement.nextElement = this.lastElement;
        temporaryLink = null;
        this.size--;
    }

    /**
     * Method removeFirstElement first element in collections SimpleLinledList<E>.
     * @throws IndexOutOfBoundsException - if, not elements in collections for removing
     */
    public void removeFirstElement() throws IndexOutOfBoundsException {
        if (0 == getSize()) {
            throw new IndexOutOfBoundsException("There are no objects to delete in the collection.");
        }
        Element<E> temporaryLink = this.firstElement.nextElement.nextElement;
        this.firstElement.nextElement.nextElement.previusElement = null;
        this.firstElement.nextElement.nextElement = null;
        this.firstElement.nextElement.previusElement = null;
        this.firstElement.nextElement = temporaryLink;
        this.firstElement.nextElement.previusElement = this.firstElement;
        temporaryLink = null;
        this.size--;
    }

    @Override
    public void add(E value) {
        Element<E> newElement = new Element<E>(value, lastElement.previusElement, lastElement);
        lastElement.previusElement.nextElement = newElement;
        lastElement.previusElement = newElement;
        this.size++;
    }

    @Override
    public E get(int index) {
        if (getSize() <= index) {
            throw new IndexOutOfBoundsException("Went beyond the collection.");
        }
        Element<E> resultElement = this.firstElement.nextElement;
        for (int i = 0; i < index; i++) {
            resultElement = resultElement.nextElement;
        }
        return resultElement.getValueElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<E>();
    }

    /**
     * Inner private Class to create a doubly-linked list of elements.
     *
     * @param <E> This describes my type parameter
     */
    public class Element<E> {
        /**
         * property for a pointer to the first element.
         */
        private E valueElement;
        /**
         * property for a pointer to the first element.
         */
        private Element<E> previusElement;
        /**
         * property for a pointer to the first element.
         */
        private Element<E> nextElement;

        /**
         * Constructor it creates a new object Element with the specified.
         *
         * @param value value for Element
         * @param prev  link of previus Element
         * @param next  link of next Element
         */
        Element(E value, Element<E> prev, Element<E> next) {
            this.valueElement = value;
            this.previusElement = prev;
            this.nextElement = next;
        }

        /**
         * Constructor it creates a new object Element with the specified.
         *
         */
        Element() {
            this.valueElement = null;
            this.previusElement = null;
            this.nextElement = null;
        }

        /**
         * Getter for property valueElement.
         * @return value Element
         */
        public E getValueElement() {
            return this.valueElement;
        }

        /**
         * Setter for property valueElement.
         * @param valueElement value for Element
         */
        public void setValueElement(E valueElement) {
            this.valueElement = valueElement;
        }

        /**
         * Getter for link of previus element.
         * @return link of previus element
         */
        public Element<E> getPreviusElement() {
            return previusElement;
        }

        /**
         * Getter for link of next element.
         * @return link of next element
         */
        public Element<E> getNextElement() {
            return nextElement;
        }
    }

    /**
     * Inner Class MyArrayListIterator implement the iterator .
     *
     * @param <E> This describes my type parameter
     */
    private class MyLinkedListIterator<E> implements Iterator<E> {
        /**
         * property index in Iterator.
         */
        private int positionIterator = 0;

        @Override
        public boolean hasNext() {
            return this.positionIterator < getSize();
        }

        @Override
        public E next() {
            E result = null;
            if (this.hasNext()) {
                result = (E) get(this.positionIterator++);
            } else {
                throw new IndexOutOfBoundsException("Went beyond the collection.");
            }
            return result;
        }
    }
}