package ru.spoddubnyak;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class CountSymbolsInLine.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 07.12.2017
 */
public class CountSymbolsInLineTest {
    /**
     * property  test data, test input string line.
     */
    private final String testInLine = " 111 222 333 444 555  666 777 888  ";

    /**
     * property  for return.
     */
    private final String newLine = System.getProperty("line.separator");

    /**
     * Test testing count space in line string, in other Thread.
     */
    @Test
    public void whenThenSpace() {
        final String expectedResult = String.format("%s%s", "Thread number 2. Count space in line: 11.", newLine);
        CountSymbolsInLine flows = new CountSymbolsInLine(testInLine);
        Thread thread = flows.getCountSpace();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignore) { /*NOP*/ }
        assertThat(out.toString(), is(expectedResult));
    }

    /**
     * Test number of works in line string, in other Thread.
     */
    @Test
    public void whenThenWords() {
        final String expectedResult = String.format("%s%s", "Thread number 1. Count words in line: 8.", newLine);
        CountSymbolsInLine flows = new CountSymbolsInLine(testInLine);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread firstThread = flows.getCountWords();
        firstThread.start();
        try {
            firstThread.join();
        } catch (InterruptedException ignore) { /*NOP*/ }
        assertThat(out.toString(), is(expectedResult));
    }

    /**
     * Test testing result out.
     */
    @Test
    public void whenThen() {
        final int countOut = 25;
        CountSymbolsInLine flows = new CountSymbolsInLine(testInLine);
        for (int i = 1; i <= countOut; i++) {
            Thread threadFirst = flows.getCountWords();
            Thread threadSecond = flows.getCountSpace();
            System.out.println(String.format("%s%s%s", "Output №:", i, ": "));
            threadFirst.start();
            try {
                threadFirst.join();
            } catch (InterruptedException ignore) { /*NOP*/ }
            threadSecond.start();
            try {
                threadSecond.join();
            } catch (InterruptedException ignore) { /*NOP*/ }
            System.out.println("------");
        }
    }
}
