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
     * property - the user interaction method.
     */
    private Input input;

    /**
     * property - the Tracker storage items.
     */
    private Tracker tracker;

    /**
     * property -  use console out.
     */
    private String newLine = System.getProperty("line.separator");

    /** Constructor it creates a new object with the specified values.
     * @param input - interface class to communicate via the console
     * @param tracker - ithe Tracker storage items.
     *
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Static method point of entry the program.
     * @param args - incoming parameters
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(input, tracker).init();
    }

    /**
     * Initialization method. Create object Tracker and Menu.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        String answer;
        menu.fillActions("Hello! Welcome to the tracking program.");
        do {
            menu.showMenu();
            System.out.print(newLine);
            answer = this.input.ask("Select a menu item, to exit, press 'q' :> ");
            if (!answer.equals("q")) {
                menu.select(Integer.parseInt(answer) - 1);
            }
            System.out.println();
        } while (!answer.equals("q"));
    }
}