package ru.spoddubnyak;

/**
 * Interface SimpleTree describes the actions of the container.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.05.2017
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Method add element child in parent.
     * Parent  can have a list child.
     *
     * @param parent parent.
     * @param child  child.
     * @return result adding, true - if element add, false - if not element add
     */
    boolean add(E parent, E child);
}