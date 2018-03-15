package ru.spoddubnyak;

import org.junit.After;
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
 * @version 2.0
 * @since 13.03.2018
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
     * Method clear test data.
     */
    @After
    public void clearTestData() {
        this.listWork.clear();
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
        for (int i = 0; i < countElements; i++) {
            threadPool.add(this.listWork.get(0));
        }
        assertThat(countElements, is(threadPool.getQueuePool().size()));
        threadPool.startThreadPool();
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadsList().size()));

        try {
            Thread.sleep(this.timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(0, is(threadPool.getQueuePool().size()));
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadsList().size()));
        assertThat(countElements, is(threadPool.getSumAllWorks()));
        threadPool.stopThreadPool();
        assertThat(0, is(threadPool.getThreadsList().size()));

    }

    /**
     * Testing thread pool.
     */
    @Test
    public void whenCreateArrayWorkTwoThenExpectedResult() {
        /**
         * property for count test elements.
         */
        final int countElements = 5;

        /**
         * property for count test elements.
         */
        final int expectedAmountResult = 50;

        for (int i = 0; i < countElements; i++) {
            threadPool.add(this.listWork.get(1));
        }
        assertThat(countElements, is(threadPool.getQueuePool().size()));
        threadPool.startThreadPool();
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadsList().size()));

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(0, is(threadPool.getQueuePool().size()));
        assertThat(threadPool.getCountProc(), is(threadPool.getThreadsList().size()));
        assertThat(expectedAmountResult, is(threadPool.getSumAllWorks()));
        threadPool.stopThreadPool();
        assertThat(0, is(threadPool.getThreadsList().size()));
    }
}