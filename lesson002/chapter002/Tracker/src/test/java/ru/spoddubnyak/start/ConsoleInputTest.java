package ru.spoddubnyak.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class ConsoleInputTest.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 06.01.2016
 */

public class ConsoleInputTest {
    /**  .  */
    private String consoleInData = "Testing answer.\\r\\n";


    /**
     * .
     */
    @Test
    public void whenAddTwoNewItemsThenGetSameResultInTracker() {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(consoleInData.getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        String data = consoleInput.ask("Testing question: ");
        System.setIn(inputStream);
        assertThat(consoleInData, is(data));
    }
}
