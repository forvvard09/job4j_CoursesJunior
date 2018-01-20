package ru.spoddubnyak.models;

/**
 * Class class comments.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017
 */

public class Comment {

    /**
     * property -  comment.
     */
    private String comment;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param comment - comment
     */
    public Comment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter property -  comment.
     *
     * @return property -  comment
     */

    public String getComment() {
        return this.comment;
    }

    /**
     * Setter property -  comment.
     *
     * @param comment - comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}