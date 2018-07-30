package ru.spoddubnyak.actions;


import java.util.ArrayList;


/**
 * Class show menu and execute actions.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017
 */
class MenuElevator {
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
     * property - items menu.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();


    /**
     * property - for use with the console.
     */
    private Input input;

    /**
     * Constructor it creates a new object with the specified values.
     */
    MenuElevator() {

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

        this.actions.add(position++, new CallIn(String.format("%s. %s", position, "Call in elevator.")));
        this.actions.add(position++, new CallOut(String.format("%s. %s", position, "Call out elevator")));
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
        //this.actions.get(key).execute(this.input, this.tracker);
    }

    class CallIn extends BaseAction {
        /**
         * Constructor of BaseAction.
         *
         * @param name name of action to show in menu
         */
        public CallIn(String name) {
            super(name);
        }

        @Override
        public void execute(Input input/*, Tracker tracker*/) {

        }

        @Override
        public void showSuccess() {

        }

        @Override
        public int key() {
            return 0;
        }
    }

    class CallOut extends BaseAction {
        /**
         * Constructor of BaseAction.
         *
         * @param name name of action to show in menu
         */
        public CallOut(String name) {
            super(name);
        }

        @Override
        public void execute(Input input/*Tracker tracker*/) {

        }

        @Override
        public void showSuccess() {

        }

        @Override
        public int key() {
            return 0;
        }
    }


}