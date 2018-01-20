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
 * @since 19.05.2017
 */
public class SimpleArrayListTest {
    /**
     * Test method add get class SimpleArrayList.
     */
    @Test
    public void whenAddNewElementStringThenGetExpectedResult() {
        SimpleArrayList<String> stringSimpleArrayList = new SimpleArrayList<>(2);
        stringSimpleArrayList.add("1");
        stringSimpleArrayList.add("2");
        String expectedResult = "2";
        assertThat(stringSimpleArrayList.get(1), is(expectedResult));
    }

    /**
     * Test method add get class SimpleArrayList.
     */
    @Test
    public void whenAddNewElementNumberThenGetExpectedResult() {
        SimpleArrayList<Integer> stringSimpleArrayList = new SimpleArrayList<>(2);
        stringSimpleArrayList.add(1);
        stringSimpleArrayList.add(2);
        int expectedResult = 1;
        assertThat(stringSimpleArrayList.get(0), is(expectedResult));
    }

    /**
     * Test constructor class SimpleArrayList.
     */
    @Test
    public void whenSizeCreateObjetDeafultsizeThenGetExpectedSize() {
        SimpleArrayList<Integer> stringSimpleArrayList = new SimpleArrayList<>();
        stringSimpleArrayList.add(1);
        stringSimpleArrayList.add(2);
        final int expectedSize = 10;
        assertThat(stringSimpleArrayList.getLengthArrayList(), is(expectedSize));
    }

    /**
     * Test method Increase in size SimpleArrayList.
     */
    @Test
    public void whenNeedAutomaticallyIncreaseSizeObjectThenGenExpectedSizeObject() {
        SimpleArrayList<Integer> numberSimpleArrayList = new SimpleArrayList<>(2);
        numberSimpleArrayList.add(1);
        numberSimpleArrayList.add(2);
        final int expectedResult = 3;
        final int expectedLengthSimpleArrayList = 4;
        final int number = 3;
        numberSimpleArrayList.add(number);
        assertThat(numberSimpleArrayList.get(2), is(expectedResult));
        assertThat(numberSimpleArrayList.getLengthArrayList(), is(expectedLengthSimpleArrayList));
    }

    /**
     * Test iterator and methods iterators in class SimpleArrayList.
     */
    @Test
    public void whenIteratorObjectThenGetExpectedResult() {
        SimpleArrayList<Integer> numberSimpleArrayList = new SimpleArrayList<>(2);
        numberSimpleArrayList.add(1);
        numberSimpleArrayList.add(2);
        final int number = 3;
        numberSimpleArrayList.add(number);
        final int numberTwo = 4;
        numberSimpleArrayList.add(numberTwo);
        Integer[] expectedResult = new Integer[numberSimpleArrayList.getLengthArrayList()];
        Iterator<Integer> myIterator = numberSimpleArrayList.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            expectedResult[i++] = myIterator.next();
        }
        for (int j = 0; j < numberSimpleArrayList.getLengthArrayList(); j++) {
            assertThat(numberSimpleArrayList.get(j), is(expectedResult[j]));
        }
        assertThat(numberSimpleArrayList.getLengthArrayList(), is(expectedResult.length));
    }

    /**
     * Test error when referring to a non-existent index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenTryingGetElementNonExistentIndexThenGetErrorIndexOutOfBoundsException() {
        final int sizeMyList = 3;
        SimpleArrayList<Integer> stringSimpleArrayList = new SimpleArrayList<>(sizeMyList);
        stringSimpleArrayList.add(1);
        stringSimpleArrayList.add(2);
        final int numbeThree = 3;
        stringSimpleArrayList.add(numbeThree);
        final int errorElement = 99;
        stringSimpleArrayList.get(errorElement);
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
        SimpleArrayList<Integer> numberSimpleArrayList = new SimpleArrayList<>(2);
        Assert.assertThat(numberSimpleArrayList.getCount(), is(0));
        /**
         * Inner local class create thread.
         *
         */
        class ThreadTwo implements Runnable {
            @Override
            public void run() {
                for (int i = stepCount; i < expectedContelements; i++) {
                    numberSimpleArrayList.add(i);
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
                    numberSimpleArrayList.add(i);
                }
            }
        }

        ThreadOne threadOne = new ThreadOne();
        Thread threadTwo = new Thread(new ThreadTwo());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        Assert.assertThat(numberSimpleArrayList.getCount(), is(expectedContelements));
    }
}