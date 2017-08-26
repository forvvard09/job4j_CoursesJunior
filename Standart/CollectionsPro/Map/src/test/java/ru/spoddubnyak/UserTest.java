package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.08.2017
 */
public class UserTest {
    /**
     * property name - name user.
     */
    private final String name = "testName";
    /**
     * property children - number children.
     */
    private final int children = 9;
    /**
     * property birthday - birthday user.
     */
    private final Calendar birthday = new GregorianCalendar(1990, 12, 21);

    /**
     * Test method create object User and get expected field.
     */
    @Test
    public void whenCreateConstructorObjectGetExpectedFieldObject() {
        User user = new User(name, children, birthday);
        assertThat(user.getName(), is(name));
        assertThat(user.getChildren(), is(children));
        assertThat(user.getBirthday(), is(birthday));
    }

    /**
     * Test do not override methods equals() and hashCode().
     */
    @Test
    public void whenAddMappElementsNotEqualsAndFashCodeGetExpectedResult() {
        User userOne = new User(name, children, birthday);
        User userTwo = new User(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(false));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(false));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, "One");
        map.put(userTwo, "One");
        System.out.println(map);
    }
}
