package ru.spoddubnyak;


import java.util.*;


/**
 * Class sorting User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class SortUser {

    /**
     * Method Sorts the list of users by age and returns it to TreeSet.
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

    /**
     * Method Sorts the list of users by hash code  and returns it to List.
     *
     * @param userList - a list of users
     * @return List - sorting list users
     */
    public List<User> sortHash(List<User> userList) {
        Collections.sort(userList, new UserHashComporator());
        return userList;

    }

    /**
     * Method Sorts the list of users by length name users and returns it to List.
     *
     * @param userList - a list of users
     * @return List - sorting list users
     */
    public List<User> sortLength(List<User> userList) {
        Collections.sort(userList, new UserLengthNameComporator());
        return userList;
    }

    /**
     * Method Sorts the list of users by length name, using an anonymous class  and returns it to sort List.
     *
     * @param userList - a list of users
     * @return List - sorting list users
     */
    public List<User> sortLengthByAnonymous(List<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return userList;
    }

    /**
     * Method Sorts the list of users by hash code, using an anonymous class  and returns it to sort List.
     *
     * @param userList - a list of users
     * @return List - sorting list users
     */
    public List<User> sortHashByAnonymous(List<User> userList) {
        Collections.sort(userList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                User user1 = (User) o1;
                User user2 = (User) o2;
                return user1.hashCode() - user2.hashCode();
            }
        });
        return userList;
    }
}
