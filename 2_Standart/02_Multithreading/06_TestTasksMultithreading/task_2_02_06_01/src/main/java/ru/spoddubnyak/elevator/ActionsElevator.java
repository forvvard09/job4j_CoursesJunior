package ru.spoddubnyak.elevator;

public abstract class ActionsElevator {

    /**
     * property - name action.
     */
    private String nameActions;

    /**
     * Constructor of BaseAction.
     *
     * @param nameActions name of action to show in menu
     */
    public ActionsElevator(String nameActions) {
        this.nameActions = nameActions;
    }

    /**
     * Abstract method of executing actions, that need to implement.
     *
     */
    public abstract void execute();

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
