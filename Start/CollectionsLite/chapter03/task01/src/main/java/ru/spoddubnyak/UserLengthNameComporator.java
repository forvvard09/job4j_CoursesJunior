package ru.spoddubnyak;

import java.util.Comparator;

/**
 * A helper class for implementing a search using comporator to length name code.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class UserLengthNameComporator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getName().length() - o2.getName().length();
    }
}
