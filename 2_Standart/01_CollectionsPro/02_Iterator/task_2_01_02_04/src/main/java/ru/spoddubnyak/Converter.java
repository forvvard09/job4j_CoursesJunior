package ru.spoddubnyak;

import java.util.Iterator;

/**
 * Interface Converter o traverse iterator iterators.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 28.04.2017
 */
public interface Converter {
    /**
     * The method of converting several iterators into one.
     * @param it - input iterators
     * @return - resultant iteraotor
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
