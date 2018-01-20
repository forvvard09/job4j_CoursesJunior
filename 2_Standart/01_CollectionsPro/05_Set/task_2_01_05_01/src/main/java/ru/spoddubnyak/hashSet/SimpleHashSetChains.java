package ru.spoddubnyak.hashSet;

import ru.spoddubnyak.hashSet.model.NodeHashSet;
import ru.spoddubnyak.hashSet.model.SimpleHashTable;

import java.util.Arrays;

/**
 * Class SimpleHashSet describes the actions of an Hash table chain method.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.08.2017
 */
public class SimpleHashSetChains<E> extends SimpleHashTable<E> {
    /**
     * number elements in HashTable.
     */
    private int size = 0;
    /**
     * array Nodes.
     */
    private NodeHashSet<E>[] hashTable;
    /**
     * property for a counter for links in own element to array.
     */
    private int levelNesting = 0;
    /**
     * property as many as possible in one element of the array.
     */
    private static final int MAX_LEVEL_NESTING = 4;
    /**
     * property flag need to check for duplicates in the HashTable.
     */
    private boolean checkDublicates = true;

    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     * @param  extentHashTable - number elements in HashTable
     */
    public SimpleHashSetChains(int extentHashTable) {
        extentHashTable = extentHashTable <= 0 ? 1 : extentHashTable;
        NodeHashSet<E>[] temporaryHashTable = new NodeHashSet[extentHashTable];
        this.hashTable = temporaryHashTable;
    }

    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     *
     */
    public SimpleHashSetChains() {
        setExtentSet(DEFOULT_EXTENT);
        NodeHashSet<E>[] temporaryHashTable = new NodeHashSet[getExtentSet()];
        this.hashTable = temporaryHashTable;
    }

    /**
     * Getter for property extentSet.
     *
     * @return property number elements in the collection
     */
    public int getSizeSet() {
        return this.size;
    }

    @Override
    public int find(final E value) {
        boolean valid = false;
        int index = hash(value);
        NodeHashSet<E> node = hashTable[index];
        while (!valid && node != null) {
            valid = node.getValue().equals(value);
            node = node.getNext();
        }
        return !valid ? index : -1;
    }

    @Override
    public boolean existenceValid(final E value) {
        return find(value) == -1;
    }

    @Override
    public void add(E e) {
        int indexHash = 0;
        if (this.checkDublicates) {
            indexHash = find(e);
        } else {
            indexHash = hash(e);
        }
        if (indexHash != -1) {
            int index = indexHash;
            NodeHashSet<E> node = new NodeHashSet<E>(e, null);
            if (this.hashTable[index] != null) {
                NodeHashSet<E> temporaryNode = this.hashTable[index];
                while (temporaryNode.getNext() != null) {
                    this.levelNesting++;
                    temporaryNode = temporaryNode.getNext();
                }
                temporaryNode.setNext(node);
            } else {
                this.hashTable[index] = node;
            }
            this.size++;
            if (this.levelNesting >= this.MAX_LEVEL_NESTING) {
                increaseHashTable();
            }
            this.levelNesting = 0;
        }
    }

    @Override
    public void increaseHashTable() {
        NodeHashSet[] copyHashTable = Arrays.copyOf(this.hashTable, this.hashTable.length);
        this.checkDublicates = false;
        this.levelNesting = 0;
        this.size = 0;
        final double ratio = 1.5;

        setExtentSet((int) (getExtentSet() * ratio) + this.MAX_LEVEL_NESTING);
        NodeHashSet<E>[] newHashTable = new NodeHashSet[getExtentSet()];
        this.hashTable = newHashTable;
        for (NodeHashSet<E> node : copyHashTable) {
            if (node == null) {
                continue;
            }
            if (node.getNext() != null) {
                NodeHashSet<E> temporaryNode = node;
                while (temporaryNode != null) {
                    add(temporaryNode.getValue());
                    temporaryNode = temporaryNode.getNext();
                }
            } else {
                add(node.getValue());
            }
        }
        this.checkDublicates = true;
    }
}