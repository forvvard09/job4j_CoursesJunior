package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollectionsSet;

import java.util.Iterator;

/**
 * Class SimpleLinkedSet describes the actions of the container on the basis of a bidirectional list.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.07.2017
 */
public class SimpleSetLinkedList<E> implements ISet<E> {
    /**
     * property for a pointer to the first element.
     */
    private Node<E> firstElement;
    /**
     * property for a pointer to the last element.
     */
    private Node<E> lastElement;

    /**
     * property size collection, number of items in the SimpleSetLinkedList.
     */
    private int size = 0;

    /**
     * Constructor it creates a new object SimpleSetLinkedList container with the specified values.
     */
    public SimpleSetLinkedList() {
        this.firstElement = new Node<E>();
        this.lastElement = new Node<E>();
        this.firstElement.next = this.lastElement;
        this.lastElement.prev = this.firstElement;
        this.firstElement.prev = this.lastElement;
        this.lastElement.next = this.firstElement;
    }

    /**
     * Getter for property size.
     *
     * @return property size, number of items in the SimpleSetLinkedList
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Setter for property size.
     *
     * @param size new size for SimpleSetLinkedList
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Method checks if there is a collection in the collection of the same object.
     *
     * @param e object
     * @return true - if not dublicates, false - if dublicates is
     */
    private boolean checkedDublicates(E e) {
        boolean valid = true;
        Node<E> temporaryLink = this.firstElement.next;
        while (valid && temporaryLink != this.lastElement) {
            if (e.equals(temporaryLink.getValueNode())) {
                valid = false;
            }
            temporaryLink = temporaryLink.next;
        }
        return valid;
    }

    /**
     * Method return Node by index.
     *
     * @param index - index in collections Set
     * @throws NotElementsInCollectionsSet - if, when you try to access an item in an empty collection
     * @return link by Object
     */
    public Node<E> getElement(int index) throws NotElementsInCollectionsSet {
        if (index < 0 && index > getSize()) {
            throw new NotElementsInCollectionsSet("An attempt to delete from an empty collection Set.");
        }
        Node<E> temporaryNode = this.firstElement.next;
        for (int i = 0; i < index; i++) {
            temporaryNode = temporaryNode.next;
        }
        return temporaryNode;
    }

    /**
     * Method return Node by index.
     *
     * @param node - link by Node
     * @return value Node
     */
    public E getElementValue(Node<E> node) {
        return node.getValueNode();
    }


    @Override
    public void add(E e) {
        if (checkedDublicates(e)) {
            Node<E> newNode = new Node<E>(e, this.lastElement, this.lastElement.prev);
            this.lastElement.prev.next = newNode;
            this.lastElement.prev = newNode;
            setSize(getSize() + 1);
        }
    }

    @Override
    public int getSizeSet() {
        return getSize();
    }

    @Override
    public boolean isEmpty() {
        return 0 == getSize() ? false : true;
    }

    /**
     * Method return index in Set.
     *
     * @param e - Node for find
     * @return index in collections, or -1, if not found Node
     */
    private int searchValue(E e) {
        int result = -1;
        if (0 < getSize()) {
            Node<E> temporaryLink = this.firstElement.next;
            boolean valid = true;
            int i = 0;
            while (valid && temporaryLink != this.lastElement) {
                if (e.equals(temporaryLink.getValueNode())) {
                    valid = false;
                    result = i;
                }
                i++;
                temporaryLink = temporaryLink.next;
            }
        }
        return result;
    }

    @Override
    public boolean remove(E e) throws NotElementsInCollectionsSet {
        boolean result = false;
        int index = searchValue(e);
        if (getSize() == 0) {
            throw new NotElementsInCollectionsSet("Went beyond the collection.");
        } else if (-1 != index) {
            Node<E> temporaryLink = getElement(index);
            temporaryLink.prev.next = temporaryLink.next;
            temporaryLink.next = temporaryLink.prev;
            temporaryLink.prev = null;
            temporaryLink.next = null;
            temporaryLink.setValueNode(null);
            result = true;
            setSize(getSize() - 1);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIteratorSetLinkedLis();
    }

    /**
     * Iterator class for SimpleSetArray.
     */
    private class MyIteratorSetLinkedLis implements Iterator<E> {
        /**
         * property current position.
         */
        private int indexIterator = 0;

        @Override
        public boolean hasNext() {
            return this.indexIterator < getSize();
        }

        @Override
        public E next() {
            E result;
            if (hasNext()) {
                result = getElementValue(getElement(this.indexIterator++));
            } else {
                throw new NotElementsInCollectionsSet("Went beyond the collection.");
              }
            return result;
        }
    }

    /**
     * Inner private Class to create a doubly-linked list of elements.
     *
     * @param <E> This describes my type parameter
     */
    private class Node<E> {
        /**
         * property for a pointer the element.
         */
        private E value;
        /**
         * property for a pointer to next element.
         */
        private Node<E> next;
        /**
         * property for a pointer to prevision element.
         */
        private Node<E> prev;

        /**
         * Constructor it creates a new object Node with the specified.
         *
         * @param value value for Node
         * @param prev  link of previus Node
         * @param next  link of next Node
         */
        Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        /**
         * Constructor it creates a new object Node with the specified.
         *
         */
        Node() {
            this.value = null;
            this.next = null;
            this.prev = null;
        }

        /**
         * Getter for property value.
         * @return value Node
         */
        private E getValueNode() {
            return this.value;
        }

        /**
         * Setter for property value.
         * @param value value for Node
         */
        private void setValueNode(E value) {
            this.value = value;
        }
    }
}