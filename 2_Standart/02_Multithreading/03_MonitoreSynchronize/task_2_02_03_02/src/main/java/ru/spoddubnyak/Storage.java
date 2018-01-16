package ru.spoddubnyak;

/**
 * Interface describes the methods User storage.
 *
 * @param <User> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.01.2018
 */
public interface Storage<User> {

    /**
     * Method is adding new User in UserStorage.
     *
     * @param user - new User
     * @return - result - true - all ok, false - if not ok
     */
    boolean add(User user);

    /**
     * Method is updating User in UserStorage.
     *
     * @param user - User for updating
     * @return - result - true - all ok, false - if not ok
     * @throws FindUserError not find User in UserStorage
     */
    boolean update(User user) throws FindUserError;

    /**
     * Method is removing User out UserStorage.
     *
     * @param user - User for removing
     * @return - result - true - all ok, false - if not ok
     */
    boolean delete(User user);

    /**
     * Method return User by id.
     *
     * @param id - id user's
     * @return User user for find
     * @throws FindUserError not find User in UserStorage
     */
    User getUser(int id) throws FindUserError;

    /**
     * Method return count user's in userStorage.
     *
     * @return count user's in userStorage
     */
    int getSize();
}
