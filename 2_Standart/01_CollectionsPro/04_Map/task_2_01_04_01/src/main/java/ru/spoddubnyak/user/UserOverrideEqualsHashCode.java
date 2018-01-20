package ru.spoddubnyak.user;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class User for learning purposes, override methods hashCode() and equals().
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 29.08.2017
 */
public class UserOverrideEqualsHashCode extends User {
    /**
     * Constructor it creates a new Object User with the specified values.
     *
     * @param name     - name for object User
     * @param children - number children
     * @param birthday - birth day
     */
    public UserOverrideEqualsHashCode(String name, int children, Calendar birthday) {
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
        if (!(o instanceof UserOverrideEqualsHashCode) && !valid) {
            result = false;
            valid = true;
        }
        if (!valid) {
            UserOverrideEqualsHashCode user = (UserOverrideEqualsHashCode) o;
            result = getChildren() == user.getChildren()
                    && Objects.equals(getName(), user.getName())
                    && Objects.equals(getBirthday(), user.getBirthday());
        }
        return result;
    }
}
