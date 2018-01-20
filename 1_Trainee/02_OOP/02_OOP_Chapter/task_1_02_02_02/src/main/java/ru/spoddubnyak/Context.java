package ru.spoddubnyak;

/**
 * Context is a class which uses a Strategy.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.01.2017
 */
public class Context {
    /**
     * property - uses strategy.
     */
    private Strategy strategy;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param strategy - the strategy
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Method execute strategy, out in console sphere.
     *
     * @param side - side sphere
     */
    public void executeStrategy(int side) {
        strategy.draw(strategy.pic(side));
    }
}
