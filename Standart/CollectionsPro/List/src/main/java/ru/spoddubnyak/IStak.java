package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollections;

/**
 * Interface .
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.07.2017
 */
public interface IStak<E> {
    /**
     * Method puts an object in MyStack.
     *@param value - value element collections
     *
     */
    void push(E value);

    /**
     * Method pulls an object from the stack.
     *
     * @throws NotElementsInCollections - if, when you try to access an item in an empty collection
     */
    void pop() throws NotElementsInCollections;

    /**
     * Method return last object from the stack.
     * @throws NotElementsInCollections - if, when you try to access an item in an empty collection
     * @return last Object in collections
     */
    E peek() throws NotElementsInCollections;


    /**
     * The method looks for the given element on the stack, returning the number of pop operations that are required to translate the element to the top of the stack.
     * If the specified element is not present on the stack, this method returns -1.
     *
     * @param value search object
     * @return the number of pop operations that are required to translate the element to the top of the stack or -1, if the specified element is not present on the stack
     */
    int search(E value);

    /**
     * The method checks the MyStack for elements .
     *
     * @return true if the stack is empty
     */
    boolean empty();
}
