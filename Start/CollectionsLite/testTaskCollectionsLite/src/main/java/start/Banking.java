package start;


import errors.AccountException;
import errors.UsersException;
import model.Account;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;



/**
 * Class start Banking - method for for working with user payments, User and Account.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.04.2017
 */
public class Banking {
    /**
     * property listAccount - storage accounts.
     */
    private List<Account> listAccount = new ArrayList<>(0);
    /**
     * property mapBanking - storage Users and Accounts.
     */
    private Map<User, List<Account>> mapBanking = new HashMap<User, List<Account>>();

    /**
     * Getter property mapBanking.
     *
     * @return Map - propery mapBanking
     */
    public Map<User, List<Account>> getMapBanking() {
        return this.mapBanking;
    }

    /**
     * Method Adds a new user.
     *
     * @param user - object User, new user
     * @throws UsersException - user = null;
     */
    public void addUser(User user) throws UsersException {
        if (user == null) {
            throw new UsersException("User must be created.");
        }
        this.mapBanking.put(user, listAccount);
    }

    /**
     * Method remove user.
     *
     * @param user - object User, user for delete
     * @throws UsersException - not find User in mapBanking
     */
    public void deleteUser(User user) throws UsersException {
        for (Iterator<User> iteraor = mapBanking.keySet().iterator(); iteraor.hasNext();) {
            if (user.equals(iteraor.next())) {
                iteraor.remove();
            } else {
                throw new UsersException("User is not found.");
            }
        }
    }

    /**
     * Method adds a new account for user.
     *
     * @param user    - object User, new user
     * @param account - object Account, new acount
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find a in mapBanking
     */
    public void addAccountToUser(User user, Account account) throws UsersException, AccountException {
        if (!(account == null))  {
            if (this.mapBanking.containsKey(user)) {
                this.mapBanking.get(user).add(account);
            } else {
                throw new UsersException("User is not found.");
            }
        } else {
            throw new AccountException("Account can not be null. ");
        }

    }

    /**
     * Method adds a new account for user.
     *
     * @param user    - object User, new user
     * @param account - object Account, new acount
     * @throws UsersException - not find User in mapBanking
     */
    public void deleteAccountFromUser(User user, Account account) throws UsersException {
        if (this.mapBanking.containsKey(user)) {
            this.mapBanking.get(user).remove(account);
        } else {
            throw new UsersException("User is not found.");
        }
    }

    /**
     * Method get list accounts for user.
     *
     * @param user - object User, user
     * @return resultList - list all accounts for user
     * @throws UsersException - not find User in mapBanking
     */
    public List<Account> getUserAccounts(User user) throws UsersException {
        List resultList;
        if (this.mapBanking.containsKey(user)) {
            resultList = this.mapBanking.get(user);
        } else {
            throw new UsersException("User is not found.");
        }
        return resultList;
    }

    /**
     * Method calculates possible transfer of money from one account to another.
     *
     * @param srcUser    - user from account
     * @param srcAccount - account to be transferred
     * @param dstUser    - the user whose account is to be paid
     * @param dstAccount - account to be transfer for
     * @param amount -  transfer amount
     * @return validTransfer - true transfer is possible, false is not possible
     * @throws UsersException - not find User in mapBanking
     * @throws AccountException - not find a in mapBanking
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) throws UsersException, AccountException {
        boolean validTransfer = false;
        if (this.mapBanking.containsKey(srcUser) || this.mapBanking.containsKey(dstUser)) {
            if (this.mapBanking.get(srcUser).contains(srcAccount) || this.mapBanking.get(srcUser).contains(dstAccount)) {
                if (srcAccount.getValue() - amount >= 0) {
                    validTransfer = true;
                }
            } else {
                throw new AccountException("One or a second account not found.");
            }
        } else {
            throw new UsersException("Translation is not possible, one of the users is not found.");
        }
        return validTransfer;
    }
}
