package ru.spoddubnyak;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count - increment value use multitreading.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.01.2018
 */
@ThreadSafe
public class Count {
    /**
     * property -  count.
     */
    @GuardedBy("this")
    private int value;

    /**
     * Method increment value count.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Method return value count.
     *
     * @return value count
     */
    public synchronized int getValue() {
        return this.value;
    }
}
