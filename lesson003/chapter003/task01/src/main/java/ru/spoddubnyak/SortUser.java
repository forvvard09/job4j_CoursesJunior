package ru.spoddubnyak;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class sorting User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class SortUser {

    /**
     * Method Sorts the list of users and returns it to TreeSet.
     *
     * @param userList - a list of users
     * @return Set - sorting set users
     */
    public Set<User> sort(List<User> userList) {
        Set<User> userThreeSet = new TreeSet<>();
        for (User user : userList) {
            userThreeSet.add(user);
        }
        return userThreeSet;
    }
}
