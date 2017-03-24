package ru.spoddubnyak.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Comments.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 19.01.2017z
 */
public class CommentTest {

    /**
     * test property -  comment.
     */
    private String comment;

    /**
     * Test create object Comment using constructor.
     */
    @Test
    public void whenCreatesCommentsUsingConstructorThenGetSameResultInComment() {
        comment = "test comment";
        Comment comments = new Comment(comment);
        assertThat(comment, is(comments.getComment()));
    }

    /**
     * Test setter and getter class Comment.
     */
    @Test
    public void whenSetCommentThenGetSameResultComment() {
        comment = "test comment";
        Comment comments = new Comment("test");
        comments.setComment(comment);
        assertThat(comment, is(comments.getComment()));
    }

}