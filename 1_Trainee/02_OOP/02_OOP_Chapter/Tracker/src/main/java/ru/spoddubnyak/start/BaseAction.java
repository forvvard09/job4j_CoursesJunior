package ru.spoddubnyak.start;

/**
 * Abstract class for actions.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.2.2017
 */
public abstract class BaseAction implements UserAction {

    /**
     * property - name action.
     */
    private String nameActions;

    /**
     * Constructor of BaseAction.
     *
     * @param name name of action to show in menu
     */
    public BaseAction(String name) {
        this.nameActions = name;
    }

    /**
     * Abstract method of executing actions, that need to implement.
     *
     * @param input   - work with the console
     * @param tracker - storage Items
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Abstract method key of action.
     *
     * @return - number Item pf menu
     */
    public abstract int key();

    /**
     * Method for getting info for items in menu.
     *
     * @return - formatted string of item menu
     */
    public String showInfo() {
        return String.format(this.nameActions);
    }
}
