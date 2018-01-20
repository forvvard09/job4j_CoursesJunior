package model;

/**
 * Class model User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.04.2017
 */
public class User {
    /**
     * property name - user  nama.
     */
    private String name;
    /**
     * property passport - user  passport.
     */
    private int passport;

    /**
     * Constructor it creates a new object User with the specified values.
     *
     * @param name     - set property name
     * @param passport - set property passport
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Getter property name.
     *
     * @return name - property name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter property passport.
     *
     * @return passport - property passport
     */
    public int getPassport() {
        return this.passport;
    }

    /**
     * Setter property passport.
     *
     * @param passport - property passport
     */
    public void setPassport(int passport) {
        this.passport = passport;
    }

    /**
     * Setter property name.
     *
     * @param name - property name
     */
    public void setName(String name) {
        this.name = name;
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

        if (passport != user.passport) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        final int tempNumber = 31;
        int result = name != null ? name.hashCode() : 0;
        result = tempNumber * result + passport;
        return result;
    }
}
