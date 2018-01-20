package ru.spoddubnyak.start;

import org.junit.Test;
import ru.spoddubnyak.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Tracker.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 26.12.2016
 */
public class TrackerTest {
    /**  first element array Item of Tracker.  */
    private final int firstElement = 0;

    /**
     * Test method add class Tracker Add two new items in storage Tracker.
     */
    @Test
    public void whenAddTwoNewItemsThenGetSameResultInTracker() {
        Tracker tracker = new Tracker();
        Item[] items = new Item[2];
        final Long timeOne = 10L;
        final Long timeTwo = 20L;
        items[0] = new Item("name0", "desk0", timeOne);
        items[1] = new Item("name1", "desk1", timeTwo);
        tracker.add(items[0]);
        tracker.add(items[1]);
        assertThat(items, is(tracker.findAll()));
    }

    /**
     * Test method add class Tracker Add one new items in storage Tracker.
     */
    @Test
    public void whenAddOneNewItemThenGetSameResultInTracker() {
        Tracker tracker = new Tracker();
        final Long timeOne = 10L;
        Item item = new Item("name0", "desk0", timeOne);
        tracker.add(item);
        assertThat(item, is(tracker.findAll()[firstElement]));
    }

    /**
     * Test method update class Tracker Update item of itemUpdate storage Tracker.
     */
    @Test
    public void whenUpdateItemThenGetSameResultInTracker() {
        Tracker tracker = new Tracker();
        final Long timeOne = 10L;
        final Long timeTwo = 20L;
        final Long createUpdate = 99L;
        Item itemOne = new Item("name0", "desk0", timeOne);
        Item itemTwo = new Item("name1", "desk1", timeTwo);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        Item itemUpdate = new Item("nameUpdate", "descreptionUpdate", createUpdate);
        itemUpdate.setId(itemOne.getId());
        tracker.update(itemUpdate);
        assertThat(itemUpdate, is(tracker.findAll()[firstElement]));
    }

    /**
     * Test method delete class Tracker delete item of itemDelete storage Tracker.
     */
    @Test
    public void whenDeleteItemThenGetSameResultInTracker() {
        Tracker tracker = new Tracker();
        final Long timeOne = 10L;
        final Long timeTwo = 20L;
        Item itemOne = new Item("name0", "desk0", timeOne);
        Item itemTwo = new Item("name1", "desk1", timeTwo);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.delete(itemOne);
        assertThat(itemTwo, is(tracker.findAll()[firstElement]));
    }
    /**
     * Test method findByID class Tracker find Item by id in storage Tracker.
     */
    @Test
    public void whenFindByIdThenGetItembyId() {
        Tracker tracker = new Tracker();
        final Long timeOne = 10L;
        final Long timeTwo = 20L;
        Item itemOne = new Item("name0", "desk0", timeOne);
        Item itemTwo = new Item("name1", "desk1", timeTwo);
        tracker.add(itemOne);
        int idItemOne = itemOne.getId();
        tracker.add(itemTwo);
        assertThat(itemOne, is(tracker.findById(idItemOne)));
    }

    /**
     * Test method findByName class Tracker find items by Name item in storage Tracker.
     */
    @Test
    public void whenFindByNameThenGetItembyName() {
        Tracker tracker = new Tracker();
        Item[] testItems = new Item[2];
        final Long timeOne = 11L;
        final Long timeTwo = 22L;
        final Long timeThree = 33L;
        testItems[0] = new Item("name01", "descr01", timeOne);
        testItems[1] = new Item("natt02", "descr02", timeTwo);
        Item itemThree = new Item("test", "test", timeThree);
        tracker.add(testItems[0]);
        tracker.add(testItems[1]);
        tracker.add(itemThree);
        assertThat(testItems, is(tracker.findByName("na")));
    }
}