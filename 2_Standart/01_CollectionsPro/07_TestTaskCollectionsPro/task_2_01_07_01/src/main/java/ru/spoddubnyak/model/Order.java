package ru.spoddubnyak.model;

/**
 * Class Order parsing new order of file.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.02.2018
 */
public class Order {
    /**
     * property id order.
     */
    private int orderId;
    /**
     * property name book in order.
     */
    private String name;
    /**
     * property operation order.
     */
    private String operation;
    /**
     * property volume in order.
     */
    private int volume;
    /**
     * property prive in order.
     */
    private double price;

    /**
     * Constructor it creates a new object Order container with the specified.
     *
     * @param orderId   id order
     * @param name      - name book in order
     * @param operation - operation in order
     * @param volume    - volume in order
     * @param price     - price in order
     */
    public Order(int orderId, String name, String operation, int volume, double price) {
        this.orderId = orderId;
        this.name = name;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
    }

    /**
     * Constructor it creates a new object Order container with the specified.
     *
     * @param operation - operation in order
     * @param price     - price in order
     * @param volume    - volume in order
     */
    public Order(String operation, double price, int volume) {
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Constructor it creates a new object Order container with the specified.
     *
     * @param orderId id order
     * @param name    - name book in order
     */
    public Order(int orderId, String name) {
        this.orderId = orderId;
        this.name = name;
    }

    /**
     * Constructor it creates a new object Order container with the specified.
     *
     * @param orderId id order
     * @param price   - price in order
     * @param volume  - volume in order
     */
    public Order(int orderId, double price, int volume) {
        this.orderId = orderId;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Getter for property price.
     *
     * @return link property price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Getter for property orderId.
     *
     * @return link property orderId
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * Getter for property volume.
     *
     * @return link property volume
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Setter for property volume.
     *
     * @param volume property volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Getter for property name.
     *
     * @return link property name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for property operation.
     *
     * @return link property operation
     */
    public String getOperation() {
        return this.operation;
    }


    @Override
    public String toString() {
        String result = volume + "@" + price;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (orderId != order.orderId) {
            return false;
        }
        return name != null ? name.equals(order.name) : order.name == null;
    }

    @Override
    public int hashCode() {
        final int index = 31;
        int result = orderId;
        result = index * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}