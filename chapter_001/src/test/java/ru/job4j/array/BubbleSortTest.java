package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void when51273then12357() {
        int[] result = new BubbleSort().sort(new int[]{5, 1, 2, 7, 3});
        assertThat(new int[] {1, 2, 3, 5, 7}, is(result));
    }

    @Test
    public void when55775then55577() {
        int[] result = new BubbleSort().sort(new int[]{5, 5, 7, 7, 5});
        assertThat(new int[] {5, 5, 5, 7, 7}, is(result));
    }
}
