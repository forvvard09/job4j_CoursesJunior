package ru.spoddubnyak;

import java.util.Comparator;

/**
 * A helper class for implementing a search using comporator to hash code.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class UserHashComporator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.hashCode() - o2.hashCode();
    }
}
