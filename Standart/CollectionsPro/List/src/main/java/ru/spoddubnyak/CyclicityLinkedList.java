package ru.spoddubnyak;

/**
 * Class contains a doubly connected list and determines whether it is cyclic.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.05.2017
 */
public class CyclicityLinkedList<T> {
    /**
     * property amount Node.
     */
    private int amountNode = 0;

    /**
     * Getter link first Node.
     * @return link first Node
     */
    public Node<T> getFirst() {
        return this.first;
    }

    /**
     * property link first Node.
     */
    private Node<T> first;

    /**
     * Method add new element in container.
     * @param cycle - if the truth last element refers to the first, if the lie, then the last element does not refer anywhere
     * @return link first Element
     */
    public Node<T> createsNode(boolean cycle) {
        this.first = new Node(++this.amountNode);
        Node<T> two = new Node(++this.amountNode);
        Node<T> third = new Node(++this.amountNode);
        Node<T> four = new Node(++this.amountNode);

        first.next = two;
        two.next = third;
        third.next = four;
        if (cycle) {
            four.next = this.first;
        } else {
            four.next = null;
        }
        return this.first;
    }

    /**
     * Getter for property value Ndoe.
     * @param linkNode link for Node
     * @return value Node
     */
    public T getValue(Node<T> linkNode) {
        return linkNode.value;
    }

    /**
     * Method The list determines whether the list is cyclic.
     * @param first - link of first Node
     * @return result, true - link cyclic, false - link not cyclic
     */
    public boolean hasCycle(Node<T> first) {
        Node<T> tempLink = first;
        boolean result = false;
        int i = 0;
        while (!result && i < this.amountNode) {
            tempLink = tempLink.next;
            if (tempLink == first) {
                result = true;
            }
            i++;
        }
        return result;
    }

    /**
     * Inner private Class to create a linked list of elements.
     *
     * @param <T> This describes my type parameter
     */
    private class Node<T> {
        /**
         * property value for Node.
         */
         private T value;
        /**
         * property for a pointer to the next Node.
         */
        private Node<T> next;
        /**
         * Constructor it creates a new object Element with the specified.
         *
         * @param value value for Element
         */
        Node(T value) {
            this.value = value;
        }
    }
}
