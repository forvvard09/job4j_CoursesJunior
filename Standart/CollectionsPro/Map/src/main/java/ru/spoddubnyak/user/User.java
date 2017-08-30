package ru.spoddubnyak.user;

import java.util.Calendar;

/**
 * Class User for learning purposes, mastering the Map.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.08.2017
 */
public class User {
    /**
     * property name - name user.
     */
    private String name;
    /**
     * property children - number children.
     */
    private int children;
    /**
     * property birthday - birth day user.
     */
    private Calendar birthday;

    /**
     * Getter for property name.
     *
     * @return property name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for property children.
     *
     * @return property children
     */
    public int getChildren() {
        return this.children;
    }

    /**
     * Getter for property birthday.
     *
     * @return property birthday
     */
    public Calendar getBirthday() {
        return this.birthday;
    }

    /**
     * Constructor it creates a new Object User with the specified values.
     *
     * @param name     - name for object User
     * @param children - number children
     * @param birthday - birth day
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
