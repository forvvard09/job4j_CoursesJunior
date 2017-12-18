package ru.spoddubnyak.hashSet.model;

/**
 * Class NodeHashSet implementation of an element for HashSet.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.08.2017
 */
public class NodeHashSet<E> {
    /**
     * property new Object.
     */
    private E value;
    /**
     * property link for next element.
     */
    private NodeHashSet<E> next;

    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     * @param  value - new object
     * @param next link to next element
     */
    public NodeHashSet(E value, NodeHashSet<E> next) {
        this.value = value;
        this.next = next;
    }
    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     * @param  value - new object
     */
    public NodeHashSet(E value) {
        this.value = value;
    }
    /**
     * Getter for property value.
     *
     * @return property value
     */
    public E getValue() {
        return this.value;
    }

    /**
     * Setter for property value.
     *
     * @param  value new property value
     */
    public void setValue(E value) {
        this.value = value;
    }
    /**
     * Getter for property next.
     *
     * @return property next
     */
    public NodeHashSet<E> getNext() {
        return this.next;
    }

    /**
     * Setter for property next.
     *
     * @param next  property next
     */
    public void setNext(NodeHashSet<E> next) {
        this.next = next;
    }
}

