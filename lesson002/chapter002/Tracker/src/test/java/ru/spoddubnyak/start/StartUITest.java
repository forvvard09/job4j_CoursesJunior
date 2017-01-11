package ru.spoddubnyak.start;

import org.junit.Test;
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
     * Test method change Emulation menu item 1 -  Add a new item in the tracker.
     */
    @Test
    public void whenChangeAddNewItemThenGetNewItem() {
        String[] answers = {"1", "name1", "desc1", "001"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        Tracker tracker = new Tracker();
        String itemsMenu = input.ask("Testing: ");
        startUI.action(itemsMenu, tracker);
        Item[] items = tracker.findByName(answers[1]);
        assertThat(items, is(tracker.findAll()));
    }

    /**
     * Test method change Emulation menu item 2 -  Update item in tracker.
     */
    @Test
    public void whenChangeUpdateIemThenGetUpdateItem() {
        String[] answers = {"2", "id", "name2", "desc2", "999"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        String itemsMenu = input.ask("Testing: ");
        startUI.action(itemsMenu, tracker);
        Item[] itemUpdate = tracker.findByName(answers[2]);
        assertThat(itemUpdate, is(tracker.findAll()));
    }

    /**
     * Test method change Emulation menu item 3 -  Delete item in tracker.
     */
    @Test
    public void whenChangeDeleteItemThenGetItemsWithoutDeleteItem() {
        final int firstElement = 0;
        String[] answers = {"3", "id"};
        final Long createOne = 111L;
        final Long createTwo = 222L;
        Item itemOne = new Item("name01", "desc01", createOne);
        Item itemTwo = new Item("name02", "desc02", createTwo);
        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        tracker.add(itemTwo);
        answers[1] = String.valueOf(itemOne.getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        String itemsMenu = input.ask("Testing: ");
        startUI.action(itemsMenu, tracker);
        assertThat(itemTwo, is(tracker.findAll()[firstElement]));
    }

    /**
     * Test method change Emulation menu item 4 -  Find all item's in tracker.
     */
    @Test
    public void whenChangeFindAllItemsGetAllItemsToConsole() {
        String[] answers = {"4", "name"};
        final int size = 3;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        items[2] = new Item("test", "test");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        String itemsMenu = input.ask("Testing: ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        startUI.action(itemsMenu, tracker);
        String expectedResponse = "Find all item's in tracker:" + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator")
                + items[0].getId() + "--" + items[0].getName() + "--" + items[0].getDescription() + "--" + items[0].getCreate() + System.getProperty("line.separator")
                + items[1].getId() + "--" + items[1].getName() + "--" + items[1].getDescription() + "--" + items[1].getCreate() + System.getProperty("line.separator")
                + items[2].getId() + "--" + items[2].getName() + "--" + items[2].getDescription() + "--" + items[2].getCreate() + System.getProperty("line.separator")
                + "=>" + System.getProperty("line.separator")
                + "The find all operation is successful." + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator");
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 5 -  Find item by name in tracker.
     */
    @Test
    public void whenChangeFindNameGetAllItemsByFindToConsole() {
        String[] answers = {"5", "name"};
        Item[] items = new Item[2];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        Item itemThree = new Item("test", "test");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(itemThree);
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        String itemsMenu = input.ask("Testing: ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        startUI.action(itemsMenu, tracker);
        String expectedResponse = "Find item by name in tracker:" + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator")
                + items[0].getId() + "--" + items[0].getName() + "--" + items[0].getDescription() + "--" + items[0].getCreate() + System.getProperty("line.separator")
                + items[1].getId() + "--" + items[1].getName() + "--" + items[1].getDescription() + "--" + items[1].getCreate() + System.getProperty("line.separator")
                + "=>" + System.getProperty("line.separator")
                + "The find by name operation is successful." + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator");
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 6 -  Find item by id in tracker.
     */
    @Test
    public void whenChangeFinIdGetItemByIdToConsole() {
        String[] answers = {"6", "name"};
        final int size = 3;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        items[2] = new Item("name03", "desc03");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        answers[1] = String.valueOf(items[1].getId());
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input);
        String itemsMenu = input.ask("Testing: ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        startUI.action(itemsMenu, tracker);
        String expectedResponse = "Find item by id in tracker:" + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator")
                + items[1].getId() + "--" + items[1].getName() + "--" + items[1].getDescription() + "--" + items[1].getCreate() + System.getProperty("line.separator")
                + "=>" + System.getProperty("line.separator")
                + "The find by id operation is successful." + System.getProperty("line.separator")
                + "-----" + System.getProperty("line.separator");
        assertThat(out.toString(), is(expectedResponse));
    }
}