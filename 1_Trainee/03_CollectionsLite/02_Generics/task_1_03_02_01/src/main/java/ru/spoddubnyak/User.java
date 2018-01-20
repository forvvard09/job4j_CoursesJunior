package ru.spoddubnyak;

import java.util.Random;

/**
 * Class contains information on user.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2017
 */
public class User {
    /**
     * property -  for fet random id.
     */
    private static final Random RN = new Random();
    /**
     * property -  max number random.
     */
    private static final int RANGERANDOM = 999;
    /**
     * property -  id.
     */
    private int id;
    /**
     * property -  name.
     */
    private String name;
    /**
     * property -  city.
     */
    private String city;

    /**
     * Constructor it creates a new object User with the specified values.
     */
    public User() {
        this.id = this.generationId();
    }

    /**
     * Constructor it creates a new object User with the specified values.
     *
     * @param id - property id
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * Constructor it creates a new object User with the specified values.
     *
     * @param id   - propery id
     * @param name - property name
     * @param city - property city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Metgod generating random id.
     *
     * @return id
     */
    int generationId() {
        return RN.nextInt(RANGERANDOM);
    }

    /**
     * Getter get id.
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter set property id.
     *
     * @param id - poperty id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter get name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter set property name.
     *
     * @param name - property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter get city.
     *
     * @return city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter set property city.
     *
     * @param city - property city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
