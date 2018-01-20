package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckStringTest {

    @Test
    public void whenSubBeginningOriginThenChek() {
        CheckString strings = new CheckString("abcdee", "ab");
        assertThat(strings.checkLineEntyAnotherLine(), is(true));
    }

    @Test
    public void whenSubMidleOriginThenChek() {
        CheckString strings = new CheckString("abcdee", "cd");
        assertThat(strings.checkLineEntyAnotherLine(), is(true));
    }

    @Test
    public void whenSubEndOriginThenChek() {
        CheckString strings = new CheckString("abcdee", "ee");
        assertThat(strings.checkLineEntyAnotherLine(), is(true));
    }

    @Test
    public void whenSubNotOriginThenChek() {
        CheckString strings = new CheckString("abcdee", "opo");
        assertThat(strings.checkLineEntyAnotherLine(), is(false));
    }
}