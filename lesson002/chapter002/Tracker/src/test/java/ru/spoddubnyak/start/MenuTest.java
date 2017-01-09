package ru.spoddubnyak.start;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Menu.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.12.2016
 */
public class MenuTest {
    /**
     * property - items menu.
     */
    private String[] expectedAnswers = {"1: Menu 1", "2: Menu 2", "3: Menu 3"};
    /**
     * property - greeting.
     */
    private String expectedGreeting = "Hello! Test greetin.";

    /**
     * Test method writes and read a list of available actions menu.
     */
    @Test
    public void whenSetListActionMenuThenGetListActionMenu() {
        Menu menu = new Menu();
        menu.setMenuActions(expectedAnswers);
        assertThat(expectedAnswers, is(menu.getMenuActions()));
    }

    /**
     * Test method writes and read a greeting.
     */
    @Test
    public void whenSetGreetingThenGetGreetin() {
        Menu menu = new Menu();
        menu.setGreeting(expectedGreeting);
        assertThat(expectedGreeting, is(menu.getGreeting()));
    }

    /**
     * Test method writes a greeting and out to console.
     */
    @Test
    public void whenShowGreetingThenOutGreeting() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu();
        menu.setGreeting(expectedGreeting);
        menu.showGreeting();
        assertThat(out.toString(), is(expectedGreeting + System.getProperty("line.separator") + System.getProperty("line.separator")));
    }
}