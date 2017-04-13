package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * Test method sort class SortUser.
     */
    @Test
    public void whenNonSortedLIstOneThenGetSortedSet() {
        List<User> userList = new ArrayList<>();
        userList.add(userOne);
        userList.add(userTwo);
        userList.add(userThree);
        Set<User> userSortSet = new SortUser().sort(userList);
        User[] espectedResult = userSortSet.toArray(new User[userSortSet.size()]);
        assertThat(espectedResult[0].getAge(), is(userThree.getAge()));
        assertThat(espectedResult[1].getAge(), is(userOne.getAge()));
        assertThat(espectedResult[2].getAge(), is(userTwo.getAge()));
        assertThat(userList.size(), is(espectedResult.length));
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
        User[] espectedResult = userSortSet.toArray(new User[userSortSet.size()]);
        for (User user : espectedResult) {
            System.out.println(String.format("%s %s", user.getName(), user.getAge()));
        }
        assertThat(espectedResult[0].getAge(), is(userThree.getAge()));
        assertThat(espectedResult[1].getAge(), is(userOne.getAge()));
        assertThat(espectedResult[2].getAge(), is(userTwo.getAge()));
        assertThat(userList.size() - 1, is(espectedResult.length));
    }
}


