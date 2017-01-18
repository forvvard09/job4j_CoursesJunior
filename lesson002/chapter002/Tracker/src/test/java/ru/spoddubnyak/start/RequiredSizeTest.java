package ru.spoddubnyak.start;


import org.junit.Test;
import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class RequiredSize.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 20.01.2017
 */

public class RequiredSizeTest {
    /**
     * Test method add class Tracker Add two new items in storage Tracker.
     */
    @Test
    public void whenNotEnoughSpaceThenIncreasingTheSizeTwiceItems() {
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
    public void whenMoreSpaceIncreasingTheReducingRizeTwiceItems() {
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

    /**
     * Test method add class Comment Add two new comments.
     */
    @Test
    public void whenNotEnoughSpaceThenIncreasingTheSizeTwiceComments() {
        final int size = 2;
        Comment[] comments = new Comment[size];
        for (int i = 0; i < 2; i++) {
            comments[i] = new Comment(String.format("%s-$s", "coments", i));
        }
        int expectedOutcome = 2 * comments.length;
        RequiredSize requiredSize = new RequiredSize();
        comments = requiredSize.increaseSize(comments);
        int result = comments.length;
        assertThat(expectedOutcome, is(result));
    }
}