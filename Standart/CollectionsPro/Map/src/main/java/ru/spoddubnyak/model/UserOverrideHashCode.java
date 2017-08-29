package ru.spoddubnyak.model;

import java.util.Calendar;

/**
 * Class User for learning purposes, override method hashCode().
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 29.08.2017
 */
public class UserOverrideHashCode extends User {
    /**
     * Constructor it creates a new Object User with the specified values.
     *
     * @param name     - name for object User
     * @param children - number children
     * @param birthday - birth day
     */
    public UserOverrideHashCode(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        final int koefTwo = 17;
        final int koef = 31;
        int result = koefTwo;
        result = koef * result + (getName() != null ? getName().hashCode() : 0);
        result = koef * result + getChildren();
        result = koef * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
