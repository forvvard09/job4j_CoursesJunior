package ru.spoddunyak.models;

import ru.spoddubnyak.errors.NoFindValueException;

/**
 * Abstract class BaseStore execute methods Storage.
 *
 * @param <T> This describes my type parameter
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.05.2017
 */
public abstract class BaseStore<T extends Base> implements Store<T> {

    /**
     * property - actions storage.
     * @param <T> This describes my type parameter
     */
    private SimpleArray<T> simpleArray;

    /**
     * Constructor of new object BaseStore.
     *
     */
    public BaseStore() {
        this.simpleArray = new SimpleArray<T>();
    }

    @Override
    public void add(T value) {
        this.simpleArray.add(value);
    }

    @Override
    public void update(T lastValue, T nextValue) throws NoFindValueException {
        this.simpleArray.update(lastValue, nextValue);
    }

    @Override
    public void delete(T deleteValue) throws NoFindValueException {
        this.simpleArray.delete(deleteValue);
    }

    @Override
    public T get(int position) throws NoFindValueException {
        return this.simpleArray.get(position);
    }
}
