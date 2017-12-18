package ru.spoddubnyak.binareeTree;

/**
 * Interface SimpleBinaryTree describes the actions of the container based o binary Tree.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.09.2017
 */
public interface SimpleBinaryTree<E extends Comparable> extends Iterable {

    /**
     * Method add element child in Tree.
     *
     * @param child child.
     * @return result adding, true - if element add, false - if not element add
     */
    boolean add(E child);

    /**
     * Method return number elements in BinaryTree.
     *
     * @return result adding, true - if element add, false - if not element add
     */
    int getSize();

    /**
     * Method returns true if this set contains the specified element.
     *
     * @param value value for search
     * @return result adding, true - if element add, false - if not element add
     */
    boolean contains(E value);

    /**
     * Method check colection binaryTree on emptiness.
     *
     * @return false if colection - not emptiness, true - emptiness
     */
    boolean empty();
}
