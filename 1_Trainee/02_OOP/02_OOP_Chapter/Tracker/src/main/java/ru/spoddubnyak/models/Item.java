package ru.spoddubnyak.models;

import java.util.ArrayList;

/**
 * Class class storage of the massif of the records Item and performance of actions with this massif.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.12.2016
 */
public class Item {
    /**
     * property - identification number.
     */
    private int id;

    /**
     * property -  name.
     */
    private String name;
    /**
     * property -  description.
     */
    private String description;
    /**
     * property - time.
     */
    private Long create;
    /**
     * property - comments for Item.
     */
    private ArrayList<Comment> comments = new ArrayList<Comment>(2);

    /**
     * property - positionComments.
     */
    private int commentPosition = 0;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param name        - name
     * @param description - description
     * @param create      - time
     */
    public Item(String name, String description, Long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Constructor it creates a new object with the specified values except for creation time.
     *
     * @param name        - name
     * @param description - description
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }


    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param id          - identification number
     * @param name        - name
     * @param description - description
     * @param create      - time
     */
    public Item(int id, String name, String description, Long create) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param id          - identification number
     * @param name        - name
     * @param description - description
     */
    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Getter property -  name.
     *
     * @return property -  name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter property -  name.
     *
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter property -  id.
     *
     * @return property -  id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter property -  id.
     *
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter property -  description.
     *
     * @return property -  description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter property -  description.
     *
     * @param description - description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter property -  create.
     *
     * @return property -  create
     */
    public Long getCreate() {
        return create;
    }

    /**
     * Setter property -  create.
     *
     * @param create - time.
     */
    public void setCreate(Long create) {
        this.create = create;
    }

    /**
     * Method add a comment to an array of comments.
     *
     * @param comment - new comment
     */
    public void addComment(Comment comment) {
        this.comments.add(commentPosition++, comment);

    }

    /**
     * Method delete comment object.
     */
    public void delComments() {
        this.comments.clear();
    }

    /**
     * Method returns an array of comments.
     *
     * @return Comment[] - array comments
     */
    public ArrayList<Comment> getComments() {
        ArrayList<Comment> result = new ArrayList<>(this.commentPosition);
        for (int i = 0; i != this.commentPosition; i++) {
            result.add(i, this.comments.get(i));
        }
        return result;
    }
}