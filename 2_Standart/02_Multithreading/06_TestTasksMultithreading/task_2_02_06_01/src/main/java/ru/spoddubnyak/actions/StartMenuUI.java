package ru.spoddubnyak.actions;

import ru.spoddubnyak.elevator.MenuElevator;

public class StartMenuUI {
    /**
     * property -  use console out.
    */
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * property - the user interaction method.
     */
    private Input input;

    public StartMenuUI(Input input) {
        this.input = input;
    }

    public static void main(String[] args) {
        
        Input input = new ValidateInput();
        new StartMenuUI().init();
    }

    /**
     * Initialization method. Create object Tracker and Menu.
     */
    public void init() {
        MenuElevator menu = new MenuElevator();
        menu
        menu.fillActions("Hello! Welcome to the tracking program.");
        final int keysActionExit = 7;
        UserAction exitTracker = new BaseAction(String.format("%s. %s", keysActionExit + 1, "Exit tracker.")) {

            @Override
            public void execute(Input input, Tracker tracker) {
                showSuccess();
                tracker.exitTracker();
            }

            @Override
            public int key() {
                return keysActionExit;
            }

            @Override
            public void showSuccess() {
                System.out.println("Exit Tracker.");
            }


        };

        menu.addAction(exitTracker);
        int[] range = menu.getActions();
        do {
            System.out.print(NEWLINE);
            menu.showMenu();
            System.out.print(NEWLINE);
            menu.select(this.input.ask("Select a menu item :> ", range));
            System.out.println();
        } while (!"y".equals(this.input.ask("Exit Tracker? (y)Yes/(n)No :> ")));
    }
}
