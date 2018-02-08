package ru.spoddubnyak.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Class BookOrder to store the all orders after removal.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.02.2018
 */
public class BookOrder {
    /**
     * property save order with operation buy.
     */
    private Set<Order> buyTree;
    /**
     * property save order with operation sell.
     */
    private Set<Order> sellTree;

    /**
     * Constructor it creates a new object BookOrder container with the specified values and sorting by comporator compUp.
     */
    public BookOrder() {
        CompPriceUp compUp = new CompPriceUp();
        this.buyTree = new TreeSet(compUp);
        this.sellTree = new TreeSet(compUp).descendingSet();
    }

    /**
     * Getter for property BueTree.
     *
     * @return link property BueTree
     */
    public Set<Order> getBuyTree() {
        return this.buyTree;
    }

    /**
     * getter for property SellTree.
     *
     * @return link property SellTree
     */
    public Set<Order> getSellTree() {
        return this.sellTree;
    }

    /**
     * Method add order in object BookOrder, depending on the operation of Order operation.
     *
     * @param order order for adding
     */
    public void addOrder(Order order) {
        if (order.getOperation().equals(XMLAttributes.OPERATION_SELL.getAtributes())) {
            this.sellTree.add(generationNewOrder(order));
        } else if (order.getOperation().equals(XMLAttributes.OPERATION_BUY.getAtributes())) {
            this.buyTree.add(generationNewOrder(order));
        }
    }

    /**
     * Method generate new Order.
     *
     * @param order for create new order's
     * @return new order by specification
     */
    private Order generationNewOrder(Order order) {
        int orderId = order.getOrderId();
        double price = order.getPrice();
        int volume = order.getVolume();
        Order newOrder = new Order(orderId, price, volume);
        return newOrder;
    }
}
