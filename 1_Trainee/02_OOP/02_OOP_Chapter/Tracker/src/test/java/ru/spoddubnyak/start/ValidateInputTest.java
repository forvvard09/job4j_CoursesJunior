package ru.spoddubnyak.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ValidateInput checks the validity of entered data.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 11.02.2017
 */
public class ValidateInputTest extends ConsoleInput {

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
        ValidateInput validate = new ValidateInput();
        String data = validate.ask("Testing question: ");
        System.setIn(inputStream);
        assertThat(consoleInName, is(data));
    }

    /**
     * Test method long ask.
     */
    @Test
    public void whenAddNewCreateThenGetSameResultat() {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(consoleInCreate.getBytes()));
        ValidateInput validate = new ValidateInput();
        final long mask = 99;
        long expectedResult = Long.parseLong(consoleInCreate);
        Long data = validate.ask("Testing question: ", mask);
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
        ValidateInput validate = new ValidateInput();
        int expectedResult = Integer.parseInt(consoleInMenu) - 1;
        int key = validate.ask("Testing question: ", range);
        System.setIn(inputStream);
        assertThat(expectedResult, is(key));
    }

    /**
     * Test method int ask emulate a variety of non-existent menu item.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenChangeDoesNotExistMenuItemThenGetError() {
        final int[] range = {1, 2, 3, 4};
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream("9".getBytes()));
        ValidateInput validate = new ValidateInput();
        validate.ask("Testing question: ", range);
        System.setIn(inputStream);
    }
}