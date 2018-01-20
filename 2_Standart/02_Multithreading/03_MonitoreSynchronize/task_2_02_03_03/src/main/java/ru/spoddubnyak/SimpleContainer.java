package ru.spoddubnyak;


/**
 * Interface SimpleContainer describes the actions of the container.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.05.2017
 */

public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Method add new element in container.
     *
     * @param value - new element
     */
    void add(E value);

    /**
     * Method return container element by index.
     *
     * @param index - index in container
     * @return - container element by index
     */
    E get(int index);
}
