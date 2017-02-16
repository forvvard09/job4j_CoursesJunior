package ru.spoddubnyak.start;

import org.junit.Test;
import ru.spoddubnyak.errors.MenuOutException;
import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class StartUI.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 06.01.2016
 */
public class StartUITest {

    /**
     * property -  testing greeting for tests.
     */
    private String testGreeting = "TestingGreeting";

    /**
     * property -  use console out.
     */
    private String newLine = System.getProperty("line.separator");

    /**
     * Test StartUI.
     */
    @Test
    public void whenTestThenTest() {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(input, tracker);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menu.fillActions(testGreeting);
        String expectedResult = String.format("%s%s%s", testGreeting, newLine, newLine);
        assertThat(out.toString(), is(expectedResult));
    }

    /**
     * Test StartUI.
     */
    @Test(expected = MenuOutException.class)
    public void whenTestThenTest2() {
        String[] answer = {"99"};
        Input input = new StubInput(answer);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(input, tracker);
        start.init();
    }

    /**
     * Test StartUI.
     */
    @Test(expected = NumberFormatException.class)
    public void whenTestThenTest3() {
        String[] answer = {"test"};
        Input input = new StubInput(answer);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(input, tracker);
        start.init();
    }

    /**
     * Test StartUI.
     */
    @Test
    public void whenChangeAddNewItemThenGetNewItem() {
        String[] answers = {"1", "name1", "desc1", "001", "q"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        Item[] expectItems = tracker.findByName(answers[1]);
        assertThat(expectItems, is(tracker.findAll()));
    }

    /**
     * Test StartUI.
     */
    @Test(expected = NumberFormatException.class)
    public void whenThenTest() {
        String[] answers = {"1", "name1", "desc1", "test", "q"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
    }

    /**
     * Test method change Emulation menu item 2 -  Update item in tracker.
     */
    @Test
    public void whenChangeUpdateIemThenGetUpdateItem() {
        String[] answers = {"2", "id", "name2", "desc2", "999", "q"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        Item[] expectItems = tracker.findByName(answers[2]);
        assertThat(expectItems, is(tracker.findAll()));
    }

    /**
     * Test Update item in tracker.
     */
    @Test(expected = NumberFormatException.class)
    public void whenChangeUpdateIemThenTest() {
        String[] answers = {"2", "id", "name2", "desc2", "test", "q"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
    }

    /**
     * Test method change Emulation menu item 3 -  Delete item in tracker.
     */
    @Test
    public void whenChangeDeleteItemThenGetItemsWithoutDeleteItem() {
        final int firstElement = 0;
        String[] answers = {"3", "id", "q"};
        final Long createOne = 111L;
        final Long createTwo = 222L;
        Item itemOne = new Item("name01", "desc01", createOne);
        Item itemTwo = new Item("name02", "desc02", createTwo);
        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        tracker.add(itemTwo);
        answers[1] = String.valueOf(itemOne.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(itemTwo, is(tracker.findAll()[firstElement]));
    }

    /**
     * Test method .
     */
    @Test
    public void whenChangeDeleteItemThenGetItemsWithoutDeleteItemTest() {
        final int firstElement = 0;
        String[] answers = {"3", "m", "q"};
        final Long createOne = 111L;
        Item itemOne = new Item("name01", "desc01", createOne);
        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(itemOne, is(tracker.findAll()[firstElement]));
    }

    /**
     * Test method change Emulation menu item 7 -  Add comment in Item.
     */
    @Test
    public void whenSetCommentThenGetComment() {
        String[] answers = {"7", "id", "testComment", "q"};
        Item item = new Item("name01", "desc01");
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        String[] expectedComment = {answers[2]};
        new StartUI(input, tracker).init();
        String[] parentComment = new String[1];
        int i = 0;
        item = tracker.findById(item.getId());
        for (Comment comment : item.getComments()) {
            parentComment[i] = comment.getComment();
            i++;
        }
        assertThat(expectedComment, is(parentComment));
    }

    /**
     * Test. Emulation erroneous object creation.
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateObjectWithEmptyThenError() {
        Tracker tracker = null;
        Input input = null;
        new StartUI(input, tracker).init();
    }

    /**
     * Test. Emulation erroneous object creation.
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateObjectWithOneEmptyThenError() {
        Tracker tracker = new Tracker();
        Input input = null;
        StartUI start = new StartUI(input, tracker);
        start.init();
    }

    /**
     * Test method change Emulation several menu items that are suitable within the meaning of.
     */
    @Test
    public void whenSelectMenuItemsThenGetExpectedResult() {
        final int firstElement = 0;
        String[] answers = {"3", "666", "n", "3", "id", "n", "4", "q"};
        final Long createOne = 111L;
        final Long createTwo = 222L;
        Item itemOne = new Item("name01", "desc01", createOne);
        Item itemTwo = new Item("name02", "desc02", createTwo);
        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        tracker.add(itemTwo);
        final int idAnswer = 4;
        answers[idAnswer] = String.valueOf(itemOne.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(itemTwo, is(tracker.findAll()[firstElement]));
    }
}