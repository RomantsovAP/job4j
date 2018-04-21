package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test
    public void whenArrayHasLengh5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasLengh0ThenMinus1() {
        assertThat(-1, is(new FindLoop().indexOf(new int[] {}, 1)));
    }

    @Test
    public void whenArrayHasNoSuchElementThenMinus1() {
        assertThat(-1, is(new FindLoop().indexOf(new int[] {2, 3, 4, 5, 6, 7}, 1)));
    }

    @Test
    public void whenArrayHasSuchElementThenRightIndex() {
        assertThat(5, is(new FindLoop().indexOf(new int[] {2, 3, 4, 5, 6, 7}, 7)));
    }
}
