package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Item;

/**
 * Class change of the size of the Item array of records.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.12.2016
 */

public class RequiredSize {
    /**
     * Method it reduces the size of the storage array item is a factor of 2.
     *
     * @param items          - an array of Item
     * @return Item[] value
     */
     Item[] removeEmpti(Item[] items) {
        int requiredSize = items.length / 2;
        Item[] realLengtItems = new Item[requiredSize];
        System.arraycopy(items, 0, realLengtItems, 0, requiredSize);
        return realLengtItems;
    }

    /**
     * Method increases the size of the storage array item is a factor of 2.
     *
     * @param items - an array of Item
     * @return - an array of records with the new size of the array
     */
    public Item[] increaseSize(Item[] items) {
        Item[] requiredLengthItems = new Item[items.length * 2];
        System.arraycopy(items, 0, requiredLengthItems, 0, items.length);
        return requiredLengthItems;
    }
}
