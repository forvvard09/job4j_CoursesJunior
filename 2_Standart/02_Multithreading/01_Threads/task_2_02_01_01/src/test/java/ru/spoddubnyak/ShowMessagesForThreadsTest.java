package ru.spoddubnyak;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class ShowMessagesForThreads.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.12.2017
 */

public class ShowMessagesForThreadsTest {
    /**
     * property -  object for run program.
     */
    private ShowMessagesForThreads app = new ShowMessagesForThreads("1 22 333 4444 55555");
    /**
     * property -  new line.
     */
    private final String newLine = System.getProperty("line.separator");
    /**
     * property -  expected answer.
     */
    private final String expGetCountSpace = "Thread number 1. Count words in line: 5.";
    /**
     * property -  expected answer.
     */
    private final String expGetResultCountWords = "Thread number 2. Count space in line: 4.";
    /**
     * property -  expected answer.
     */
    private final String expGetStartMessage = "Start program.";
    /**
     * property -  expected answer.
     */
    private final String expGetFinishMessage = "Finish program.";

    /**
     * Test testing result out.
     */
    @Test
    public void whenGetresultThenExpectedResult() {
        /**
         * property -  expected answer
         */
        String expectedResponse = String.format("%s%s%s%s%s%s%s%s", expGetStartMessage, newLine,
                expGetResultCountWords, newLine, expGetCountSpace, newLine, expGetFinishMessage, newLine);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread thread = null;
        try {
            thread = app.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(expectedResponse));
    }
}
