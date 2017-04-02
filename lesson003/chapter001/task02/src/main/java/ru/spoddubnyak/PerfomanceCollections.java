package ru.spoddubnyak;


import java.util.Collection;
import java.util.Iterator;

/**
 * Class adding and removing entries from collections is required for further performance testing Collections: LinkedList, ArrayList, TreeSet.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.04.2017
 */
public class PerfomanceCollections {

    /**
     * property - startTime for to determine the initial measurement time.
     */
    private long startTime;

    /**
     * Method adds a line to the collection.
     *
     * @param collection - used collection
     * @param line       - added template string
     * @param amount     - number of line
     * @return - elapsed time in milliseconds
     */
    public long add(Collection<String> collection, String line, int amount) {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.format("%s - number %s", line, i));
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Method remove a line to the collection.
     *
     * @param collection - used collection
     * @param amount     - number of line
     * @return - elapsed time in milliseconds
     */
    public long delete(Collection<String> collection, int amount) {
        startTime = System.currentTimeMillis();
        Iterator iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            if (iterator.hasNext()) {
                iterator.remove();
                iterator.next();
            }
        }
        return System.currentTimeMillis() - startTime;
    }
}
