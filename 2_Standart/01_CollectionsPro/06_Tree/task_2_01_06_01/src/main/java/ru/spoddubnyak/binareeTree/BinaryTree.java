package ru.spoddubnyak.binareeTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class BinaryTree describes the actions of the container based o binary Tree.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.09.2017
 */
public class BinaryTree<E extends Comparable> implements SimpleBinaryTree<E> {
    /**
     * property root in colection BinaryTree.
     */
    private Leaf<E> root = null;
    /**
     * property number elements in colection BinaryTree.
     */
    private int size = 0;

    /**
     * property for getting all the elements collections BinaryTree in ArrayList.
     */
    private List<E> allElements = new ArrayList<>();

    /**
     * Constructor for class BinaryTree.
     */
    public BinaryTree() {
    }

    /**
     * Constructor for class BinaryTree.
     *
     * @param value value for root elements
     */
    public BinaryTree(E value) {
        this.root = new Leaf<>(value);
        this.size++;
    }

    /**
     * Method create root element.
     *
     * @param value value for element
     */
    private void initialRoot(E value) {
        root = new Leaf<E>(value);
        this.size++;
    }

    /**
     * Method recursive search in collection binaryTree value.
     *
     * @param startSearch the element from which the search begins
     * @param searchValue value for search value
     * @return null, if value not search. Leaf<E> if value search
     */
    private Leaf searchValue(Leaf<E> startSearch, E searchValue) {
        Leaf<E> result = null;
        Leaf<E> startForSearch = startSearch;
        int compare = searchValue.compareTo(startForSearch.getValue());
        if (0 == compare) {
            result = startForSearch;
        } else if (compare < 0 && startForSearch.getLeftChild() != null) {
            result = searchValue(startForSearch.getLeftChild(), searchValue);
        } else if (compare > 0 && startForSearch.getRightChild() != null) {
            result = searchValue(startForSearch.getRightChild(), searchValue);
        }
        return result;
    }

    /**
     * Method recursive search element for add and add element.
     *
     * @param startSearch the element from which the search begins
     * @param searchValue value for add in BinaryTree
     */
    private void searchLeafForAdd(Leaf<E> startSearch, E searchValue) {
        Leaf<E> newLeaf = new Leaf<>(searchValue);
        Leaf<E> startForSearch = startSearch;
        int compare = searchValue.compareTo(startForSearch.getValue());
        if (compare < 0) {
            if (startForSearch.getLeftChild() == null) {
                startForSearch.setLeftChild(newLeaf);
            } else {
                searchLeafForAdd(startForSearch.getLeftChild(), searchValue);
            }
        }
        if (compare > 0) {
            if (startForSearch.getRightChild() == null) {
                startForSearch.setRightChild(newLeaf);
            } else {
                searchLeafForAdd(startForSearch.getRightChild(), searchValue);
            }
        }
    }

    /**
     * Method adds Binarytree elements in List.
     *
     * @param allElements  List for add
     * @param startGetLeaf element of the tree from which the addition of elements begins
     */
    public void toList(List<E> allElements, Leaf<E> startGetLeaf) {
        if (startGetLeaf != null) {
            allElements.add(startGetLeaf.getValue());
            if (startGetLeaf.getLeftChild() != null) {
                toList(this.allElements, startGetLeaf.getLeftChild());
            }
            if (startGetLeaf.getRightChild() != null) {
                toList(this.allElements, startGetLeaf.getRightChild());
            }
        }
    }

    @Override
    public boolean add(E child) {
        boolean result = false;
        if (this.root == null) {
            initialRoot(child);
            result = true;
        } else if (searchValue(this.root, child) == null) {
            searchLeafForAdd(this.root, child);
            this.size++;
            result = true;
        }
        return result;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean contains(E value) {
        return searchValue(this.root, value) == null ? false : true;
    }

    @Override
    public boolean empty() {
        return 0 == this.size;
    }

    @Override
    public Iterator<E> iterator() {
        toList(this.allElements, this.root);
        return new MyIterator();
    }

    /**
     * Inner Class MyTreeIterator implement the iterator .
     */
    class MyIterator implements Iterator<E> {
        /**
         * property index in Iterator.
         */
        private int position = 0;

        @Override
        public boolean hasNext() {
            return this.position < allElements.size();
        }

        @Override
        public E next() {
            E result;
            if (this.hasNext()) {
                result = allElements.get(this.position++);
            } else {
                throw new NoSuchElementException("Went beyond the collection.");
            }
            return result;
        }
    }
}
