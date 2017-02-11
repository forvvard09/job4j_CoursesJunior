package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

import java.util.Optional;

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
        String newLine = System.getProperty("line.separator");
        boolean invalid = true;
        System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Update item in tracker:", newLine, "-----", newLine);
        int idItem = 0;
        do {
            try {
                String answer = input.ask("Enter id Item or press 'm' to return to the menu :> ");
                if (answer.equals("m")) {
                    System.out.println("Return menu.");
                    return;
                }
                idItem = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data or 'm' to return to the menu.", newLine, "-----", newLine);
            }
        } while (invalid);
        if (tracker.findById(idItem) == null) {
            System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
            return;
        }
        String nameNewItem = input.ask("Enter name new Item :> ");
        String descreptionNewItem = input.ask("Enter descreption new Item :> ");
        long createNewItem = input.ask("Enter create Item in formating long :> ", tracker.getMascCreate());
        tracker.update(new Item(idItem, nameNewItem, descreptionNewItem, createNewItem));
        showSuccess();
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

    /**
     * property - carriage shift.
     */
    private String newLine = System.getProperty("line.separator");

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
        System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find item by id in tracker:", newLine, "-----", newLine);
        int id = 0;
        boolean invalid = true;
        do {
            try {
                String answer = input.ask("Enter id for find by id Item in Tracker or press 'm' return to menu. :> ");
                if (answer.equals("m")) {
                    System.out.println("Return menu.");
                    return;
                }
                id = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again or 'm' to return to the menu.", newLine, "-----", newLine);
            }
        } while (invalid);
        Optional<Item> itemFindId = Optional.ofNullable(tracker.findById(id));
        if (!itemFindId.isPresent()) {
            System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
            return;
        } else {
            System.out.println("-----");
            System.out.printf("%s--%s--%s--%s%s", itemFindId.get().getId(), itemFindId.get().getName(), itemFindId.get().getDescription(), itemFindId.get().getCreate(), System.getProperty("line.separator"));
            if (itemFindId.get().getComments().length != 0) {
                MenuTracker.showComments(itemFindId.get());
            }
            showSuccess();
        }
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
     * Geter get number actions.
     *
     * @return number actions
     */
    public int[] getActions() {
        int[] getActions = new int[this.actions.length];
        int i = 0;
        for (UserAction action : this.actions) {
            if (action != null) {
                getActions[i] = action.key();
            }
            i++;
        }
        return getActions;
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
        /**
         * property - number Records.
         */
        private int numberRecords = 0;

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
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find all item's in tracker:", newLine, "-----", newLine);
            numberRecords = 0;
            for (Item item : tracker.findAll()) {
                numberRecords++;
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().length != 0) {
                    showComments(item);
                }
            }
            showSuccess();
        }

        @Override
        public void showSuccess() {
            if (numberRecords == 0) {
                System.out.printf("%s%s%s%s%s", "=>", newLine, "No records found.", newLine, "-----");
            } else {
                System.out.println("=>");
                System.out.println("The find all item's in tracker is successful.");
                System.out.println("-----");
            }
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

        /**
         * property - number Records.
         */
        private int numberRecords = 0;

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
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find item by name in tracker:", newLine, "-----", newLine);
            String key = input.ask("Enter key for find by name in Tracker:> ");
            System.out.println("-----");
            for (Item item : tracker.findByName(key)) {
                numberRecords++;
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().length != 0) {
                    showComments(item);
                }
            }
            showSuccess();
        }

        @Override
        public void showSuccess() {

            if (numberRecords == 0) {
                System.out.printf("%s%s%s%s%s", "=>", newLine, "No records found.", newLine, "-----");
            } else {
                System.out.println("=>");
                System.out.println("Find item by name in tracker is successful.");
                System.out.println("-----");
            }
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
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Add a new item in the tracker:", newLine, "-----", newLine);
            String nameItem = input.ask("Enter name Item :> ");
            String descreptionItem = input.ask("Enter descreption Item :> ");
            Long createItem = input.ask("Enter create Item in formating Long :> ", tracker.getMascCreate());
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
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Add a new comment by Item in the tracker:", newLine, "-----", newLine);
            int id = 0;
            boolean invalid = true;
            do {
                try {

                    String answer = input.ask("Enter id for find by id Item in Tracker or press 'm' return to menu:> ");
                    if (answer.equals("m")) {
                        System.out.println("Return menu.");
                        return;
                    }
                    id = Integer.parseInt(answer);
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again or 'm' to return to the menu.", newLine, "-----", newLine);
                }
            } while (invalid);
            Optional<Item> itemFindId = Optional.ofNullable(tracker.findById(id));
            if (!itemFindId.isPresent()) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
                return;
            }
            String commentInConsole = input.ask("Enter comment by Item :> ");
            Comment comment = new Comment(commentInConsole);
            itemFindId.get().addComment(comment);
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
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Delete item in tracker:", newLine, "-----", newLine);
            boolean invalid = true;
            int idItem = 0;
            do {
                try {
                    String answer = input.ask("Enter id Item delete or press 'm' for return to menu:> ");
                    if (answer.equals("m")) {
                        System.out.println("Return to menu.");
                        return;
                    }
                    idItem = Integer.parseInt(answer);
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, agayn or press 'm' to return to the menu.", newLine, "-----", newLine);
                }
            } while (invalid);
            if (tracker.findById(idItem) == null) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
                return;
            }
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