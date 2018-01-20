package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollections;

/**
 * Class SimpleStack describes the actions of an array-based container MySimpleLinkedList.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.07.2017
 */
public class SimpleQueue<E> extends SimpleStack<E> {
    /**
     * property size Queue.
     */
    private int sizeQueue = 0;

    /**
     * Getter for property size.
     * @return property size Queue
     */
    public int getSizeQueue() {
        return sizeQueue;
    }

    /**
     * Setter for property size.
     * @param sizeQueue size Queue
     */
    public void setSizeQueue(int sizeQueue) {
        this.sizeQueue = sizeQueue;
    }

    /**
     * Method offer add an object in MyQueue.
     *
     * @param value - value element collections
     * @return - returns the result of the addition if true is added, if no false is added
     */
    public boolean offer(E value) {
        int size = 1 + getSizeQueue() + getSize();
        add(value);
        return size == getSize();
    }
    /**
     * Method pulls an object from the queue.
     *
     * @throws NotElementsInCollections - if, when you try to access an item in an empty collection
     */
    public void poll() throws NotElementsInCollections {
        getErrorNotElement();
        removeFirstElement();
    }

    /**
     * Method return last object from the stack.
     * @throws NotElementsInCollections - if, when you try to access an item in an empty collection
     * @return last Object in collections
     */
    public E peek() throws NotElementsInCollections {
        getErrorNotElement();
        return get(0);
    }
}