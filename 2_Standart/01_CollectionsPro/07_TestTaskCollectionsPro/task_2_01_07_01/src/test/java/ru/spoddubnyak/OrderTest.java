package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.model.Order;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Tests class Order.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 08.02.2018
 */
public class OrderTest {
    /**
     * Property test field orderId for testing.
     */
    private final int orderId = 99;
    /**
     * Property test field name for testing.
     */
    private final String name = "tetName";
    /**
     * Property test field operation for testing.
     */
    private final String operation = "testOperation";
    /**
     * Property test field volume for testing.
     */
    private final int volume = 5;
    /**
     * Property test field price for testing.
     */
    private final double price = 5.5;
    /**
     * New object Order for testing.
     */
    private Order order = new Order(this.orderId, this.name, this.operation, this.volume, this.price);

    /**
     * Test constructor - create new Order, getter.
     */
    @Test
    public void whenCreateNewOrderThenGetExpectedNewOrder() {
        assertThat(this.order == null, is(false));
        assertThat(this.order.getName(), is(this.name));
        assertThat(this.order.getOperation(), is(this.operation));
        assertThat(this.order.getOrderId(), is(this.orderId));
        assertThat(this.order.getVolume(), is(this.volume));
        assertThat(this.order.getPrice(), is(this.price));
    }
}
