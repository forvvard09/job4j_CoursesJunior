package ru.spoddubnyak.start;

/**
 * Interface user interaction and performance of the action menu.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.01.2017
 */
public interface UserAction {
    /**
     * Method return number action.
     *
     * @return key - number action
     */
    int key();

    /**
     * Method execute action.
     *
     * @param input   - work with the console
     * @param tracker - storage Items
     */
    void execute(Input input, Tracker tracker);

    /**
     * Method out console  success message actions.
     */
    void showSuccess();

    /**
     * Method return information about action.
     *
     * @return key - number action
     */
    String showInfo();
}
