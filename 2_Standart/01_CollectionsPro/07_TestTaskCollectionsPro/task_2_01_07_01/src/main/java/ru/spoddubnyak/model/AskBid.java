package ru.spoddubnyak.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AskBid to store the result and display.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 06.02.2018
 */
public class AskBid {
    /**
     * property for a format print to display.
     */
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * property save order with operation buy.
     */
    private List<String> ask;
    /**
     * property save order with operation sell.
     */
    private List<String> bid;

    /**
     * Constructor it creates a new object AskBid container with the specified values.
     */
    public AskBid() {
        this.ask = new ArrayList<>();
        this.bid = new ArrayList<>();
    }

    /**
     * Getter for property ask.
     *
     * @return link property ask
     */
    public List<String> getAsk() {
        return ask;
    }

    /**
     * Getter for property bid.
     *
     * @return link property bid
     */
    public List<String> getBid() {
        return bid;
    }

    /**
     * Method to print ask and bid display.
     */
    public void printAskBid() {
        int length = ask.size() <= bid.size() ? ask.size() : bid.size();
        for (int i = 0; i < length; i++) {
            System.out.printf("%s --> %s%s", ask.get(i), bid.get(i), this.NEW_LINE);
        }
    }
}
