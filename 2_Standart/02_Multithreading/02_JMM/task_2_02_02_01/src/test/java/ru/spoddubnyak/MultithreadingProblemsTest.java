package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class MultithreadingProblems.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.01.2018
 */
public class MultithreadingProblemsTest {
    /**
     * Test shows problems multithreading.
     *
     * @throws InterruptedException - errors with threads
     */
    @Test
    public void whenGetResultThenNotExpectedResult() throws InterruptedException {
        MultithreadingProblems multiProblem = new MultithreadingProblems();
        multiProblem.runing();
        final int expectedResult = multiProblem.getCountStep() * 2;
        boolean resultOne = (expectedResult == multiProblem.getResultVisibilityProblem());
        boolean resultTwo = (expectedResult == multiProblem.getResultRaceCondition());
        assertThat(false, is(resultOne));
        assertThat(false, is(resultTwo));
    }
}
