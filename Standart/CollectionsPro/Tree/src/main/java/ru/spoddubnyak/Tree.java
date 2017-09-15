package ru.spoddubnyak;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class Tree describes the actions of the container on the basis of Tree.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 04.09.2017
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * property root in Parent for Tree.
     */
    private Node root;
    /**
     * property number elements in Tree.
     */
    private int size = 0;
    /**
     * property for getting all the elements in ArrayList.
     */
    private List<E> listTree = new ArrayList<>();

    /**
     * Constructor for class Tree.
     *
     * @param value value for root elements
     */
    Tree(E value) {
        this.root = new Node(value);
        this.size = 1;
    }

    /**
     * Constructor for class Tree.
     */
    Tree() {
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (0 == this.size) {
            initialRoot(child);
            result = true;
        } else {
            Node<E> nodeParent = findElementsInTree(root, parent);
            Node<E> nodeChild = findElementsInTree(root, child);
            if (nodeParent != null && nodeChild == null) {
                nodeParent.children.add(new Node<E>(child));
                result = true;
                this.size++;
            }
        }
        return result;
    }

    /**
     * Method create root element.
     *
     * @param value value for element
     * @return result create root element, true if ok, false if not ok
     */
    public boolean initialRoot(E value) {
        boolean result = false;
        if (this.size == 0) {
            this.root = new Node(value);
            this.size++;
            result = true;
        }
        return result;
    }

    /**
     * Method return number elements in collection Tree.
     *
     * @return return number elements in collection Tree
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Method adds tree elements in List.
     *
     * @param listTree  List for add
     * @param startTree element of the tree from which the addition of elements begins
     */
    private void getAllToList(List<E> listTree, Node<E> startTree) {
        listTree.add(startTree.value);
        for (Node<E> node : startTree.children) {
            getAllToList(listTree, node);
        }
    }

    @Override
    public Iterator<E> iterator() {
        getAllToList(this.listTree, this.root);
        return new MyTreeIterator();
    }

    /**
     * Method return Node<E> by value.
     *
     * @param startFindNode element of the tree from which the search of elements begins
     * @param value         value by search for Tree
     * @return if search successfully returns the Node<E> containing the value, if not - null
     */
    private Node<E> findElementsInTree(Node<E> startFindNode, E value) {
        Node<E> node = null;
        if (startFindNode.value.equals(value)) {
            node = startFindNode;
        } else {
            List<Node<E>> children = startFindNode.children;
            for (Node<E> child : children) {
                node = findElementsInTree(child, value);
                if (node != null) {
                    break;
                }
            }
        }
        return node;
    }

    /**
     * Inner Class Node  .
     *
     * @param <E> This describes my type parameter
     */
    private class Node<E> {
        /**
         * property for children.
         */
        private List<Node<E>> children;
        /**
         * property for value.
         */
        private E value;

        /**
         * Constructor for class Node.
         *
         * @param value value
         */
        Node(E value) {
            this.children = new ArrayList<Node<E>>();
            this.value = value;
        }
    }

    /**
     * Inner Class MyTreeIterator implement the iterator .
     *
     * @param <E> This describes my type parameter
     */
    private class MyTreeIterator<E> implements Iterator<E> {
        /**
         * property index in Iterator.
         */
        private int positionIterator = 0;


        @Override
        public boolean hasNext() {
            return this.positionIterator < listTree.size();
        }

        @Override
        public E next() {
            E result;
            if (this.hasNext()) {
                result = (E) listTree.get(this.positionIterator++);
            } else {
                throw new IndexOutOfBoundsException("Went beyond the collection.");
            }
            return result;
        }
    }
}
