package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
     * Test method create object User and get expected field.
     */
    @Test
    public void whenCreateConstructorObjectGetExpectedFieldObject() {
        final String name = "testName";
        final int children = 9;
        final Calendar birthday = new GregorianCalendar(1990, 12, 21);
        User user = new User(name, children, birthday);
        assertThat(user.getName(), is(name));
        assertThat(user.getChildren(), is(children));
        assertThat(user.getBirthday(), is(birthday));
    }
}
