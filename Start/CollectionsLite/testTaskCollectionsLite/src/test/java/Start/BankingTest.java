package start;

import errors.AccountException;
import errors.UsersException;
import model.Account;
import model.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class - testing class BankingTest.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class BankingTest {
    /**
     * property test testPassport.
     */
    private static final int PASSPORT = 123456790;
    /**
     * property test VALUE_TEST.
     */
    private static final double VALUE = 99.9;
    /**
     * property test REQUISITES_TEST.
     */
    private static final long REQUISITES = 1234567;
    /**
     * property test NAME_TEST.
     */
    private static final String NAME = "testName";

    /**
     * Test add new User in Map.
     * @throws UsersException - not find User in mapBanking
     */
    @Test
    public void whenAddNewUserThenGetNewUserInMap() throws UsersException {
        Banking banking = new Banking();
        User user = new User(NAME, PASSPORT);
        banking.addUser(user);
        assertThat(banking.getMapBanking().containsKey(user), is(true));
    }

    /**
     * Test error by add user = null.
     * @throws UsersException - not find User in mapBanking
     */
    @Test(expected = UsersException.class)
    public void whenAddNullUserThenErrorUsersException() throws UsersException {
        Banking banking = new Banking();
        User user = null;
        banking.addUser(user);
    }

    /**
     * Test remove user in Map.
     * @throws UsersException - not find User in mapBanking
     */
    @Test
    public void whenDeleteUserThenNotGetUserInMap() throws UsersException {
        Banking banking = new Banking();
        User user = new User(NAME, PASSPORT);
        banking.addUser(user);
        assertThat(banking.getMapBanking().containsKey(user), is(true));
        banking.deleteUser(user);
        assertThat(banking.getMapBanking().containsKey(user), is(false));
    }

    /**
     * Test error not find user for remove.
     * @throws UsersException - not find User in mapBanking
     */
    @Test(expected = UsersException.class)
    public void whenDeleteNotFindUserThenGetErrorUsersException() throws UsersException {
        Banking banking = new Banking();
        User userTwo = null;
        banking.deleteUser(userTwo);
    }

    /**
     * Test new account for user.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find a in mapBanking
     */
    @Test
    public void whenAddAccountUserThenGetAccountUser() throws UsersException, AccountException {
        boolean valid = false;
        Banking banking = new Banking();
        User user = new User(NAME, PASSPORT);
        banking.addUser(user);
        Account account = new Account(VALUE, REQUISITES);
        banking.addAccountToUser(user, account);
        List<Account> listAccount = banking.getUserAccounts(user);
        for (Account accountTemp : listAccount) {
            if (accountTemp.equals(account)) {
                valid = true;
            }
        }
        assertThat(valid, is(true));
    }

    /**
     * Test new account for non-existent user.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find a in mapBanking
     */
    @Test(expected = UsersException.class)
    public void whenAddAccountNotUserThenGetErrorUsersException() throws UsersException, AccountException {
        Banking banking = new Banking();
        User user = new User(NAME, PASSPORT);
        Account account = new Account(VALUE, REQUISITES);
        banking.addAccountToUser(user, account);
    }

    /**
     * Test new account for non-existent user.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find a in mapBanking
     */
    @Test(expected = AccountException.class)
    public void whenAddAccountNullUserThenGetErrorAccountException() throws UsersException, AccountException {
        Banking banking = new Banking();
        User user = new User(NAME, PASSPORT);
        Account account = null;
        banking.addAccountToUser(user, account);
    }

    /**
     * Test calculates possible transfer of money from one account to another.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find account in mapBanking
     */
    @Test
    public void whenValidTransferThenGetTrue() throws UsersException, AccountException {
        Banking banking = new Banking();
        User userOne = new User(NAME, PASSPORT);
        Account accountOne = new Account(VALUE, REQUISITES);
        User userTwo = new User(NAME, PASSPORT);
        Account accountTwo = new Account(VALUE, REQUISITES);
        banking.addUser(userOne);
        banking.addAccountToUser(userOne, accountOne);
        banking.addUser(userTwo);
        banking.addAccountToUser(userTwo, accountTwo);
        boolean valid = banking.transferMoney(userOne, accountOne, userTwo, accountTwo, VALUE);
        assertThat(valid, is(true));
    }

    /**
     * Test calculates possible transfer of money from one account to another.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find account in mapBanking
     */
    @Test
    public void whenNotValidTransferThenGetFalse() throws UsersException, AccountException {
        Banking banking = new Banking();
        User userOne = new User(NAME, PASSPORT);
        Account accountOne = new Account(VALUE, REQUISITES);
        User userTwo = new User(NAME, PASSPORT);
        Account accountTwo = new Account(VALUE, REQUISITES);
        banking.addUser(userOne);
        banking.addAccountToUser(userOne, accountOne);
        banking.addUser(userTwo);
        banking.addAccountToUser(userTwo, accountTwo);
        boolean valid = banking.transferMoney(userOne, accountOne, userTwo, accountTwo, VALUE * VALUE);
        assertThat(valid, is(false));
    }

    /**
     * Test calculates possible transfer of money from one account to another.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find account in mapBanking
     */
    @Test(expected = AccountException.class)
    public void whenNotAccountThenGetErrorAccountException() throws UsersException, AccountException {
        Banking banking = new Banking();
        User userOne = new User(NAME, PASSPORT);
        Account accountOne = new Account(VALUE, REQUISITES);
        User userTwo = new User(NAME, PASSPORT);
        Account accountTwo = null;
        banking.addUser(userOne);
        banking.addAccountToUser(userOne, accountOne);
        banking.addUser(userTwo);
        banking.transferMoney(userOne, accountOne, userTwo, accountTwo, VALUE * VALUE);
    }

    /**
     * Test calculates possible transfer of money from one account to another.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find account in mapBanking
     */
    @Test(expected = UsersException.class)
    public void whenNotAccountThenGetErrorUsersException() throws UsersException, AccountException {
        Banking banking = new Banking();
        User userOne = new User(NAME, PASSPORT);
        Account accountOne = new Account(VALUE, REQUISITES);
        User userTwo = new User(NAME, PASSPORT);
        Account accountTwo = new Account(VALUE, REQUISITES);
        final int testPassportError = 9999;
        User userThree = new User(NAME, testPassportError);
        banking.addUser(userOne);
        banking.addAccountToUser(userOne, accountOne);
        banking.addUser(userTwo);
        banking.transferMoney(userOne, accountOne, userThree, accountTwo, VALUE * VALUE);
    }

    /**
     * Test calculates possible transfer of money from one account to another.
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find account in mapBanking
     */
    @Test(expected = UsersException.class)
    public void whenNotAccountThenGetErrorUsersExceptionTwo() throws UsersException, AccountException {
        Banking banking = new Banking();
        User userOne = new User(NAME, PASSPORT);
        Account accountOne = new Account(VALUE, REQUISITES);
        User userTwo = new User(NAME, PASSPORT);
        Account accountTwo = new Account(VALUE, REQUISITES);
        final int testErrorPassport = 9999;
        User userThree = new User(NAME, testErrorPassport);
        banking.addUser(userOne);
        banking.addAccountToUser(userOne, accountOne);
        banking.addUser(userTwo);
        banking.transferMoney(userThree, accountOne, userOne, accountTwo, VALUE * VALUE);
    }
}
