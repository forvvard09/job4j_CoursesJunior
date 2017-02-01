package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

/**
 * Internal class Execute actions "Update item in tracker.".
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        final int ACTION = 1;
        return ACTION;
    }

    @Override
    public String showInfo() {
        return String.format("%s. %s", this.key() + 1, "Update item in tracker.");
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Update item in tracker:");
        int idItem = Integer.parseInt(input.ask("Enter id Item :>"));
        String nameNewItem = input.ask("Enter name new Item :> ");
        String descreptionNewItem = input.ask("Enter descreption new Item :> ");
        Long createNewItem = Long.parseLong(input.ask("Enter create Item in formating Long :> "));
        tracker.update(new Item(idItem, nameNewItem, descreptionNewItem, createNewItem));
    }

    @Override
    public void showSuccess() {
        System.out.println("=>");
        System.out.println("The update operation is successful.");
        System.out.println("-----");
    }
}

/**
 * Internal  class execute actions "Find item by id in tracker.".
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.01.2017
 */
class FindById implements UserAction {

    @Override
    public int key() {
        final int ACTION = 5;
        return ACTION;
    }

    @Override
    public String showInfo() {
        return String.format("%s. %s", this.key() + 1, "Find item by id in tracker.");
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Find item by id in tracker:");
        int id = Integer.parseInt(input.ask("Enter id for find by id Item in Tracker :> "));
        Item itemFindId = tracker.findById(id);
        System.out.println("-----");
        System.out.printf("%s--%s--%s--%s%s", itemFindId.getId(), itemFindId.getName(), itemFindId.getDescription(), itemFindId.getCreate(), System.getProperty("line.separator"));
        if (itemFindId.getComments().length != 0) {
            MenuTracker.showComments(itemFindId);
        }
        showSuccess();
    }

    @Override
    public void showSuccess() {
        System.out.println("=>");
        System.out.println("The find by id operation is successful.");
        System.out.println("-----");
    }
}

/**
 * Class show menu and execute actions.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017
 */
public class MenuTracker {
    /**
     * property - the number of actions in the menu.
     */
    private static final int COUNT_ACTIONS = 7;
    /**
     * property - newLine.
     */
    private static String newLine = System.getProperty("line.separator");
    /**
     * property - greeting.
     */
    private String greeting;
    /**
     * property - specimen Tracker.
     */
    private Tracker tracker;
    /**
     * property - items menu.
     */
    private UserAction[] actions = new UserAction[COUNT_ACTIONS];

    /**
     * property - for use with the console.
     */
    private Input input;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param input   - console
     * @param tracekr - storage Items
     */
    public MenuTracker(Input input, Tracker tracekr) {
        this.input = input;
        this.tracker = tracekr;
    }


    /**
     * Static method out console comments for Item.
     *
     * @param item - item
     */
    public static void showComments(Item item) {
        int count = 0;
        for (Comment comment : item.getComments()) {
            System.out.println(String.format("%s %s: %s", " Coment", ++count, comment.getComment()));
        }
    }

    /**
     * Method fill actions for execute.
     *
     * @param greeting - greeting
     */
    public void fillActions(String greeting) {
        this.greeting = String.format("%s%s", greeting, newLine);
        System.out.println(this.greeting);
        int index = 0;
        this.actions[index++] = new AddItem();
        this.actions[index++] = new EditItem();
        this.actions[index++] = new DeleteItem();
        this.actions[index++] = new FindAllItems();
        this.actions[index++] = new FinByName();
        this.actions[index++] = new FindById();
        this.actions[index++] = new AddCommentItem();
    }

    /**
     * Method execute actions bu key.
     *
     * @param key - number actions
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Method out console menu actions.
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.showInfo());
            }
        }
    }

    /**
     * Internal static class Execute actions "Find all item's in tracker.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class FindAllItems implements UserAction {

        @Override
        public int key() {
            final int ACTION = 3;
            return ACTION;
        }

        @Override
        public String showInfo() {
            return String.format("%s. %s", this.key() + 1, "Find all item's in tracker.");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Find all item's in tracker:");
            System.out.println("-----");
            for (Item item : tracker.findAll()) {
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().length != 0) {
                    showComments(item);
                }
            }
            showSuccess();
        }

        @Override
        public void showSuccess() {
            System.out.println("=>");
            System.out.println("The find all item's in tracker is successful.");
            System.out.println("-----");
        }
    }

    /**
     * Internal static class Execute actions "Find item by name in tracker.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class FinByName implements UserAction {

        @Override
        public int key() {
            final int ACTION = 4;
            return ACTION;
        }

        @Override
        public String showInfo() {
            return String.format("%s. %s", this.key() + 1, "Find item by name in tracker.");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Find item by name in tracker:");
            String key = input.ask("Enter key for find by name in Tracker :> ");
            System.out.println("-----");
            for (Item item : tracker.findByName(key)) {
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().length != 0) {
                    showComments(item);
                }
            }
            showSuccess();
        }

        @Override
        public void showSuccess() {
            System.out.println("=>");
            System.out.println("Find item by name in tracker is successful.");
            System.out.println("-----");
        }
    }

    /**
     * Internal  class Execute actions "Add a new item in the tracker.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class AddItem implements UserAction {

        @Override
        public int key() {
            final int ACTION = 0;
            return ACTION;
        }

        @Override
        public String showInfo() {
            return String.format("%s. %s", this.key() + 1, "Add a new item in the tracker.");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Add a new item in the tracker:");
            String nameItem = input.ask("Enter name Item :> ");
            String descreptionItem = input.ask("Enter descreption Item :> ");
            Long createItem = Long.parseLong(input.ask("Enter create Item in formating Long :> "));
            tracker.add(new Item(nameItem, descreptionItem, createItem));
            showSuccess();
        }

        @Override
        public void showSuccess() {
            System.out.println("=>");
            System.out.println("The add new Item is successful.");
            System.out.println("-----");
        }
    }

    /**
     * Internal  class Execute actions "Add a new comment in Item.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class AddCommentItem implements UserAction {

        @Override
        public int key() {
            final int ACTION = 6;
            return ACTION;
        }

        @Override
        public String showInfo() {
            return String.format("%s. %s", this.key() + 1, "Add a new comment in Item.");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Add a new comment by Item in the tracker:");
            int id = Integer.parseInt(input.ask("Enter id for find by id Item in Tracker :> "));
            String commentInConsole = input.ask("Enter comment by Item :> ");
            Comment comment = new Comment(commentInConsole);
            tracker.findById(id).addComment(comment);
            showSuccess();
        }

        @Override
        public void showSuccess() {
            System.out.println("=>");
            System.out.println("The add comment in Item is successful.");
            System.out.println("-----");
        }
    }

    /**
     * Internal  class Execute actions "Delete item in tracker.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class DeleteItem implements UserAction {

        @Override
        public int key() {
            final int ACTION = 2;
            return ACTION;
        }

        @Override
        public String showInfo() {
            return String.format("%s. %s", this.key() + 1, "Delete item in tracker.");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            int idItem = Integer.parseInt(input.ask("Enter id Item delete:>"));
            if (tracker.findById(idItem).getComments().length != 0) {
                tracker.findById(idItem).delComments();
            }
            tracker.delete(tracker.findById(idItem));
            showSuccess();
        }

        @Override
        public void showSuccess() {
            System.out.println("=>");
            System.out.println("The delete operation is successful.");
            System.out.println("-----");

        }
    }
}