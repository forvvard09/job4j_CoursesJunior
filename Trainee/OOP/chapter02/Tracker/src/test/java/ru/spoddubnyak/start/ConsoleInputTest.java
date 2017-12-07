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

    /**
     * property - data in console.
     */
    private String consoleInName = "Testing answer.\\r\\n";

    /**
     * property - data in console.
     */
    private String consoleInCreate = "55";

    /**
     * property - data in console.
     */
    private String consoleInMenu = "3";


    /**
     * Test method String ask.
     */
    @Test
    public void whenAddTwoNewItemsThenGetSameResultInTracker() {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(consoleInName.getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        String data = consoleInput.ask("Testing question: ");
        System.setIn(inputStream);
        assertThat(consoleInName, is(data));
    }

    /**
     *
     * Test method long ask.
     *
     */
    @Test
    public void whenAddNewCreateThenGetSameResultat() {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(consoleInCreate.getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        final long mask = 99;
        long expectedResult = Long.parseLong(consoleInCreate);
        Long data = consoleInput.ask("Testing question: ", mask);
        System.setIn(inputStream);
        assertThat(expectedResult, is(data));
    }

    /**
     * Test method int ask.
     */
    @Test
    public void whenChangeMenuItemThenGetKeyItem() {
        final int[] range = {1, 2, 3, 4};
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(consoleInMenu.getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        int expectedResult = Integer.parseInt(consoleInMenu) - 1;
        int key = consoleInput.ask("Testing question: ", range);
        System.setIn(inputStream);
        assertThat(expectedResult, is(key));
    }
}