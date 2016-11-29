package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Item;

import java.util.Random;

public class Tracker {

    private static final Random RN = new Random();
    private Item[] items = new Item[10];
    private int position = 0;

    public Item add(Item item) {
        item.setId(this.geneateId());
        this.items[position++] = item;
        return item;
    }

    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    String geneateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

	public void edit (Item fresh) {
		for (int index=0; index!=items.length; ++index) {
			Item item = items[index];
			if (item != null && item.getId().equals(fresh.getId())) {
				items[index] = fresh; 
				break;
			}
		}
	}
	
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

}
