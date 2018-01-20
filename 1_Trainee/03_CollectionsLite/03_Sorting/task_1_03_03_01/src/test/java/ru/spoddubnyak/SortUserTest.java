package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test class testing sorting in class SortUser class.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class SortUserTest {
    /**
     * property test object User.
     */
    private final User userOne = new User("Tom", 21);
    /**
     * property name User.
     */
    private final User userTwo = new User("John", 27);
    /**
     * property name User.
     */
    private final User userThree = new User("Viktor", 19);

    /**
     * property name User.
     */
    private final User userFour = new User("Vladimir", 21);

    /**
     * Auxiliary method for sorting array User through hash code.
     *
     * @param userList - list user
     * @return userList - sorting list user by hash code
     */
    private User[] sortingArrayByHashCode(User[] userList) {
        User minHash;
        for (int i = 0; i < userList.length; i++) {
            for (int j = i + 1; j < userList.length; j++) {
                if (userList[i].hashCode() > userList[j].hashCode()) {
                    minHash = userList[i];
                    userList[i] = userList[j];
                    userList[j] = minHash;
                }
            }
        }
        return userList;
    }

    /**
     * Test method sort class SortUser.
     */
    @Test
    public void whenNonSortedLIstOneThenGetSortedSet() {
        List<User> userList = new ArrayList<>();
        userList.add(userOne);
        userList.add(userTwo);
        userList.add(userThree);
        Set<User> userSortSet = new SortUser().sort(userList);
        User[] expectedResult = userSortSet.toArray(new User[userSortSet.size()]);
        assertThat(userList, containsInAnyOrder(expectedResult));
    }

    /**
     * Test method sort class SortUser.
     */
    @Test
    public void whenNonSortedLIstTwoThenGetSortedSet() {
        List<User> userList = new ArrayList<>();
        userList.add(userOne);
        userList.add(userTwo);
        userList.add(userThree);
        userList.add(userFour);
        Set<User> userSortSet = new SortUser().sort(userList);
        User[] expectedResult = userSortSet.toArray(new User[userSortSet.size()]);
        assertThat(userSortSet, containsInAnyOrder(expectedResult));
    }

    /**
     * Test method sort class SortUser, use comporator.
     */
    @Test
    public void whenNonSortedLIstTwoThenGetSortedListByLengthName() {
        List<User> userList = new ArrayList<>();
        userList.add(userThree);
        userList.add(userTwo);
        userList.add(userOne);
        userList.add(userFour);
        SortUser listUserSort = new SortUser();
        listUserSort.sortLength(userList);
        User[] expectedResult = userList.toArray(new User[userList.size()]);
        assertThat(userList, containsInAnyOrder(expectedResult));
    }

    /**
     * Test method sort class SortUser, use anonim class.
     */
    @Test
    public void whenNonSortedLIstTwoThenGetSortedListByLengthNameUseAnonimClass() {
        List<User> userList = new ArrayList<>();
        userList.add(userThree);
        userList.add(userTwo);
        userList.add(userOne);
        userList.add(userFour);
        SortUser listUserSort = new SortUser();
        listUserSort.sortLengthByAnonymous(userList);
        User[] expectedResult = userList.toArray(new User[userList.size()]);
        assertThat(userList, containsInAnyOrder(expectedResult));
    }

    /**
     * Test method sort by hash code class SortUser, use comporator.
     */
    @Test
    public void whenNonSortedLIstTwoThenGetSortedListByHashCode() {
        List<User> userList = new ArrayList<>();
        userList.add(userThree);
        userList.add(userTwo);
        userList.add(userOne);
        userList.add(userFour);
        SortUser listUserSort = new SortUser();
        listUserSort.sortHash(userList);
        User[] expectedResult = userList.toArray(new User[userList.size()]);
        sortingArrayByHashCode(expectedResult);
        assertThat(userList, containsInAnyOrder(expectedResult));
    }

    /**
     * Test method sort by hash code class SortUser, use anonim class.
     */
    @Test
    public void whenNonSortedLIstTwoThenGetSortedListByHashCodeUseAnonimClass() {
        List<User> userList = new ArrayList<>();
        userList.add(userThree);
        userList.add(userTwo);
        userList.add(userOne);
        userList.add(userFour);
        SortUser listUserSort = new SortUser();
        listUserSort.sortHashByAnonymous(userList);
        User[] expectedResult = userList.toArray(new User[userList.size()]);
        sortingArrayByHashCode(expectedResult);
        assertThat(expectedResult[0].hashCode(), is((userList.get(0).hashCode())));
        assertThat(expectedResult[1].hashCode(), is((userList.get(1).hashCode())));
        assertThat(expectedResult[2].hashCode(), is((userList.get(2).hashCode())));
        assertThat(expectedResult[expectedResult.length - 1].hashCode(), is((userList.get(userList.size() - 1).hashCode())));
        assertThat(userList.size(), is(expectedResult.length));
    }
}