package ru.spoddubnyak.start;

import ru.spoddubnyak.models.Comment;
import ru.spoddubnyak.models.Item;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Internal class Execute actions "Update item in tracker.".
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017
 */
class EditItem extends BaseAction {

    /**
     * property - number action.
     */
    private static final int ACTION = 1;

    /**
     * Constructor of EditItem.
     *
     * @param name name of action to show in menu
     */
    EditItem(String name) {
        super(name);
    }

    @Override
    public int key() {
        return ACTION;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String newLine = System.getProperty("line.separator");
        boolean invalid = true;
        System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Update item in tracker:", newLine, "-----", newLine);
        int idItem = 0;
        do {
            try {
                String answer = input.ask("Enter id Item or press 'm' to exit:> ");
                if (answer.equals("m")) {
                    return;
                }
                idItem = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again.", newLine, "-----", newLine);
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
class FindById extends BaseAction {

    /**
     * property - number action.
     */
    private static final int ACTION = 5;

    /**
     * property - carriage shift.
     */
    private String newLine = System.getProperty("line.separator");

    /**
     * Constructor of FindById.
     *
     * @param name name of action to show in menu
     */
    FindById(String name) {
        super(name);
    }

    @Override
    public int key() {
        return ACTION;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find item by id in tracker:", newLine, "-----", newLine);
        int id = 0;
        boolean invalid = true;
        do {
            try {
                String answer = input.ask("Enter id for find by id Item in Tracker, or press 'm' to exit:> ");
                if (answer.equals("m")) {
                    System.out.println("Return menu.");
                    return;
                }
                id = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again.", newLine, "-----", newLine);
            }
        } while (invalid);
        Optional<Item> itemFindId = Optional.ofNullable(tracker.findById(id));
        if (!itemFindId.isPresent()) {
            System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
            return;
        } else {
            System.out.println("-----");
            System.out.printf("%s--%s--%s--%s%s", itemFindId.get().getId(), itemFindId.get().getName(), itemFindId.get().getDescription(), itemFindId.get().getCreate(), System.getProperty("line.separator"));
            if (itemFindId.get().getComments().size() != 0) {
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
class MenuTracker {
    /**
     * property - newLine.
     */
    private static String newLine = System.getProperty("line.separator");
    /**
     * property - greeting.
     */
    private String greeting;

    /**
     * property - position action in menu action.
     */
    private int position = 0;

    /**
     * property - specimen Tracker.
     */
    private Tracker tracker;
    /**
     * property - items menu.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();


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
    MenuTracker(Input input, Tracker tracekr) {
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
     * Geter get number actions.
     *
     * @return number actions
     */
    public int[] getActions() {
        int[] getActions = new int[this.actions.size()];
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

        this.actions.add(position++, new MenuTracker.AddItem(String.format("%s. %s", position, "Add a new item in the tracker.")));
        this.actions.add(position++, new EditItem(String.format("%s. %s", position, "Update item in tracker.")));
        this.actions.add(position++, new MenuTracker.DeleteItem(String.format("%s. %s", position, "Delete item in tracker.")));
        this.actions.add(position++, new FindAllItems(String.format("%s. %s", position, "Find all item's in tracker.")));
        this.actions.add(position++, new MenuTracker.FinByName(String.format("%s. %s", position, "Find item by name in tracker.")));
        this.actions.add(position++, new FindById(String.format("%s. %s", position, "Find item by id in tracker.")));
        this.actions.add(position++, new MenuTracker.AddCommentItem(String.format("%s. %s", position, "Add a new comment in Item.")));
    }

    /**
     * Method adds a new action to the menu.
     *
     * @param actoin - adds a new action to the menu
     */
    public void addAction(UserAction actoin) {
        this.actions.add(position++, actoin);
    }

    /**
     * Method execute actions bu key.
     *
     * @param key - number actions
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Internal static class Execute actions "Find all item's in tracker.".
     *
     * @author Sergei Poddubnyak (forvvard09@gmail.com)
     * @version 1.0
     * @since 19.01.2017
     */
    private static class FindAllItems extends BaseAction {
        /**
         * property - number action.
         */
        private static final int ACTION = 3;
        /**
         * property - number Records.
         */
        private int numberRecords = 0;

        /**
         * Constructor of FindAllItems.
         *
         * @param name name of action to show in menu
         */
        private FindAllItems(String name) {
            super(name);
        }

        @Override
        public int key() {
            return ACTION;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find all item's in tracker:", newLine, "-----", newLine);
            numberRecords = 0;
            for (Item item : tracker.findAll()) {
                numberRecords++;
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().size() != 0) {
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
    private static class FinByName extends BaseAction {

        /**
         * property - number action.
         */
        private static final int ACTION = 4;
        /**
         * property - number Records.
         */
        private int numberRecords = 0;

        /**
         * Constructor of FindByName.
         *
         * @param name name of action to show in menu
         */
        private FinByName(String name) {
            super(name);
        }

        @Override
        public int key() {
            return ACTION;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Find item by name in tracker:", newLine, "-----", newLine);
            String key = input.ask("Enter key for find by name in Tracker:> ");
            System.out.println("-----");
            for (Item item : tracker.findByName(key)) {
                numberRecords++;
                System.out.printf("%s--%s--%s--%s%s", item.getId(), item.getName(), item.getDescription(), item.getCreate(), newLine);
                if (item.getComments().size() != 0) {
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
    private static class AddItem extends BaseAction {

        /**
         * property - number action.
         */
        private static final int ACTION = 0;

        /**
         * Constructor of AddItem.
         *
         * @param name name of action to show in menu
         */
        private AddItem(String name) {
            super(name);
        }

        @Override
        public int key() {
            return ACTION;
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
    private static class AddCommentItem extends BaseAction {
        /**
         * property - number action.
         */
        private static final int ACTION = 6;

        /**
         * Constructor of AddCommentItem.
         *
         * @param name name of action to show in menu
         */
        private AddCommentItem(String name) {
            super(name);
        }

        @Override
        public int key() {
            return ACTION;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Add a new comment by Item in the tracker:", newLine, "-----", newLine);
            int id = 0;
            boolean invalid = true;
            do {
                try {

                    String answer = input.ask("Enter id for find by id Item in Tracker, or press 'm' to exit:> ");
                    if (answer.equals("m")) {
                        return;
                    }
                    id = Integer.parseInt(answer);
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again.", newLine, "-----", newLine);
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
    private static class DeleteItem extends BaseAction {
        /**
         * property - number action.
         */
        private static final int ACTION = 2;

        /**
         * Constructor of DeleteItem.
         *
         * @param name name of action to show in menu
         */
        private DeleteItem(String name) {
            super(name);
        }

        @Override
        public int key() {
            return ACTION;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.printf("%s%s%s%s%s%s", "-----", newLine, "Delete item in tracker:", newLine, "-----", newLine);
            boolean invalid = true;
            int idItem = 0;
            do {
                try {
                    String answer = input.ask("Enter id Item delete, or press 'm' to exit:> ");
                    if (answer.equals("m")) {
                        return;
                    }
                    idItem = Integer.parseInt(answer);
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.printf("%s%s%s%s%s%s", "=>", newLine, "You entered incorrect data, again.", newLine, "-----", newLine);
                }
            } while (invalid);
            if (tracker.findById(idItem) == null) {
                System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Items with not found.", newLine, "-----", newLine);
                return;
            }
            if (tracker.findById(idItem).getComments().size() != 0) {
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