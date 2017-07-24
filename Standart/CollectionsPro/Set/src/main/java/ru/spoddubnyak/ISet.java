package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollectionsSet;

/**
 * Interface ISet, methods for the collection set.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.07.2017
 */
public interface ISet<E> extends Iterable<E> {
    /**
     * Method puts an object in collections.
     *@param e - value element collections
     *
    */
    void add(E e);
    /**
     * Method return size collections, amount elements.
     *@return size collections
     *
    */
    int getSizeSet();
    /**
     * The method checks the MyStack for elements .
     *
     * @return true if the stack is empty
    */
    boolean isEmpty();

    /**
     * The method checks the MyStack for elements .
     *
     * *@param e - value element collections
     * @return true if the stack is empty
     * @throws NotElementsInCollectionsSet - if, when you try to access an item in an empty collection
     */
    boolean remove(E e) throws NotElementsInCollectionsSet;
}