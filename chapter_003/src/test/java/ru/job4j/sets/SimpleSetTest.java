package ru.job4j.sets;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenAddSomeElementsThenTheyPresenInSet() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(123);
        set.add(124);
        set.add(125);
        int sum = 0;
        for (Integer i : set) {
            sum += i;
        }
        assertThat(sum, is(372));
    }

    @Test
    public void whenAddEqualElementsThenOnlyOnePresentInSet() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        int sum = 0;
        for (Integer i : set) {
            sum += i;
        }
        assertThat(sum, is(3));
    }
}
