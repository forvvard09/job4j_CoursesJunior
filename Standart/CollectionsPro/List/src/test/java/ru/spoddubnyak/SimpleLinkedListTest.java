package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Store SimpleArrayList.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.05.2017
 */
public class SimpleLinkedListTest {
    /**
     * Test method add get class SimpleLinkedList.
     */
    @Test
    public void whenThen() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        String expectedResult = "2";
        assertThat(simpleLinkedList.get(1), is(expectedResult));
    }
}