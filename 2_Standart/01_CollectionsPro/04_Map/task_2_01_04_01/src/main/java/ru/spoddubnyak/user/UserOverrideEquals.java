package ru.spoddubnyak.user;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class User for learning purposes, override method hashCode().
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 29.08.2017
 */
public class UserOverrideEquals extends User {
    /**
     * Constructor it creates a new Object User with the specified values.
     *
     * @param name     - name for object User
     * @param children - number children
     * @param birthday - birth day
     */
    public UserOverrideEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        boolean valid = false;
        if (this == o) {
            result = true;
            valid = true;
        }
        if ((o == null || o.getClass() != this.getClass()) && !valid) {
            result = false;
            valid = true;
        }
        if (!(o instanceof UserOverrideEquals) && !valid) {
            result = false;
            valid = true;
        }
        if (!valid) {
            UserOverrideEquals user = (UserOverrideEquals) o;
            result = getChildren() == user.getChildren()
                    && Objects.equals(getName(), user.getName())
                    && Objects.equals(getBirthday(), user.getBirthday());
        }
        return result;
    }
}
