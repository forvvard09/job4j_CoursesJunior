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
     * Constructor it creates a new object SimpleLinkedList container with the specified values.
     */
    public SimpleLinkedList() {
        this.lastElement = new Element<E>(null, this.firstElement, null);
        this.firstElement = new Element<E>(null, null, this.lastElement);
    }

    /**
     * Getter for property size.
     * @return property size, number of items in the SimpleLinkedList
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(E value) {
        Element<E> temp = lastElement;
        temp.setValueElement(value);
        lastElement = new Element<E>(null, temp, null);
        temp.nextElement = lastElement;
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
    private static class Element<E> {
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