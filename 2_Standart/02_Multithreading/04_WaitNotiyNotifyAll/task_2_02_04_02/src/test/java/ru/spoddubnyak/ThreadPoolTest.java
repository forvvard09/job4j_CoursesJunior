package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ThreadPoolTest for testing method's class hreadPool and realization thread pool.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.03.2018
 */
public class ThreadPoolTest {
    /**
     * property for timer.
     */
    private final int timer = 15000;
    /**
     * property for array test work .
     */
    private List<Work> listWork = new ArrayList<>();
    /**
     * property for test ThreadPool.
     */
    private ThreadPool threadPool = new ThreadPool();

    /**
     * Method generation test data for testing.
     */
    @Before
    public void generationTestData() {
        final int timer = 10;
        final int testValueOne = 5;
        final int testValueTwo = 10;

        Work workOne = new Work(1, 1, timer);
        Work workTwo = new Work(testValueOne, testValueTwo, timer);
        this.listWork.add(workOne);
        this.listWork.add(workTwo);
    }


    /**
     * Testing thread pool.
     */
    @Test
    public void whenCreateArrayWorkOneThenExpectedReslt() {
        /**
         * property for count test elements.
         */
        final int countElements = 10;

        generationTestData();
        for (int i = 0; i < countElements; i++) {
            threadPool.add(this.listWork.get(0));
        }
        assertThat(countElements, is(threadPool.getQueuePool().size()));
        threadPool.startTreathGroup();
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadGroup().activeCount()));

        try {
            Thread.sleep(this.timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(0, is(threadPool.getQueuePool().size()));
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadGroup().activeCount()));
        assertThat(countElements, is(threadPool.getSumAllWorks()));
    }

    /**
     * Testing thread pool.
     */
    @Test
    public void whenCreateArrayWorkTwoThenExpectedReslt() {
        /**
         * property for count test elements.
         */
        final int countElements = 5;

        /**
         * property for count test elements.
         */
        final int expectedAmountResult = 50;

        generationTestData();
        for (int i = 0; i < countElements; i++) {
            threadPool.add(this.listWork.get(1));
        }
        assertThat(countElements, is(threadPool.getQueuePool().size()));
        threadPool.startTreathGroup();
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadGroup().activeCount()));

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(0, is(threadPool.getQueuePool().size()));
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadGroup().activeCount()));
        assertThat(expectedAmountResult, is(threadPool.getSumAllWorks()));
    }
}