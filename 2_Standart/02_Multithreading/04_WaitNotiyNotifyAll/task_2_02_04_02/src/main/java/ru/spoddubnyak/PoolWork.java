package ru.spoddubnyak;

/**
 * Interafce PoolWork describes the necessary methods and constants.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 01.03.2018
 */
public interface PoolWork {

    /**
     * Method add work to queue for run .
     *
     * @param work - work for thread
     */
    void add(Work work);
}
