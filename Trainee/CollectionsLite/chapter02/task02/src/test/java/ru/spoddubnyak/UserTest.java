package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class - testing class User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2017
 */
public class UserTest {
    /**
     * property - TEST_NAME.
     */
    static final String TEST_NAME = "name";
    /**
     * property - TEST_CITY.
     */
    static final String TEST_CITY = "city";
    /**
     * property - TEST_ID.
     */
    private static final int TEST_ID = 99;
    /**
     * property - new object User for testing.
     */
    private User userOne = new User();

    /**
     * Test create new object User through a constructor with empty values.
     */
    @Test
    public void whenConstructorNotNewUserThenGetIdOne() {
        User userTwo = new User();
        final int expectedId = userTwo.getId() != 0 ? userTwo.getId() : 0;
        assertThat(userTwo.getId(), is(expectedId));
    }

    /**
     * Test create new object User through the designer with the specified id.
     */
    @Test
    public void whenConstructorIdNewUserThenGetIdOne() {
        User userTwo = new User(TEST_ID);
        assertThat(userTwo.getId(), is(TEST_ID));
    }

    /**
     * Test create new object User through the designer with the specified: id, name, city.
     */
    @Test
    public void whenConstructorAllNewUserThenGetIdTwo() {
        User userTwo = new User(TEST_ID, TEST_NAME, TEST_CITY);
        assertThat(userTwo.getId(), is(TEST_ID));
        assertThat(userTwo.getName(), is(TEST_NAME));
        assertThat(userTwo.getCity(), is(TEST_CITY));
    }

    /**
     * Test setter and getter property id.
     */
    @Test
    public void whenSetIdThenGetId() {
        userOne.setId(TEST_ID);
        assertThat(userOne.getId(), is(TEST_ID));
    }

    /**
     * Test setter and getter property name.
     */
    @Test
    public void whenSetNameThenGetName() {
        userOne.setName(TEST_NAME);
        assertThat(userOne.getName(), is(TEST_NAME));
    }

    /**
     * Test setter and getter property city.
     */
    @Test
    public void whenSetCityThenGetCity() {
        userOne.setCity(TEST_CITY);
        assertThat(userOne.getCity(), is(TEST_CITY));
    }
}
