package ru.spoddubnyak;

/**
 * Class describes the user to save to the userStorage.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.01.2018
 */
public class User {
    /**
     * property -  minimum random number for random id.
     */
    private static final int MIN_RANDOM_ID = 1000;
    /**
     * property -  maximum random number for random id.
     */
    private static final int MAX_RANDOM_ID = 9999;
    /**
     * property -  id for User.
     */
    private int id;
    /**
     * property -  amount for User.
     */
    private int amount;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param id     id User's
     * @param amount amount User's
     */
    public User(int id, int amount) {
        this.id = id;
        if (amount < 0) {
            amount = 0;
        }
        this.amount = amount;
    }

    /**
     * Constructor it creates a new object with the specified values.
     */
    public User() {
        this.id = generationRandomId(MIN_RANDOM_ID, MAX_RANDOM_ID);
        this.amount = 0;
    }

    /**
     * Getter for property id.
     *
     * @return property id for user
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for property amount.
     *
     * @return property amount for user
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Setter for property amount.
     *
     * @param amount property amount for User's
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Method generate random id for user.
     *
     * @param minNumber minimum random number for random id.
     * @param maxNumber maximum random number for random id.
     * @return - return id
     */
    private int generationRandomId(final int minNumber, int maxNumber) {
        maxNumber -= minNumber;
        return (int) (Math.random() * ++maxNumber) + minNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
