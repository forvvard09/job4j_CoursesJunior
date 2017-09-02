package ru.spoddubnyak.guide;

import ru.spoddubnyak.guide.errors.NotFindsElementsInCollections;
import ru.spoddubnyak.guide.model.GuideMap;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class Guide implementing your own data structure is a directory based on Map.
 *
 * @param <T> This describes my type parameter for key
 * @param <V> This describes my type parameter for value
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 30.08.2017
 */
public class Guide<T, V> implements GuideMap<T, V> {
    /**
     * property default size for array Node.
     */
    private static final int DEFAULT_SIZE = 16;
    /**
     * property array Nodes.
     */
    private Node[] hashTable;
    /**
     * property number Entry in Guide.
     */
    private int size;
    /**
     * property length array Node.
     */
    private int sizeHashTable = 0;
    /**
     * property change size array Node.
     */
    private boolean increas = true;

    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     */
    public Guide() {
        this.hashTable = new Node[DEFAULT_SIZE];
        this.sizeHashTable = DEFAULT_SIZE;
    }

    /**
     * Constructor it creates a new object SimpleHashSetChains with the specified.
     *
     * @param lengthHashTable preset size for array Node
     */
    public Guide(int lengthHashTable) {
        this.hashTable = new Node[lengthHashTable];
        this.sizeHashTable = lengthHashTable;
    }

    /**
     * Getter for property extentSet.
     *
     * @return number Entry in Guide
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Getter for property extentSet.
     *
     * @return number length array Node
     */
    public int getSizeHashTable() {
        return this.sizeHashTable;
    }

    /**
     * Method check dublicates in Guide by key.
     *
     * @param key key Entry
     * @return if such a key exists in the collection, return -1 if there is no return index for array Node
     */
    private int validDublicate(T key) {
        int result = -1;
        int findIndex = hash(key, sizeHashTable);
        if (hashTable[findIndex] == null) {
            result = findIndex;
        }
        return result;
    }

    @Override
    public boolean insert(T key, V value) {
        if (getSize() == hashTable.length && increas) {
            size = 0;
            increaseHashTable(hashTable);
        }
        boolean result = false;
        int index = validDublicate(key);
        if (!result && -1 != index) {
            hashTable[index] = new Node(key, value);
            size++;
            result = true;
        }
        return result;
    }

    /**
     * Method increase array Node, when all elements whole arrays is full .
     *
     * @param hashTable array Node for increase
     */
    private void increaseHashTable(Node[] hashTable) {
        Node[] copyHashTable = Arrays.copyOf(hashTable, hashTable.length);
        sizeHashTable = hashTable.length * 2;
        Node[] newHashtable = new Node[sizeHashTable];
        this.hashTable = newHashtable;
        increas = false;
        newHashtable = null;
        for (Node node : copyHashTable) {
            if (node != null) {
                insert((T) node.key, (V) node.value);
            }
        }
        increas = true;
    }

    @Override
    public V get(T key) {
        V result = null;
        int index = hash(key, hashTable.length);
        if (hashTable[index] != null) {
            result = (V) hashTable[index].value;
        }
        if (result == null) {
            throw new NotFindsElementsInCollections("Key not faund in Guide.");
        }
        return result;
    }

    @Override
    public boolean delete(T key) {
        boolean result = false;
        int index = hash(key, hashTable.length);
        if (hashTable[index] != null) {
            result = true;
            hashTable[index] = null;
            size--;
        }
        return result;
    }

    /**
     * Method calculate index by array Node based hashCode for key.
     *
     * @param key             key Entry
     * @param lengthHashTable length array Node
     * @return index by array Node based hashCode for key
     */
    private int hash(T key, int lengthHashTable) {
        return key.hashCode() % lengthHashTable;
    }

    @Override
    public Iterator iterator() {
        return new MyMapIterator();
    }

    /**
     * Inner Class MyArrayListIterator implement the iterator .
     *
     * @param <T> This describes my type parameter for key
     * @param <V> This describes my type parameter for value
     */
    private class Node<T, V> {
        /**
         * property for key.
         */
        private T key;
        /**
         * property for value.
         */
        private V value;

        /**
         * Constructor it creates a new object Node with the specified.
         */
        Node() {
            this.key = null;
            this.value = null;
        }

        /**
         * Constructor it creates a new object Node with the specified.
         *
         * @param key   property for key
         * @param value property for value
         */
        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s:%s, %s:%s", "key", this.key, "value", this.value);
        }
    }

    /**
     * Inner Class MyArrayListIterator implement the iterator .
     */
    private class MyMapIterator implements Iterator {
        /**
         * property index in Iterator.
         */
        private int index = 0;

        /**
         * number Entries.
         */
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < getSize();
        }

        @Override
        public Object next() {
            if (0 >= getSize()) {
                throw new NotFindsElementsInCollections("Not find elements in Guide.");
            }
            Object result;
            while (hashTable[index] == null) {
                index++;
            }
            result = hashTable[index++];
            count++;
            return result;
        }
    }
}