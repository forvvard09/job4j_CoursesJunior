package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.user.User;
import ru.spoddubnyak.user.UserOverrideEquals;
import ru.spoddubnyak.user.UserOverrideEqualsHashCode;
import ru.spoddubnyak.user.UserOverrideHashCode;

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
    public void whenCreateConstructorObjectThenGetExpectedFieldObject() {
        User user = new User(name, children, birthday);
        assertThat(user.getName(), is(name));
        assertThat(user.getChildren(), is(children));
        assertThat(user.getBirthday(), is(birthday));
    }

    /**
     * Test do not override methods equals() and hashCode().
     */
    @Test
    public void whenAddMappElementsNotEqualsAndHashCodeThenGetExpectedResult() {
        User userOne = new User(name, children, birthday);
        User userTwo = new User(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(false));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(false));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, "One");
        map.put(userTwo, "One");
        System.out.println(map);
    }

    /**
     * Test override method hashCode().
     */
    @Test
    public void whenAddMappElementsOverrideHashCodeThenGetExpectedResult() {
        UserOverrideHashCode userOne = new UserOverrideHashCode(name, children, birthday);
        UserOverrideHashCode userTwo = new UserOverrideHashCode(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(false));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(true));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, "One");
        map.put(userTwo, "One");
        System.out.println(map);
    }

    /**
     * Test override method hashCode().
     */
    @Test
    public void whenAddMappElementsOverrideHashCodeThenGetNotEqualObjectExpectedResult() {
        UserOverrideHashCode userOne = new UserOverrideHashCode(null, children, birthday);
        UserOverrideHashCode userTwo = new UserOverrideHashCode(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(false));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(false));
    }

    /**
     * Test override method equals().
     */
    @Test
    public void whenAddMappElementsOverrideEqualsThenGetExpectedResult() {
        UserOverrideEquals userOne = new UserOverrideEquals(name, children, birthday);
        UserOverrideEquals userTwo = new UserOverrideEquals(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(true));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(false));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, "One");
        map.put(userTwo, "One");
        System.out.println(map);
    }

    /**
     * Test override method equals(), equals Object at null.
     */
    @Test
    public void whenEqualsObjectNullThenGetExpectedResult() {
        UserOverrideEquals userOne = new UserOverrideEquals(name, children, birthday);
        assertThat(userOne.equals(null), is(false));
    }

    /**
     * Test override method equals(), equals diffrent object.
     */
    @Test
    public void whenEqualsDifferentObjectGetExpectedResult() {
        UserOverrideEquals userOne = new UserOverrideEquals(name, children, birthday);
        assertThat(userOne.equals(userOne), is(true));
        assertThat(userOne.hashCode() == userOne.hashCode(), is(true));
    }

    /**
     * Test override methods hashcode() and equals().
     */
    @Test
    public void whenAddMappElementsOverrideEqualsHashCodeThenGetExpectedResult() {
        UserOverrideEqualsHashCode userOne = new UserOverrideEqualsHashCode(name, children, birthday);
        UserOverrideEqualsHashCode userTwo = new UserOverrideEqualsHashCode(name, children, birthday);
        assertThat(userOne.equals(userTwo), is(true));
        assertThat(userOne.hashCode() == userTwo.hashCode(), is(true));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, "One");
        map.put(userTwo, "One");
        System.out.println(map);
    }

    /**
     * Test override methods hashcode() and equals().
     */
    @Test
    public void whenEqualsNullthenExpectedResult() {
        UserOverrideEqualsHashCode userOne = new UserOverrideEqualsHashCode(name, children, birthday);
        assertThat(userOne.equals(null), is(false));
    }

    /**
     * Test override methods hashcode() and equals(), coparison hashcode one object.
     */
    @Test
    public void whenCoparisoHashCodeOneObjectThenGetExpectedResult() {
        UserOverrideEqualsHashCode userOne = new UserOverrideEqualsHashCode(name, children, birthday);
        assertThat(userOne.equals(userOne), is(true));
        assertThat(userOne.hashCode() == userOne.hashCode(), is(true));
    }
}