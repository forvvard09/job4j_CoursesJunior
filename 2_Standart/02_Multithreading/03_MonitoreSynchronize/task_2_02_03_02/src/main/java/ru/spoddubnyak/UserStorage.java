package ru.spoddubnyak;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Class UserStorage - Storage is saving Users.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.01.2018
 */
@ThreadSafe
public class UserStorage implements Storage<User> {
    /**
     * property -  storage for saving Users.
     */
    @GuardedBy("itself")  //@GuardedBy("itself")
    private final Set<User> usersSet;

    /**
     * Constructor - creates a new object UserStorage by specififcation.
     */
    UserStorage() {
        this.usersSet = new HashSet<>();
    }

    @Override
    public boolean add(User user) {
        synchronized (this.usersSet) {
            return this.usersSet.add(user);
        }
    }

    @Override
    public boolean update(User user) throws FindUserError {
        synchronized (this.usersSet) {
            boolean result = true;
            User userUpdate = getUser(user.getId());
            if (userUpdate == null) {
                throw new FindUserError("User not find in storage.");
            }
            userUpdate.setAmount(user.getAmount());
            return result;
        }
    }

    @Override
    public boolean delete(User user) {
        synchronized (this.usersSet) {
            return this.usersSet.remove(user);
        }
    }

    @Override
    public User getUser(int id) throws FindUserError {
        User userOut = null;
        synchronized (this.usersSet) {
            for (User user : this.usersSet) {
                if (user.getId() == id) {
                    userOut = user;
                    break;
                }
            }
            if (userOut == null) {
                throw new FindUserError("User not find in storage.");
            }
            return userOut;
        }
    }

    @Override
    public int getSize() {
        synchronized (this.usersSet) {
            return this.usersSet.size();
        }
    }

    /**
     * Method makes a write-off of the amount from the account of one user to the account of another user.
     *
     * @param fromId id for user one
     * @param toId   id for user two
     * @param amount number amount
     * @return - true, if operation true, false - if operation false
     */
    public boolean transfer(int fromId, int toId, int amount) {
        User userFrom = null;
        User userTo = null;
        boolean findSearch = false;
        boolean resultOperation = false;
        synchronized (this.usersSet) {
            for (User user : this.usersSet) {
                if (user.getId() == fromId) {
                    userFrom = user;
                }
                if (user.getId() == toId) {
                    userTo = user;
                }
                if (userFrom != null && userTo != null) {
                    findSearch = true;
                    break;
                }
            }
            if (findSearch && userFrom.getAmount() >= 0) {
                resultOperation = true;
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
            }
            return resultOperation;
        }
    }
}
