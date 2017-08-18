package ru.spoddubnyak.hashSet.model;

/**
 * Abstarct classClass SimpleHashTable for implementation SimpleHashset.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.08.2017
 */
public abstract class SimpleHashTable<E> {
    /**
     * property for a size of HashTable.
     */
    private int extentSet = 0;
    /**
     * property default size HashTable.
     */
    protected static final int DEFOULT_EXTENT = 16;
    /**
     * Getter for property extentSet.
     *
     * @return property extentSet
     */
    public int getExtentSet() {
        return this.extentSet;
    }
    /**
     * Setter for property extentSet.
     *
     * @param extentSet for property this.extentSet
     */
    public void setExtentSet(int extentSet) {
        this.extentSet = extentSet;
    }
    /**
     * Method calculates HashTable index for an element.
     *
     * @param value object for add in the collection.
     * @return index in hashTable
     *
     */
    public int hash(final E value) {
        final int kofOne = 31;
        final int kofTwo = 17;
        int hash = kofOne;
        hash = hash * kofTwo + value.hashCode();
        return 0 == this.extentSet ? 0 : Math.abs(hash % this.extentSet);
    }

    /**
     * Getter for property extentSet.
     *
     * @return property size HashTable
     */
    public int getSizeHashTable() {
        return getExtentSet();
    }

    /**
     * Method add element in the collection.
     *
     * @param e object for add in the collection.
     */
    public abstract void add(E e);
    /**
     * Method check is there an object in the collection.
     *
     * @param value object for find in collection.
     *
     * @return true - if object in cilection, false - if object not colection
     */
    public abstract boolean existenceValid(final E value);
    /**
     * Method return number elements in collection.
     *
     * @return number elements in collection
     */
    public abstract int getSizeSet();
    /**
     * Method increases the size of the table when filling in the tables.
     *
     */
    public abstract void increaseHashTable();
    /**
     * Method search object in collection.
     *
     * @param value object for search
     * @return -1, if object is not collection, if object is collection return his hash
     */
    public abstract int find(final E value);
}
