package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

import java.util.Random;

/**
 * Class class storages of records in a tracker.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.12.2016
 */
public class Tracker {
    /**
     * property - identification number.
     */
    private static final Random RN = new Random();
    /**
     * property - range of numbers for the generation of the identification number.
     */
    private static final int RANGERANDOM = 9999;
    /**
     * property - initial size of a store.
     */
    private static final int INITIALIZE = 3;
    /**
     * property - max number for Create time.
     */
    private final long maskCreation = 999999999L;
    /**
     * items store.
     */
    private Item[] items = new Item[INITIALIZE];
    /**
     * element position for record in.
     */
    private int position = 0;

    /**
     * getter - rerutn max number for Create time Item.
     *
     * @return - max number
     */
    public long getMascCreate() {
        long mask = this.maskCreation;
        return mask;
    }

    /**
     * Method adds a new application to the tracker.
     *
     * @param item - new application to the tracker
     * @return - new Item
     */
    public Item add(Item item) {
        item.setId(this.generationId());
        if (this.position + 1 > this.items.length) {
            RequiredSize requiredSize = new RequiredSize();
            this.items = requiredSize.increaseSize(this.items);
        }
        this.items[position++] = item;
        return item;
    }

    /**
     * Method generates id - identification number.
     *
     * @return id  - generated identification number
     */
    int generationId() {
        return RN.nextInt(RANGERANDOM);
    }

    /**
     * Method searches for a given parameter in the id.
     *
     * @param id - string to search by the name of the array
     * @return Item  - entries, which corresponds @param id
     */
    public Item findById(int id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId() == id) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Method returns the position in the Tracker.
     *
     * @param findItem -  item of Tracker
     * @return - position in the Tracker
     */
    private int getPosition(Item findItem) {
        int indexItem = 0;
        for (Item item : items) {
            if (item != null && item.equals(findItem)) {
                break;
            }
            indexItem++;
        }
        return indexItem;
    }

    /**
     * Method update the record from the Tracker.
     *
     * @param itemNew -  new item
     */
    public void update(Item itemNew) {
        for (int index = 0; index != items.length; ++index) {
            Item item = this.items[index];
            if (item != null && item.getId() == itemNew.getId()) {
                if (this.items[index].getComments().length != 0) {
                    for (Comment comment : this.items[index].getComments()) {
                        itemNew.addComment(comment);
                    }
                }
                this.items[index] = itemNew;
                break;
            }
        }
    }

    /**
     * Method removes the record from the Tracker.
     *
     * @param itemDelete -  record for deletion
     */
    public void delete(Item itemDelete) {
        int index = 0;
        index = getPosition(itemDelete);
        this.items[index] = null;
        this.position--;
        System.arraycopy(this.items, index + 1, this.items, index, this.items.length - (index + 1));
        int quantityPos = (this.position == 0) ? 1 : this.position;
        if (((this.items.length) / quantityPos) > 2) {
            RequiredSize requiredSize = new RequiredSize();
            this.items = requiredSize.removeEmpti(this.items);
        }
    }

    /**
     * Method looking for all applications in the Tracker.
     *
     * @return Item[] value  - all entries Tracker
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (this.items != null) {
                result[index] = this.items[index];
            }
        }
        return result;
    }

    /**
     * Method searches for a given parameter in the name.
     *
     * @param key - string to search by the name of the array
     * @return Item[] value  - all entries, that meet the search filter
     */
    public Item[] findByName(String key) {
        int numberOccurrences = 0;
        for (Item item : items) {
            if (item != null && item.getName().contains(key)) {
                numberOccurrences++;
            }
        }
        Item[] result = new Item[numberOccurrences];
        int index = 0;
        for (Item item : items) {
            if (item != null && item.getName().contains(key)) {
                result[index] = item;
                index++;
            }
            if (index == numberOccurrences) {
                break;
            }
        }
        return result;
    }
}