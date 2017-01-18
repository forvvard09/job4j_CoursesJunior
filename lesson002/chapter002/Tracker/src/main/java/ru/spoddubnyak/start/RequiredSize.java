package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

/**
 * Class change of the size of the Item array of records.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 20.01.2017
 */

public class RequiredSize {
    /**
     * Method it reduces the size of the storage array item is a factor of 2.
     *
     * @param items - an array of Item
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

    /**
     * Method increases the size of the comments array is a factor of 2.
     *
     * @param comments - an array of Comment
     * @return - an array of records with the new size of the array
     */
    public Comment[] increaseSize(Comment[] comments) {
        Comment[] requiredLengthComments = new Comment[comments.length * 2];
        System.arraycopy(comments, 0, requiredLengthComments, 0, comments.length);
        return requiredLengthComments;
    }
}
