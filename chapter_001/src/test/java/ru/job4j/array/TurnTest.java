package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void when54321then12345() {
        int[] result = new Turn().turn(new int[] {5, 4, 3, 2, 1});
        assertThat(new int[]{1, 2, 3, 4, 5}, is(result));
    }

    @Test
    public void when654321then123456() {
        int[] result = new Turn().turn(new int[] {6, 5, 4, 3, 2, 1});
        assertThat(new int[]{1, 2, 3, 4, 5, 6}, is(result));
    }

    @Test
    public void whenEmptyArraythenNoErr() {
        int[] result = new Turn().turn(new int[]{});
        assertThat(new int[]{}, is(result));
    }
}
