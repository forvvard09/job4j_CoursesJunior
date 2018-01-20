package ru.spoddubnyak.guide.model;

import ru.spoddubnyak.guide.errors.NotFindsElementsInCollections;

/**
 * Interface GuideMap, methods for the Guide.
 *
 * @param <T> This describes my type parameter for key
 * @param <V> This describes my type parameter for value
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 30.07.2017
 */
public interface GuideMap<T, V> extends Iterable<T> {
    /**
     * Method insert new entry.
     *
     * @param key   - key for entry
     * @param value - value for entry
     * @return true, if you can insert a new entry, false - if not can
     */
    boolean insert(T key, V value);

    /**
     * Method return value entry by key.
     *
     * @param key - key for entry
     * @return value entry by key
     * @throws NotFindsElementsInCollections - error if not find key in Guide
     */
    V get(T key) throws NotFindsElementsInCollections;

    /**
     * Method delete entry by key.
     *
     * @param key - key for entry
     * @return true, if you can delete a new entry, false - if not can
     */
    boolean delete(T key);
}
