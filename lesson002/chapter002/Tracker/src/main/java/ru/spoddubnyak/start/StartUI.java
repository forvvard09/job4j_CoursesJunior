package ru.spoddubnyak.start;

/**
 * Class point of entry into the program, initialization, interaction via the console.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 04.01.2017
 */
public class StartUI {
    /**
     * property -  use console out.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * property - the user interaction method.
     */
    private Input input;
    /**
     * property - the Tracker storage items.
     */
    private Tracker tracker;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param input   - interface class to communicate via the console
     * @param tracker - ithe Tracker storage items.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Static method point of entry the program.
     *
     * @param args - incoming parameters
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ValidateInput();
        new StartUI(input, tracker).init();
    }

    /**
     * Initialization method. Create object Tracker and Menu.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions("Hello! Welcome to the tracking program.");
        int[] range = menu.getActions();
        do {
            System.out.print(NEWLINE);
            menu.showMenu();
            System.out.print(NEWLINE);
            menu.select(this.input.ask("Select a menu item :> ", range));
            System.out.println();
        } while (!"q".equals(this.input.ask("To exit, press 'q' to proceed any other key :> ")));
    }
}