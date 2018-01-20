package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Store SimpleQueue.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.07.2017
 */
public class SimpleQueueTest {
    /**
     * Test method offer(E value) and peek(), class SimpleQueue.
     */
    @Test
    public void whenOfferElementsThenGetExpectedElement() {
        String expectedResultOne = "1";
        String expectedResultTwo = "2";
        String expectedResultThree = "3";
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        assertThat(simpleQueue.offer(expectedResultOne), is(true));
        assertThat(simpleQueue.offer(expectedResultTwo), is(true));
        assertThat(simpleQueue.offer(expectedResultThree), is(true));
        assertThat(simpleQueue.peek(), is(expectedResultOne));
    }

    /**
     * Test method offer(E value) class SimpleQueue.
     */
    @Test
    public void whenOfferElementsThenGetExpectedResult() {
        final String expectedResultOne = "1";
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        assertThat(simpleQueue.offer(expectedResultOne), is(true));
    }

    /**
     * Test method offer(E value) and peek() and pool() class SimpleStack.
     */
    @Test
    public void whenOfferAndPollElementsThenGetExpectedElement() {
        String expectedResultOne = "1";
        String expectedResultTwo = "2";
        String expectedResultThree = "3";
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        assertThat(simpleQueue.offer(expectedResultOne), is(true));
        assertThat(simpleQueue.offer(expectedResultTwo), is(true));
        assertThat(simpleQueue.offer(expectedResultThree), is(true));
        simpleQueue.poll();
        simpleQueue.poll();
        assertThat(simpleQueue.peek(), is(expectedResultThree));
    }

    /**
     * Test method offer(E value), return false with a failed attempt.
     */
    @Test
    public void whenFalseOfferThenGetExpectedFalse() {
        String expectedResultOne = "1";
        final int errorSizeQueue = -1;
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.setSizeQueue(errorSizeQueue);
        assertThat(simpleQueue.offer(expectedResultOne), is(false));
    }
}
