package ru.spoddubnyak.start;

import ru.spoddubnyak.errors.MenuOutException;

/**
 * Test Class ValidateInput.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 11.02.2017
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Method ask and get an answer to a question and check with possible answers.
     *
     * @param question - question in console
     * @param range    - range of possible responses
     * @return - answer
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException exMoe) {
                //exMoe.printStackTrace();
                System.out.println("-----");
                System.out.println("Error. Please select key from menu.");
                System.out.println("-----");
            } catch (NumberFormatException exNfe) {
                System.out.println("-----");
                System.out.println("Please enter validate data again.");
                System.out.println("-----");
            }
        } while (invalid);
        return value;
    }

    /**
     * Method ask and get an answer to a question, checks no longer whether the resulting number is the maximum possible.
     *
     * @param question  - question in console
     * @param maxNumber - the maximum possible
     * @return - answer
     */
    public long ask(String question, long maxNumber) {
        boolean invalid = true;
        long value = 0L;
        do {
            try {
                value = Long.parseLong(super.ask(question));
                invalid = false;
            } catch (NumberFormatException exNfe) {
                System.out.println("-----");
                System.out.println("Please enter validate data again.");
                System.out.println("-----");
            }
            if (value > maxNumber) {
                invalid = true;
                System.out.printf("%s: %s%s", "You have entered incorrect data, the maximum number for this operation", maxNumber, System.getProperty("line.separator"));
                System.out.println("-----");
            }
        } while (invalid);
        return value;
    }
}

