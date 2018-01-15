package ru.spoddunyak.models;

import org.junit.Test;
import ru.spoddunyak.errors.NoFindValueException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Store, RoleStore, UserStore.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.05.2017
 */
public class StoreTest {
    /**
     * property - Store<User>.
     */
    private Store<User> userStore = new UserStore();
    /**
     * property - Store<Role>.
     */
    private Store<Role> roleStore = new RoleStore();

    /**
     * Test method add objecte User and method get in array.
     *
     * @throws NoFindValueException if object not find in array
     */
    @Test
    public void whenAddStoreUserThenGetExpectedResult() throws NoFindValueException {
        User userOne = new User();
        userStore.add(userOne);
        assertThat(userOne, is(userStore.get(0)));
    }

    /**
     * Test method add object Role and method get in array.
     *
     * @throws NoFindValueException if object not find in array
     */
    @Test
    public void whenAddStoreRoleThenGetExpectedResult() throws NoFindValueException {
        Role roleOne = new Role();
        roleStore.add(roleOne);
        assertThat(roleOne, is(roleStore.get(0)));
    }

    /**
     * Test method add object User and method get in array.
     *
     * @throws NoFindValueException if object not find in array
     */
    @Test
    public void whenUpdateStoreUserThenGetExpectedResult() throws NoFindValueException {
        User userOne = new User();
        User userTwo = new User();
        User userThree = new User();
        userStore.add(userOne);
        userStore.add(userTwo);
        userStore.update(userOne, userThree);
        assertThat(userThree, is(userStore.get(0)));
        assertThat(userTwo, is(userStore.get(1)));
    }

    /**
     * Test method add object Role and method get in array.
     *
     * @throws NoFindValueException if object not find in array
     */
    @Test
    public void whenUpdateStoreRoleThenGetExpectedResult() throws NoFindValueException {
        Role roleOne = new Role();
        Role roleTwo = new Role();
        roleStore.add(roleOne);
        roleStore.update(roleOne, roleTwo);
        assertThat(roleTwo, is(roleStore.get(0)));
    }
}