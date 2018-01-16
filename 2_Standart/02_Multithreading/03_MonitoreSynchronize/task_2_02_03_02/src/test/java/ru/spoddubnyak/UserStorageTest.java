package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class UserStorage.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.01.2018
 */
public class UserStorageTest {
    /**
     * property -  for testing.
     */
    private final int amountTransfer = 25;
    /**
     * property -  storage users for testing.
     */
    private UserStorage userStorage = new UserStorage();
    /**
     * property -  array users for testing.
     */
    private User[] usersArray;

    /**
     * Method generation test data for testing.
     */
    @Before
    public void generationTestdata() {
        final int idUserOne = 1;
        final int idUserTwo = 2;
        final int idUserThree = 3;
        final int amountOne = 150;
        final int amountTwo = 100;
        final int amountThree = 50;
        this.usersArray = new User[]{new User(idUserOne, amountOne), new User(idUserTwo, amountTwo), new User(idUserThree, amountThree)};
        for (int i = 0; i < this.usersArray.length; i++) {
            userStorage.add(this.usersArray[i]);
        }
    }

    /**
     * Testing method add.
     *
     * @throws FindUserError user not find in storage.
     */
    @Test
    public void whenAddUserssThenGetExpectedResult() throws FindUserError {
        final int countUsers = 2;
        final int idUser = 1;
        final int idAmount = 50;
        User userOne = new User(idUser, idAmount);
        User userTwo = new User();
        int id = userTwo.getId();
        UserStorage storage = new UserStorage();
        storage.add(userOne);
        storage.add(userTwo);
        assertThat(storage.getSize(), is(2));
        assertThat(storage.getUser(id).getAmount(), is(0));
    }

    /**
     * Testing method transfer.
     *
     * @throws FindUserError user not find in storage.
     */
    @Test
    public void whenTransferAmountThenGetExpectedResult() throws FindUserError {
        generationTestdata();
        final int expectedResultAmount = 125;
        final int expectedNuberUsers = 3;
        boolean result = this.userStorage.transfer(1, 2, this.amountTransfer);
        assertThat(result, is(true));
        assertThat(this.userStorage.getUser(1).getAmount(), is(expectedResultAmount));
        assertThat(this.userStorage.getUser(2).getAmount(), is(expectedResultAmount));
        assertThat(this.userStorage.getSize(), is(expectedNuberUsers));
    }

    /**
     * Testing method delete.
     */
    @Test
    public void whenDeletingUserThenGetExpectedResult() {
        generationTestdata();
        final int expectedResultOne = 3;
        final int expectedResultTwo = 2;
        assertThat(this.userStorage.getSize(), is(expectedResultOne));
        boolean result = this.userStorage.delete(this.usersArray[0]);
        assertThat(result, is(true));
        assertThat(this.userStorage.getSize(), is(expectedResultTwo));
    }

    /**
     * Testing method updating.
     *
     * @throws FindUserError user not find in storage.
     */
    @Test
    public void whenEditAmountForUserThenGetExpectedResult() throws FindUserError {
        generationTestdata();
        final int getAmount = this.usersArray[0].getAmount();
        final int getid = this.usersArray[0].getId();
        final int amountUpdate = 666;
        User newUser = new User(getid, amountUpdate);
        assertThat(getAmount == amountUpdate, is(false));
        boolean result = this.userStorage.update(newUser);
        assertThat(result, is(true));
        assertThat(this.userStorage.getUser(getid).getAmount(), is(amountUpdate));
    }

    /**
     * Testing error not find User in storage .
     *
     * @throws FindUserError user not find in storage.
     */
    @Test(expected = FindUserError.class)
    public void whenFindNoSuchUserThenGetError() throws FindUserError {
        User user = new User();
        boolean result = this.userStorage.update(user);
        assertThat(result, is(false));
    }

    /**
     * Testing method transfer with unsuccessful input data.
     */
    @Test
    public void whenNuSuchUserTransferThenExpectedResponse() {
        generationTestdata();
        final int id = 999;
        final int amountTransfer = 10;
        boolean result = this.userStorage.transfer(this.usersArray[0].getId(), id, amountTransfer);
        assertThat(result, is(false));
    }

    /**
     * Testing constructor, negative amount while creating new User.
     *
     * @throws FindUserError user not find in storage.
     */
    @Test
    public void whenNegativeAmountThenGetAmountZero() throws FindUserError {
        final int id = 999;
        final int amountTransfer = -10;
        final int expectedAmount = 0;
        UserStorage storage = new UserStorage();
        boolean result = storage.add(new User(id, amountTransfer));
        assertThat(result, is(true));
        assertThat(storage.getUser(id).getAmount(), is(expectedAmount));
    }

    /**
     * Testing method add new user in two thread.
     *
     * @throws InterruptedException errors thread
     * @throws FindUserError        user not find in storage.
     */
    @Test
    public void whenAddTwoThreadThenGetExpectedResult() throws InterruptedException, FindUserError {
        final int idOne = 11;
        final int idTwo = 22;
        final int amountOne = 10;
        final int amountTwo = 50;
        final int expectedCount = 2;
        final int timer = 333;
        UserStorage storage = new UserStorage();
        assertThat(storage.getSize(), is(0));

        //anonimous class - first thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(idOne, amountOne));
            }
        }).start();

        //anonimous class - second thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(idTwo, amountTwo));
            }
        }).start();
        Thread.sleep(timer);
        assertThat(storage.getSize(), is(expectedCount));
        assertThat(storage.getUser(idOne).getAmount(), is(amountOne));
        assertThat(storage.getUser(idTwo).getAmount(), is(amountTwo));
    }
}

