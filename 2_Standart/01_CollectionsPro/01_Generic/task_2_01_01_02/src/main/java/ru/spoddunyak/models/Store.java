package ru.spoddunyak.models;

import ru.spoddubnyak.errors.NoFindValueException;

/**
 * Interface Store for actions item Storage.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.05.2017
 */
public interface Store<T extends Base> {
    /**
     * Method add new element in array.
     *
     * @param value new element
     */
    void add(T value);

    /**
     * Method update element in array.
     *
     * @param lastValue replacement element
     * @param nextValue element to be replaced
     * @throws NoFindValueException if lastElement not find in array
     */
    void update(T lastValue, T nextValue) throws NoFindValueException;
    /**
     * Method remove element in array.
     *
     * @param value value element for remove
     * @throws NoFindValueException if lastElement not find in array
     */
    void delete(T value) throws NoFindValueException;

    /**
     * Method object T by number element in array.
     *
     * @param position position in array
     * @return object in array
     * @throws NoFindValueException if object not find in array
     */
    T get(int position) throws NoFindValueException;
}
