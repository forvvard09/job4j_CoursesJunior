package ru.spoddubnyak.model;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Abstract Class Parsers parsing and output to the screen on the job.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.02.2018
 */
public abstract class Parsers {
    /**
     * property for save path by file for parsing.
     */
    private static final String FILE_PATH = "D:/orders.xml";
    /**
     * property for a format print to empty value.
     */
    private static final String EMPTY_VALUE = "-----";
    /**
     * property for a format print to display.
     */
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * property Map for storage formating data and printing.
     */
    private final Map<String, AskBid> finalDataMap = new HashMap<>();
    /**
     * property allOrders for result parsing.
     */
    private Set<Order> allOrders = new HashSet<>();
    /**
     * property Map for storage all orders by algoritm for job.
     */
    private Map<String, BookOrder> ordersMap;
    /**
     * Obgect for storage final data.
     */
    private AskBid dataAskBid;

    /**
     * Getter for property FILE_PATH.
     *
     * @return path file for parsing
     */
    public String getFilePath() {
        return this.FILE_PATH;
    }

    /**
     * Getter for property allOrders.
     *
     * @return property allOrders
     */
    public final Set<Order> getAllOrders() {
        return this.allOrders;
    }

    /**
     * Method formating sorting Map of all olders.
     *
     * @param allOrders - all orders after pretreatment
     */
    private void formating(Set<Order> allOrders) {
        this.ordersMap = new HashMap<>();
        for (Order order : allOrders) {
            if (this.ordersMap.containsKey(order.getName())) {
                this.ordersMap.get(order.getName()).addOrder(order);
            } else if (!this.ordersMap.containsKey(order.getName())) {
                BookOrder bookOrder = new BookOrder();
                bookOrder.addOrder(order);
                this.ordersMap.put(order.getName(), bookOrder);
            }
        }
    }

    /**
     * Method formating data by algoritm job.
     *
     * @throws NoSuchElementException if not found elemeent in collections
     */
    private void formatingAskBid() throws NoSuchElementException {
        Iterator<String> mapIterator = this.ordersMap.keySet().iterator();
        while (mapIterator.hasNext()) {
            this.dataAskBid = new AskBid();
            String key = mapIterator.next().toString();
            this.finalDataMap.put(key, this.dataAskBid);
            BookOrder printCollections = this.ordersMap.get(key);
            Iterator<Order> buyIterator = printCollections.getBuyTree().iterator();
            Iterator<Order> sellIterator = printCollections.getSellTree().iterator();

            while (buyIterator.hasNext() || sellIterator.hasNext()) {
                Order buy;
                Order sell;
                if (!sellIterator.hasNext()) {
                    this.dataAskBid.getBid().add(buyIterator.next().toString());
                    this.dataAskBid.getAsk().add(this.EMPTY_VALUE);
                } else if (!buyIterator.hasNext()) {
                    this.dataAskBid.getAsk().add(sellIterator.next().toString());
                    this.dataAskBid.getBid().add(this.EMPTY_VALUE);
                } else if (buyIterator.hasNext() && sellIterator.hasNext()) {
                    buy = buyIterator.next();
                    sell = sellIterator.next();
                    if (buy.getPrice() <= sell.getPrice()) {
                        this.dataAskBid.getAsk().add(buy.toString());
                        this.dataAskBid.getBid().add(sell.toString());
                    }
                }
            }
        }
    }

    /**
     * Method print result.
     */
    private void printResult() {
        formatingAskBid();
        List<String> key = sortingByNameBook();
        for (int i = 0; i < key.size(); i++) {
            System.out.printf("%s      %s%s   %s ---> %s%s", this.NEW_LINE, key.get(i), this.NEW_LINE, "BID", "ASK", this.NEW_LINE);
            this.finalDataMap.get(key.get(i)).printAskBid();
        }
    }

    /**
     * Method sorting Map by name book for print.
     *
     * @return keyList sortng list, name book for print
     */
    private List<String> sortingByNameBook() {
        Iterator<String> mapIterator = this.finalDataMap.keySet().iterator();
        List<String> keyList = new ArrayList(this.finalDataMap.size());
        while (mapIterator.hasNext()) {
            keyList.add(mapIterator.next().toString());
        }
        Collections.sort(keyList);
        return keyList;
    }

    /**
     * Method start parsing and print result.
     *
     * @throws FileNotFoundException errors with xml file
     */
    public void toStart() throws FileNotFoundException {
        formating(parsingTo());
        printResult();
    }

    /**
     * abstartct method for parsing various methods .
     *
     * @return collections Set result parsing
     * @throws FileNotFoundException errors with xml file
     */
    public abstract Set<Order> parsingTo() throws FileNotFoundException;
}