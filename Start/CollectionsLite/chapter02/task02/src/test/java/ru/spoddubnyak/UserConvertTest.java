package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.User;
import ru.spoddubnyak.UserConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class converts a list of users to a Map.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2017
 */
public class UserConvertTest {
    /**
     * test property -  id.
     */
    private final int idUserTwo = 2;

    /**
     * test property -  id.
     */
    private final int idUserThree = 3;

    /**
     * test property -  name.
     */
    private final String nameUserThree = "testName";

    /**
     * test property -  city.
     */
    private final String cityUserThree = "testCity";

    /**
     * test object User.
     */
    private User userOne = new User();

    /**
     * test object User.
     */
    private User userTwo = new User(idUserTwo);

    /**
     * test object User.
     */
    private User userThree = new User(idUserThree, nameUserThree, cityUserThree);

    /**
     * test List<User>.
     */
    private List<User> listUser = new ArrayList<>();

    /**
     * Method formating expected HashMap<Integer, User>.
     *
     * @return HashMap<Integer, User>
     */
    private HashMap<Integer, User> getExpectedResult() {
        HashMap<Integer, User> expectedHashMApUser = new HashMap<>();
        expectedHashMApUser.put(userOne.getId(), userOne);
        expectedHashMApUser.put(userTwo.getId(), userTwo);
        expectedHashMApUser.put(userThree.getId(), userThree);
        return expectedHashMApUser;
    }

    /**
     * Test method convert List<User> to HashMap<Integer, User>.
     */
    @Test
    public void whenConvertingListToHashMapThenGetHashMap() {
        listUser.add(userOne);
        listUser.add(userTwo);
        listUser.add(userThree);
        UserConvert convert = new UserConvert();
        assertThat(getExpectedResult(), is(convert.process(listUser)));
    }

    /**
     * Test method convert List<User> to HashMap<Integer, User>, dubbing keys.
     */
    @Test
    public void whenRemoveDuplicationIdThenGetHashMap() {
        final int expectedSizeHash = 2;
        listUser.add(userOne);
        listUser.add(userTwo);
        userThree.setId(userTwo.getId());
        listUser.add(userThree);
        UserConvert convert = new UserConvert();
        assertThat(getExpectedResult(), is(convert.process(listUser)));
        assertThat(expectedSizeHash, is(convert.process(listUser).size()));
    }
}
