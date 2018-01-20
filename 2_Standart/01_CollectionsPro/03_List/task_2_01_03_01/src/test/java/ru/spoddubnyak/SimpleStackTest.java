package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.errors.NotElementsInCollections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Store SimpleStack.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.07.2017
 */
public class SimpleStackTest {
    /**
     * Test method push(E value) and peek(), empty() class SimpleStack.
     */
    @Test
    public void whenAddElementsThenGetExpectedElement() {
        String expectedResultOne = "1";
        String expectedResultTwo = "2";
        String expectedResultThree = "3";
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push(expectedResultOne);
        simpleStack.push(expectedResultTwo);
        simpleStack.push(expectedResultThree);
        assertThat(simpleStack.peek(), is(expectedResultThree));
        assertThat(simpleStack.empty(), is(false));
    }

    /**
     * Test method push(E value) and peek(), empty() class SimpleStack.
     */
    @Test
    public void whenNotElementsThenGetExpectedEmpty() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        assertThat(simpleStack.empty(), is(true));
    }

    /**
     * Test method push(E value) and peek(), empty() class SimpleStack.
     */
    @Test
    public void whenSearchInCollectionsThenGetExpectedResult() {
        final String expectedResultOne = "1";
        final String expectedResultTwo = "2";
        final String expectedResultThree = "3";
        final String testItem = "test";
        final int expecteOne = 3;
        final int expectedTwo = 2;
        final int expectedThree = 1;
        final int expectedError = -1;
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push(expectedResultOne);
        simpleStack.push(expectedResultTwo);
        simpleStack.push(expectedResultThree);
        simpleStack.search(expectedResultOne);
        assertThat(simpleStack.search(expectedResultOne), is(expecteOne));
        assertThat(simpleStack.search(expectedResultTwo), is(expectedTwo));
        assertThat(simpleStack.search(expectedResultThree), is(expectedThree));
        assertThat(simpleStack.search(testItem), is(expectedError));
    }

    /**
     * Test method push(E value) and pop() class SimpleStack.
     */
    @Test
    public void whenPushAndPopElementsInCollectionsThenGetExpectedResult() {
        final String expectedResultOne = "1";
        final String expectedResultTwo = "2";
        final String expectedResultThree = "3";
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push(expectedResultOne);
        simpleStack.push(expectedResultTwo);
        simpleStack.push(expectedResultThree);
        assertThat(simpleStack.peek(), is(expectedResultThree));
        simpleStack.pop();
        assertThat(simpleStack.peek(), is(expectedResultTwo));
        simpleStack.pop();
        assertThat(simpleStack.peek(), is(expectedResultOne));
    }

    /**
     * Test method pop() Attempt to remove a nonexistent item from the collection SimpleStack.
     */
    @Test(expected = NotElementsInCollections.class)
    public void whenPopNonExistentElementThenGetErrorNotElementsInCollections() {
        final String expectedResultOne = "1";
        final String expectedResultTwo = "2";
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push(expectedResultOne);
        simpleStack.push(expectedResultTwo);
        simpleStack.pop();
        simpleStack.pop();
        simpleStack.pop();
    }
}
