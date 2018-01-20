package ru.spoddubnyak;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Store SimpleArrayList.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.07.2017
 */
public class SimpleLinkedListTest {
    /**
     * Test method add(E value) and get(i index) class SimpleLinkedList.
     */
    @Test
    public void whenAddNewElementStringThenGetExpectedResult() {
        String expectedResultOne = "1";
        String expectedResultTwo = "2";
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(expectedResultOne);
        simpleLinkedList.add(expectedResultTwo);
        assertThat(simpleLinkedList.get(0), is(expectedResultOne));
        assertThat(simpleLinkedList.get(1), is(expectedResultTwo));
    }

    /**
     * Test method getSize get class SimpleLinkedList.
     */
    @Test
    public void whenExpectedSizeThenGetResultSize() {
        String[] testData = {"test", "test", "test", "test", "test"};
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        for (String item : testData) {
            simpleLinkedList.add(item);
        }
        assertThat(simpleLinkedList.getSize(), is(testData.length));
    }

    /**
     * Test method removeLastElement class SimpleLinkedList.
     */
    @Test
    public void whenRemoveLastElementThenGetExpectedSizeCollections() {
        String[] testData = {"1", "2", "3"};
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        for (String item : testData) {
            simpleLinkedList.add(item);
        }
        simpleLinkedList.removeLastElement();
        assertThat(simpleLinkedList.getSize() + 1, is(testData.length));
    }

    /**
     * Test method removeLastElement class SimpleLinkedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveLastElementThenGetError() {
        String expectedResultOne = "1";
        String expectedResultTwo = "2";
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(expectedResultOne);
        simpleLinkedList.add(expectedResultTwo);
        simpleLinkedList.removeLastElement();
        simpleLinkedList.get(1);
    }

    /**
     * Test method removeFirstElement class SimpleLinkedList.
     */
    @Test
    public void whenRemoveFirstElementThenGetExpectedSizeCollections() {
        String[] testData = {"1", "2", "3"};
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        for (String item : testData) {
            simpleLinkedList.add(item);
        }
        simpleLinkedList.removeFirstElement();
        simpleLinkedList.removeFirstElement();
        assertThat(simpleLinkedList.get(0), is(testData[2]));
        assertThat(simpleLinkedList.getSize() + 2, is(testData.length));
    }

    /**
     * Test method removeLastElement and add class SimpleLinkedList.
     */
    @Test
    public void whenRemoveLastElementAndAddElementThenGetExpectedResult() {
        final String expectedResultOne = "1";
        final String expectedResultTwo = "2";
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(expectedResultOne);
        simpleLinkedList.add(expectedResultTwo);
        simpleLinkedList.removeLastElement();
        simpleLinkedList.add(expectedResultTwo);
        assertThat(simpleLinkedList.get(0), is(expectedResultOne));
        assertThat(simpleLinkedList.get(1), is(expectedResultTwo));
    }

    /**
     * Test iterator and methods iterators in class SimpleLinkedList.
     */
    @Test
    public void whenIteratorObjectThenGetExpectedResult() {
        SimpleLinkedList<Integer> numberSimpleLinkedList = new SimpleLinkedList<>();
        numberSimpleLinkedList.add(1);
        numberSimpleLinkedList.add(2);
        final int number = 3;
        numberSimpleLinkedList.add(number);
        final int numberTwo = 4;
        numberSimpleLinkedList.add(numberTwo);
        Integer[] expectedResult = new Integer[numberSimpleLinkedList.getSize()];
        Iterator<Integer> myIterator = numberSimpleLinkedList.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            expectedResult[i++] = myIterator.next();
        }
        for (int j = 0; j < numberSimpleLinkedList.getSize(); j++) {
            assertThat(numberSimpleLinkedList.get(j), is(expectedResult[j]));
        }
        assertThat(numberSimpleLinkedList.getSize(), is(expectedResult.length));
    }

    /**
     * Testing method add Object in coolection in two thread.
     *
     * @throws InterruptedException errors thread
     */
    @Test
    public void whenAddTwoThreadThenGetExpectedResult() throws InterruptedException {
        final int expectedContelements = 2_000_000;
        final int stepCount = 1_000_000;
        SimpleLinkedList<Integer> numberSimpleLinkedList = new SimpleLinkedList<>();
        Assert.assertThat(numberSimpleLinkedList.getSize(), is(0));
        /**
         * Inner local class create thread.
         *
         */
        class ThreadTwo implements Runnable {
            @Override
            public void run() {
                for (int i = stepCount; i < expectedContelements; i++) {
                    numberSimpleLinkedList.add(i);
                }
            }
        }
        /**
         * Inner local class create thread for testing.
         *
         */
        class ThreadOne extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < stepCount; i++) {
                    numberSimpleLinkedList.add(i);
                }
            }
        }

        ThreadOne threadOne = new ThreadOne();
        Thread threadTwo = new Thread(new ThreadTwo());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        Assert.assertThat(numberSimpleLinkedList.getSize(), is(expectedContelements));
    }
}