package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraysMergeTest {
    @Test
    public void whenArraysSortedThenItMerges() {
        int[] a = {1, 2, 3, 11, 12, 13, 21, 22, 23};
        int[] b = {4, 5, 6, 14, 15, 16, 24, 25, 26, 34, 35 ,36};

        ArraysMerge arraysMerge = new ArraysMerge();
        int[] result = {1, 2, 3, 4, 5, 6, 11, 12, 13, 14 ,15, 16, 21, 22, 23, 24, 25, 26, 34 ,35 ,36};
        assertThat(result, is(arraysMerge.merge(a,b)));
        assertThat(result, is(arraysMerge.merge(b,a)));
    }
}
