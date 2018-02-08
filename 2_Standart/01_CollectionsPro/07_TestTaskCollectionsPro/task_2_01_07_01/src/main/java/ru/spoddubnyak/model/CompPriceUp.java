package ru.spoddubnyak.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class comporator for sorting ordrs when adding in object BookOrder.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.02.2018
 */
public class CompPriceUp implements Comparator<Order>, Serializable {

    @Override
    public int compare(Order first, Order second) {
        int result;
        if (first.getOrderId() == second.getOrderId()) {
            result = 0;
        } else {
            double resultPrice = first.getPrice() - second.getPrice();
            if (0.0 == resultPrice) {
                second.setVolume(first.getVolume() + second.getVolume());
                result = 0;
            } else if (resultPrice > 0.0) {
                result = -1;
            } else {
                result = 1;
            }
        }
        return result;
    }
}
