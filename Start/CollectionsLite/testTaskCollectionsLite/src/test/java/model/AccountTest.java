package model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class - testing class Account.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class AccountTest {
    /**
     * property test VALUE_TEST.
     */
    private static final double VALUE_TEST = 99.9;
    /**
     * property test REQUISITES_TEST.
     */
    private static final long REQUISITES_TEST = 1234567;

    /**
     * Test create new object Account through a constructor with empty values.
     */
    @Test
    public void whenConstructorNewUserObjectThenGetExpectedFields() {
        Account account = new Account(VALUE_TEST, REQUISITES_TEST);
        assertThat(account.getValue(), is(VALUE_TEST));
        assertThat(account.getRequisites(), is(REQUISITES_TEST));
    }

    /**
     * Test seters and getters objects Account.
     */
    @Test
    public void whenSettersObjectThenExpectedGetters() {
        Account account = new Account(VALUE_TEST, REQUISITES_TEST);
        final double expectedValue = 11.1;
        final long expectedRequisites = 9876543;
        account.setValue(expectedValue);
        account.setRequisites(expectedRequisites);
        assertThat(account.getValue(), is(expectedValue));
        assertThat(account.getRequisites(), is(expectedRequisites));
    }
}