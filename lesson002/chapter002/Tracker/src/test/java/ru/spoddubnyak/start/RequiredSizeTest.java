package ru.spoddubnyak.start;


import org.junit.Test;
import ru.spoddubnyak.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class RequiredSize.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 26.12.2016
 */

public class RequiredSizeTest {
    /**
     * Test method add class Tracker Add two new items in storage Tracker.
     */
    @Test
    public void whenNotEnoughSpaceThenIncreasingTheSizeTwice() {
        final int  size = 2;
        Item[] items = new Item[size];
        for (int i = 0; i < 2; i++) {
            items[i] = new Item("name" + i, "desc" + i, 00L);
        }
        int expectedOutcome = 2 * items.length;
        RequiredSize requiredSize = new RequiredSize();
        items = requiredSize.increaseSize(items);
        int result = items.length;
        assertThat(expectedOutcome, is(result));
    }

    /**
     * Test method add class Tracker Add two new items in storage Tracker.
     */
    @Test
    public void whenMoreSpaceIncreasingTheReducingRizeTwice() {
        final int  size = 6;
        Item[] items = new Item[size];
        for (int i = 0; i < 2; i++) {
            items[i] = new Item("name" + i, "desc" + i, 00L);
        }
        int expectedOutcome = items.length / 2;
        RequiredSize requiredSize = new RequiredSize();
        items = requiredSize.removeEmpti(items);
        int result = items.length;
        assertThat(expectedOutcome, is(result));
    }
}