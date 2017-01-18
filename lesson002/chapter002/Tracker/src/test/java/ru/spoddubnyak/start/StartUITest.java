package ru.spoddubnyak.start;

import com.google.common.base.Joiner;
import org.junit.Test;
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
     * property -  use console out.
     */
    private String newLine = System.getProperty("line.separator");

    /**
     * Test method change Emulation menu item 1 -  Add a new item in the tracker.
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
     * Test method change Emulation menu item 3 -  Delete item in tracker.
     */
    @Test
    public void whenChangeDeleteItemWidthCommentThenGetItemsWithoutDeleteItem() {
        String[] answers = {"3", "id", "4"};
        final int size = 2;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        answers[1] = String.valueOf(items[1].getId());
        Comment comment = new Comment("Testing comment...");
        tracker.findById(items[0].getId()).addComment(comment);
        tracker.findById(items[1].getId()).addComment(comment);
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        final int exepctedSize = size - 1;
        String[] expectedItems = new String[exepctedSize];
        int i = 0;
        for (Item item : tracker.findAll()) {
            expectedItems[i] = String.format("%s--%s--%s--%s", items[i].getId(), items[i].getName(), items[i].getDescription(), items[i].getCreate());
            if (items[i].getComments().length != 0) {
                int count = 0;
                for (Comment comments : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comments.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 4 -  Find all item's in tracker.
     */
    @Test
    public void whenChangeFindAllItemsGetAllItemsToConsole() {
        String[] answers = {"4"};
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
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String answer = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(answer) - 1);
        String[] expectedItems = new String[size];
        int i = 0;
        for (Item item : tracker.findAll()) {
            expectedItems[i] = String.format("%s--%s--%s--%s", items[i].getId(), items[i].getName(), items[i].getDescription(), items[i].getCreate());
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
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
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String answer = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(answer) - 1);
        String[] expectedItems = new String[2];
        for (int i = 0; i < items.length; i++) {
            expectedItems[i] = String.format("%s--%s--%s--%s", items[i].getId(), items[i].getName(), items[i].getDescription(), items[i].getCreate());
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find item by name in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "Find item by name in tracker is successful.", newLine, newLine);
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
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        String responseItem = String.format("%s--%s--%s--%s", items[1].getId(), items[1].getName(), items[1].getDescription(), items[1].getCreate());
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find item by id in tracker:", newLine, newLine, responseItem, newLine, newLine, "The find by id operation is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 6 -  Find item by id in tracker with comment.
     */
    @Test
    public void whenFindItemWidthCommentThenGetItemsWithoutDeleteItem() {
        String[] answers = {"6", "id"};
        final int size = 2;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        answers[1] = String.valueOf(items[1].getId());
        Comment comment = new Comment("Testing comment 1...");
        tracker.findById(items[0].getId()).addComment(comment);
        comment.setComment("Testing comment 2...");
        tracker.findById(items[1].getId()).addComment(comment);
        tracker.findById(items[1].getId()).addComment(comment);
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        String expectedItems;
        expectedItems = String.format("%s--%s--%s--%s", items[1].getId(), items[1].getName(), items[1].getDescription(), items[1].getCreate());
        if (items[1].getComments().length != 0) {
            int count = 0;
            for (Comment comments : items[1].getComments()) {
                expectedItems = (String.format("%s%s%s%s: %s", expectedItems, newLine, " Coment ", ++count, comments.getComment()));
            }
        }
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find item by id in tracker:", newLine, newLine, expectedItems, newLine, newLine, "The find by id operation is successful.", newLine, newLine);
//        String expectedResponse = String.format("%s%s-----%s%s=>%s%s-----%s", "Find item by id in tracker:", newLine, expectedItems, newLine, "The find by id operation is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }


    /**
     * Test method change Emulation menu item 7 -  Add comment in Item.
     */
    @Test
    public void whenSetCommentGetCommentToConsole() {
        String[] answers = {"7", "id", "testComment", "4", "id"};
        final int size = 2;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        answers[1] = String.valueOf(items[1].getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        String[] expectedItems = new String[size];
        int i = 0;
        for (Item item : tracker.findAll()) {
            expectedItems[i] = String.format("%s--%s--%s--%s", items[i].getId(), items[i].getName(), items[i].getDescription(), items[i].getCreate());
            if (items[i].getComments().length != 0) {
                int count = 0;
                for (Comment comment : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comment.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 7 -  Add two comment in Item.
     */
    @Test
    public void whenSetTwoCommentGetCommentToConsole() {
        String[] answers = {"7", "id", "testComment", "7", "id", "testComment", "4", "id"};
        final int size = 2;
        Item[] items = new Item[size];
        items[0] = new Item("name01", "desc01");
        items[1] = new Item("name02", "desc02");
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        answers[1] = String.valueOf(items[1].getId());
        final int answerId = 4;
        answers[answerId] = answers[1];
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        String[] expectedItems = new String[size];
        int i = 0;
        for (Item item : tracker.findAll()) {
            expectedItems[i] = String.format("%s--%s--%s--%s", items[i].getId(), items[i].getName(), items[i].getDescription(), items[i].getCreate());
            if (items[i].getComments().length != 0) {
                int count = 0;
                for (Comment comment : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comment.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("%s%s-----%s%s%s=>%s%s%s-----%s", "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }
}