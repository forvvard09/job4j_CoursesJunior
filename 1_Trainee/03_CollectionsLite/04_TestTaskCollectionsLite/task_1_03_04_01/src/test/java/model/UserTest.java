package model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class - testing class User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class UserTest {
    /**
     * property test NAME_TEST.
     */
    private static final String NAME_TEST = "nameTest";
    /**
     * property test PASSPORT_TEST.
     */
    private static final int PASSPORT_TEST = 123456790;

    /**
     * Test create new object User through a constructor with empty values.
     */
    @Test
    public void whenConstructorNewUserObjectThenGetExpectedFields() {
        User user = new User(NAME_TEST, PASSPORT_TEST);
        assertThat(user.getName(), is(NAME_TEST));
        assertThat(user.getPassport(), is(PASSPORT_TEST));
    }

    /**
     * Test seters and getters objects User.
     */
    @Test
    public void whenSettersObjectThenExpectedGetters() {
        User user = new User(NAME_TEST, PASSPORT_TEST);
        String expectedName = "expectedName";
        final int expectedPassport = 1111222222;
        user.setName(expectedName);
        user.setPassport(expectedPassport);
        assertThat(user.getName(), is(expectedName));
        assertThat(user.getPassport(), is(expectedPassport));
    }

    /**
     * Test equals for User.
     */
    @Test
    public void whenNotEqualsThenExpectedFalse() {
        User user = new User(NAME_TEST, PASSPORT_TEST);
        assertThat(user.equals(null), is(false));
    }

    /**
     * Test equals for User.
     */
    @Test
    public void whenEqualsThenExpectedTrue() {
        User userOne = new User(NAME_TEST, PASSPORT_TEST);
        User userTwo = new User(NAME_TEST, PASSPORT_TEST);
        assertThat(userOne.equals(userTwo), is(true));
    }

    /**
     * Test equals for User.
     */
    @Test
    public void whenFieldPassportEqualsThenExpectedFalse() {
        User userOne = new User(NAME_TEST, PASSPORT_TEST);
        final int passportTest = 0;
        User userTwo = new User(NAME_TEST, passportTest);
        assertThat(userOne.equals(userTwo), is(false));
    }

    /**
     * Test hashCode for User.
     */
    @Test
    public void whenHashCodeThenExpectedresult() {
        User user = new User(null, 0);
        assertThat(user.hashCode(), is(0));
    }
}