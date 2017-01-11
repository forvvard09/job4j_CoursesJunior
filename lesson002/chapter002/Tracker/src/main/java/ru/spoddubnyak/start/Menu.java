package ru.spoddubnyak.start;

import java.util.Arrays;

/**
 * Class console menu .
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.12.2016
 */
public class Menu {
    /**
     * property - items menu.
     */
    private String[] menuActions = {
            "1: Add a new item in the tracker.",
            "2: Update item in tracker.",
            "3: Delete item in tracker.",
            "4: Find all item's in tracker.",
            "5: Find item by name in tracker.",
            "6: Find item by id in tracker.",
            "q: Quit to program.",
    };
    /**
     * property - greeting.
     */
    private String greeting;

    /** Getter property -  menuActions.
     * @return  property -  items menu
     */
    public String[] getMenuActions() {
        String[] getMenuAction = Arrays.copyOf(this.menuActions, this.menuActions.length);
        return getMenuAction;
    }

    /** Setter property -  menuActions.
     * @param   menuActions -  list record for menu
     */
    public void setMenuActions(String[] menuActions) {
        String[] copyMenuActions = Arrays.copyOf(menuActions, menuActions.length);
        this.menuActions = copyMenuActions;
    }

    /** Getter property -  greeting.
     * @return  property -  greeting
     */
    public String getGreeting() {
        return this.greeting;
    }

    /** Setter property -  greeting.
     * @param greeting - greeting.
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    /**
     * Method outputs on the console greeting.
     */
    public void showGreeting() {
        System.out.print(this.getGreeting());
        System.out.println(System.getProperty("line.separator"));
    }

    /**
     * Method outputs on the console a menu of possible actions.
     */
    public void showMenuActions() {
        for (String menuItems : this.menuActions) {
            System.out.println(menuItems);
        }
    }
}