package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Item;

import java.util.Random;

public class Tracker {

    private Item[] items = new Item[10];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add (Item item) {
        item.setId(this.geneateId());
        this.items[position++] = item;
        return item;
    }

    protected Item findById(String id) {
        Item result = null;
        for(Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
       }
        return result;
    }

    String geneateId() {
        return String.valueOf(RN.nextInt());
    }

}
