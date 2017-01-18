package ru.spoddubnyak.start;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class MenuTracker.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.01.2017
 */
public class MenuTrackerTest {
    /**
     * property -  use console out.
     */
    private String newLine = System.getProperty("line.separator");

    /**
     * Test method fillActions   -  Action menu, filling.
     */
    @Test
    public void whenChangeDeleteItemThenGetItemsWithoutDeleteItem() {
        String[] answers = {""};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expectedResponse = "Testing greeting";
        menuTracker.fillActions(expectedResponse);
        expectedResponse = String.format("%s%s%s", expectedResponse, newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }
}