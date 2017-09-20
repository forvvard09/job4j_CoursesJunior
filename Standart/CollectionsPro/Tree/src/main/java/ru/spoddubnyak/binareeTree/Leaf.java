package ru.spoddubnyak.binareeTree;

/**
 * Class Leaf - one element and links for colection binaryTree.
 *
 * @param <E> This describes my type parameter
 */
public class Leaf<E> {
    /**
     * property child element less than or equal to the parent.
     */
    private Leaf<E> leftChild;

    /**
     * property child element more root .
     */
    private Leaf<E> rightChild;

    /**
     * property value by specification.
     */
    private E value;

    /**
     * Constructor for object Leaf.
     *
     * @param value value for property value
     */
    public Leaf(E value) {
        this.value = value;
    }

    /**
     * Getter for rightChild.
     *
     * @return right - link to child
     */
    public Leaf<E> getRightChild() {
        return this.rightChild;
    }

    /**
     * Setter for property rightChild.
     *
     * @param newLeaf value
     */
    public void setRightChild(Leaf<E> newLeaf) {
        this.rightChild = newLeaf;
    }

    /**
     * Getter for leftChild.
     *
     * @return leftChild - link to child
     */
    public Leaf<E> getLeftChild() {
        return this.leftChild;
    }

    /**
     * Setter for property leftChild.
     *
     * @param newLeaf value
     */
    public void setLeftChild(Leaf<E> newLeaf) {
        this.leftChild = newLeaf;
    }

    /**
     * Getter for vulue.
     *
     * @return value - value for Leaf
     */
    public E getValue() {
        return this.value;
    }
}
