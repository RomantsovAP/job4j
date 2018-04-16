package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(200, 2);
        assertThat(result, is(200));
    }

    @Test
    public void whenFirstLessThanSecondAndThirdThenThird() {
        Max maximum = new Max();
        int result = maximum.max(0, 1, 150);
        assertThat(result, is(150));
    }

    @Test
    public void whenThirdLessThanSecondAndFirstThenFirst() {
        Max maximum = new Max();
        int result = maximum.max(1010, 1, 150);
        assertThat(result, is(1010));
    }

}
