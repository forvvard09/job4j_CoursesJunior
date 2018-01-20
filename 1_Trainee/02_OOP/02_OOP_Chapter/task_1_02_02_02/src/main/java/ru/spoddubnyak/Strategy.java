package ru.spoddubnyak;

/**
 * Interface class the formation and display shape.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.01.2017
 */
public interface Strategy {

    /**
     * Method pic and get an answer to a question.
     *
     * @param side - side length shape
     * @return - shape
     */
    String pic(int side);

    /**
     * Method display shape.
     *
     * @param shape - shape
     */
    void draw(String shape);
}