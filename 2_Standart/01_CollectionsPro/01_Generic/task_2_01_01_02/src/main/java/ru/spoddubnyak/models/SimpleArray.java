package ru.spoddubnyak.models;

import ru.spoddubnyak.errors.NoFindValueException;

/**
 * Class SimpleArray. Generic. Container implementation.
 *
 * @param <T> This describes my type parameter
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.05.2017
 */
public class SimpleArray<T> {

    /**
     * property - initial size of the array.
    */
    private static final int START_SIZE = 4;
    /**
     * property - array of objects.
    */
    private Object[] objects;

    /**
     * property - index in array.
    */
    private int index = 0;

    /**
     * Constructor of new object SimpleArray<T>.
     *
     */
    public SimpleArray() {
        this.objects = new Object[START_SIZE];
    }

    /**
     * Method add new element in array.
     *
     * @param value new element
     */
    public void add(T value) {
        if (index >= this.objects.length) {
            increaseArray();
        }
        objects[index++] = value;
    }

    /**
     * Method return an array element by position.
     *
     * @param position - position element
     * @return element array
     */
    public T get(int position) {
        return (T) this.objects[position];
    }

    /**
     * Method update element in array.
     *
     * @param lastValue replacement element
     * @param nextValue element to be replaced
     *
     * @throws NoFindValueException if lastElement not find in array
     */
    public void update(T lastValue, T nextValue) throws NoFindValueException {
        int position = getIndexByValue(lastValue);
        this.objects[position] = nextValue;
    }

    /**
     * Method update element in array.
     *
     * @param deleteValue element to remove
     *
     * @throws NoFindValueException if deleteValue not find in array
     */
    public void delete(T deleteValue) throws NoFindValueException {
        int positionDelete = getIndexByValue(deleteValue);
        Object[] copyArr = new Object[this.objects.length - 1];
        if (positionDelete > 0 && positionDelete < this.objects.length - 1) {
            System.arraycopy(this.objects, 0, copyArr, 0, positionDelete);
            System.arraycopy(this.objects, positionDelete + 1, copyArr, positionDelete, this.objects.length - positionDelete - 1);
            this.objects = copyArr;
            copyArr = null;
        } else if (positionDelete == 0) {
            System.arraycopy(this.objects, 1, copyArr, 0, this.objects.length - 1);
            this.objects = copyArr;
            copyArr = null;
        } else if (positionDelete == this.objects.length - 1) {
            System.arraycopy(this.objects, 0, copyArr, 0, this.objects.length - 1);
            this.objects = copyArr;
            copyArr = null;
        }
    }

    /**
     * Method position of an element in an array.
     *
     * @param findValue element to find
     * @return position findValue in array Object
     *
     * @throws NoFindValueException if findValue not find in array
     */
    private int getIndexByValue(T findValue) throws NoFindValueException {
        int findPosition = -1;
        for (int i = 0; this.objects[i] != null; i++) {
            if (this.objects[i].equals(findValue)) {
                findPosition = i;
                break;
            }
        }
        if (findPosition == -1) {
            throw new NoFindValueException(String.format("%s: %s %s.", "Error", findValue, "not find in array."));
        }
        return findPosition;
    }

    /**
     * Method increases the size of the array if there is not enough space when adding a new element.
     *
     */
    private void increaseArray() {
        int newSizeArray = this.objects.length * 2;
        Object[] tempObject = new Object[newSizeArray];
        System.arraycopy(this.objects, 0, tempObject, 0, this.objects.length);
        this.objects = tempObject;
        tempObject = null;
    }

    /**
     * Method return length array.
     *
     * @return length array
     */
    public int getLengthArr() {
        return this.objects.length;
    }
}
