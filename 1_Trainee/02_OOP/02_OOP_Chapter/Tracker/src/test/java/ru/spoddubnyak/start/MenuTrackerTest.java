package ru.spoddubnyak.start;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.spoddubnyak.errors.MenuOutException;
import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

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
    public void whenSetFillingGetFilling() {
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

    /**
     * Test method change Emulation menu item 1 -  Add a new item in the tracker.
     */
    @Test
    public void whenChangeAddNewItemThenGetNewItem() {
        String[] answers = {"1", "name", "desc", "111", "4"};
        final int positionName = 1;
        final int positionDesc = 2;
        final int positionCreate = 3;
        Item itemsExpecetd = new Item(answers[positionName], answers[positionDesc], Long.valueOf(answers[positionCreate]));
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(itemsMenu);
        Item[] itemGetId = tracker.findByName(answers[1]);
        String outItem = String.format("%s--%s--%s--%s", itemGetId[0].getId(), itemsExpecetd.getName(), itemsExpecetd.getDescription(), itemsExpecetd.getCreate());
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, outItem, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change emulation menu input error  -  message error.
     */
    @Test(expected = NumberFormatException.class)
    public void whenChangeAddWitErrorThenGetErrorMessage() {
        String[] answers = {"1", "name", "desc", "test", "4"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
    }

    /**
     * Test method change emulation not an existing menu item  -  message error.
     */
    @Test(expected = MenuOutException.class)
    public void whenChangeNotExistingItemMenuThenGetErrorMessage() {
        String[] answers = {"99", "name", "desc", "test", "4"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
    }

    /**
     * Test method change Emulation menu item 2 -  Update item in tracker.
     */
    @Test
    public void whenChangeUpdateItemThenGetUpdateItem() {
        String[] answers = {"2", "id", "name2", "desc2", "777", "4"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        Item[] itemGetId = tracker.findByName(answers[2]);
        final int positionName = 2;
        final int positionDesc = 3;
        final int positionCreate = 4;
        String outItem = String.format("%s--%s--%s--%s", itemGetId[0].getId(), answers[positionName], answers[positionDesc], Long.parseLong(answers[positionCreate]));
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, outItem, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 2 with error in id Item -  Get an error message.
     */
    @Test(expected = NumberFormatException.class)
    public void whenChangeUpdateItemWithErrorIdItemThenGetMessageAnError() {
        String[] answers = {"2", "99", "name2", "desc2", "777", "4"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
    }

    /**
     * Test method change Emulation menu item 2 with error in id Item -  Get an error message.
     */
    @Test(expected = NumberFormatException.class)
    public void whenChangeUpdateItemWithErrorCreateNewItemThenGetMessageAnError() {
        String[] answers = {"2", "99", "name2", "desc2", "test", "4"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
    }


    /**
     * Test method change Emulation menu item 2 -  Update item in tracker Item with comment.
     */
    @Test
    public void whenChangeUpdateIeWithCommentaryThenGetUpdateItemWithCommentary() {
        String[] answers = {"2", "id", "name2", "desc2", "999", "4"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        Comment comment = new Comment("Comment 1");
        item.addComment(comment);
        answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        Item[] itemGetId = tracker.findByName(answers[2]);
        final int positionName = 2;
        final int positionDesc = 3;
        final int positionCreate = 4;
        String outItem = String.format("%s--%s--%s--%s", itemGetId[0].getId(), answers[positionName], answers[positionDesc], Long.parseLong(answers[positionCreate]));
        if (item.getComments().size() != 0) {
            int count = 0;
            for (Comment comments : item.getComments()) {
                outItem = (String.format("%s%s%s%s: %s", outItem, newLine, " Coment ", ++count, comments.getComment()));
            }
        }
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, outItem, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 2 -  Return to menu.
     */
    @Test
    public void whenChangeUpdateItemAndReturnMenuThenReturnMenu() {
        String[] answers = {"2", "id", "m"};
        final Long create = 777L;
        Item item = new Item("test", "test", create);
        Tracker tracker = new Tracker();
        tracker.add(item);
        //answers[1] = String.valueOf(item.getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        input.ask("return menu");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        assertThat(out.toString(), is(""));
    }

    /**
     * Test method change Emulation menu item 3 -  Delete item in tracker.
     */
    @Test
    public void whenChangeDeleteItemThenGetItemsWithoutDeleteItem() {
        String[] answers = {"3", "id", "4"};
        final long itemCreateOne = 111L;
        final long itemCreateTwo = 222L;
        Item itemOne = new Item("oneName", "OneDesc", itemCreateOne);
        Item itemTwo = new Item("twoName", "twoDesc", itemCreateTwo);
        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        tracker.add(itemTwo);
        answers[1] = String.valueOf(itemOne.getId());
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menuTracker.getActions();
        int itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        menuTracker.select(itemsMenu);
        itemsMenu = input.ask("Select a menu item, to exit 'q' :> ", range);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(itemsMenu);
        Item[] itemGetId = tracker.findByName("twoName");
        String outItem = String.format("%s--%s--%s--%s", itemGetId[0].getId(), itemTwo.getName(), itemTwo.getDescription(), itemTwo.getCreate());
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, outItem, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test method change Emulation menu item 3 -  Delete item in tracker.
     */
    @Test
    public void whenChangeDeleteItemWithCommentThenGetItemsWithoutDeleteItem() {
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
            if (items[i].getComments().size() != 0) {
                int count = 0;
                for (Comment comments : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comments.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
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
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
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
        String expectedResponse = String.format("-----%s%s%s-----%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find item by name in tracker:", newLine, newLine, newLine, expectedResponseJoin, newLine, newLine, "Find item by name in tracker is successful.", newLine, newLine);
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
        String expectedResponse = String.format("-----%s%s%s-----%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find item by id in tracker:", newLine, newLine, newLine, responseItem, newLine, newLine, "The find by id operation is successful.", newLine, newLine);
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
        if (items[1].getComments().size() != 0) {
            int count = 0;
            for (Comment comments : items[1].getComments()) {
                expectedItems = (String.format("%s%s%s%s: %s", expectedItems, newLine, " Coment ", ++count, comments.getComment()));
            }
        }
        String expectedResponse = String.format("-----%s%s%s-----%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find item by id in tracker:", newLine, newLine, newLine, expectedItems, newLine, newLine, "The find by id operation is successful.", newLine, newLine);
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
            if (items[i].getComments().size() != 0) {
                int count = 0;
                for (Comment comment : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comment.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
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
            if (items[i].getComments().size() != 0) {
                int count = 0;
                for (Comment comment : item.getComments()) {
                    expectedItems[i] = (String.format("%s%s%s%s: %s", expectedItems[i], newLine, " Coment ", ++count, comment.getComment()));
                }
            }
            i++;
        }
        String expectedResponseJoin = Joiner.on(newLine).join(expectedItems);
        String expectedResponse = String.format("-----%s%s%s-----%s%s%s=>%s%s%s-----%s", newLine, "Find all item's in tracker:", newLine, newLine, expectedResponseJoin, newLine, newLine, "The find all item's in tracker is successful.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }


    /**
     * Test method change Emulation menu item 7 -  Add two comment in Item.
     */
    @Test
    public void whenTryToAddCommentToNonExistentThenGetHandleError() {
        String[] answers = {"7", "999"};
        Tracker tracker = new Tracker();
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions("Hello! Welcome to the tracking program.");
        String itemsMenu = input.ask("Select a menu item, to exit, press 'q' :> ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menuTracker.select(Integer.parseInt(itemsMenu) - 1);
        String expectedResponse = String.format("-----%s%s%s-----%s=>%s%s%s-----%s", newLine, "Add a new comment by Item in the tracker:", newLine, newLine, newLine, "Items with not found.", newLine, newLine);
        assertThat(out.toString(), is(expectedResponse));
    }
}