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
        int data = 1;
        this.first = new Node(data++);
        Node<T> two = new Node(data++);
        Node<T> third = new Node(data++);
        Node<T> four = new Node(data);

        first.next = two;
        two.next = third;
        third.next = four;
        if (cycle) {
            four.next = four;
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
        boolean result = false;
        boolean valid = false;
        Node<T> linkOneStep = first;
        Node<T> linkSecondStep = first;
        while (!valid && !(linkSecondStep == null)) {
            linkOneStep = linkOneStep.next;
            linkSecondStep = linkSecondStep.next.next;
            if (linkOneStep == linkSecondStep) {
                valid = true;
                result = true;
            }
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
