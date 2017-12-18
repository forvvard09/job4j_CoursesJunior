package ru.spoddubnyak;

import ru.spoddubnyak.errors.NotElementsInCollections;

/**
 * Class SimpleStack describes the actions of an array-based container MySimpleLinkedList.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.07.2017
 */

public class SimpleStack<E> extends SimpleLinkedList<E> implements IStak<E> {

    @Override
    public void push(E value) {
        add(value);
    }

    @Override
    public void pop() throws NotElementsInCollections {
        getErrorNotElement();
        removeLastElement();
    }

    @Override
    public E peek() throws NotElementsInCollections {
        getErrorNotElement();
        Element<E> lastElement =  getFirstElement().getPreviusElement();
        return  lastElement.getPreviusElement().getValueElement();
    }

    @Override
    public int search(Object value) {
        int result = 0;
        boolean validSearch = false;
        Element<E> lastElement = getFirstElement().getPreviusElement();
        while (!validSearch && lastElement.getPreviusElement() != getFirstElement()) {
            lastElement = lastElement.getPreviusElement();
            result++;
            if (value.equals(lastElement.getValueElement())) {
                validSearch = true;
            }
        }
        return validSearch ? result : -1;
    }

    @Override
    public boolean empty() {
        return getSize() == 0 ? true : false;
    }

    /**
     * Method checks if there are items in the collection SimpleStack<E>, if not then an error NotElementsInCollections.
     *
     * @throws NotElementsInCollections - if, when you try to access an item in an empty collection
     */
    protected void getErrorNotElement() throws NotElementsInCollections {
        if (0 == getSize()) {
            throw new NotElementsInCollections("The stack is empty, there is not one element.");
        }
    }
}