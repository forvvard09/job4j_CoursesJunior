package ru.spoddubnyak;

/**
 * Class model User.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.04.2017
 */
public class User implements Comparable {

    /**
     * property name User.
     */
    private String name;

    /**
     * property age User.
     */
    private int age;

    /**
     * Constructor it creates a new object User with the specified values.
     *
     * @param name - set property name
     * @param age  - set property age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
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
     * Setter property name.
     *
     * @param name - set property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter property age.
     *
     * @return name - property age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter property age.
     *
     * @param age - set property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.age - user.getAge();
    }
}
