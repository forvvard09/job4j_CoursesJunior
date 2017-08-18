package ru.spoddubnyak.hashSet;

import ru.spoddubnyak.hashSet.model.NodeHashSet;
import ru.spoddubnyak.hashSet.model.SimpleHashTable;

import java.util.Arrays;

/**
 * Class SimpleHashSet describes the actions of an Hashh table open method.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.08.2017
 */
public class SimpleHashSetOpenMethod<E> extends SimpleHashTable<E> {
    /**
     * number elements in HashTable.
     */
    private int size = 0;
    /**
     * array Nodes.
     */
    private NodeHashSet<E>[] hashTable;

    /**
     * Constructor it creates a new object SimpleHashSetOpenMethod with the specified.
     *
     * @param extentHashTable - number elements in HashTable
     */
    public SimpleHashSetOpenMethod(int extentHashTable) {
        extentHashTable = extentHashTable <= 0 ? 1 : extentHashTable;
        NodeHashSet<E>[] temporaryHashTable = new NodeHashSet[extentHashTable];
        this.hashTable = temporaryHashTable;
    }

    /**
     * Constructor it creates a new object SimpleHashSetOpenMethod with the specified.
     */
    public SimpleHashSetOpenMethod() {
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
        boolean validNull = false;
        int index = hash(value);
        if (this.hashTable[index] != null) {
            while (!valid && !validNull) {
                valid = this.hashTable[index++].getValue().equals(value);
                if (index > this.hashTable.length - 1) {
                    index = 0;
                }
                validNull = this.hashTable[index] == null;
            }

        }
        return valid ? -1 : index;
    }

    @Override
    public boolean existenceValid(final E value) {
        return find(value) == -1;
    }

    @Override
    public void add(E e) {
        if (this.size == this.hashTable.length) {
            increaseHashTable();
        }
        int indexHash = find(e);
        if (indexHash != -1) {
            int index = indexHash;
            NodeHashSet<E> node = new NodeHashSet<E>(e);
            this.hashTable[index] = node;
            this.size++;
        }
    }

    @Override
    public void increaseHashTable() {
        NodeHashSet[] copyHashTable = Arrays.copyOf(this.hashTable, this.hashTable.length);
        this.size = 0;
        final double ratio = 1.5;
        final int addConstRatio = 5;
        setExtentSet((int) (getExtentSet() * ratio) + addConstRatio);
        NodeHashSet<E>[] newHashTable = new NodeHashSet[getExtentSet()];
        this.hashTable = newHashTable;
        for (NodeHashSet<E> node : copyHashTable) {
            if (node == null) {
                continue;
            } else {
                NodeHashSet<E> temporaryNode = node;
                add(temporaryNode.getValue());
            }
        }
    }
}