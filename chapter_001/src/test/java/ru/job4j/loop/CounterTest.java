package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {

    @Test
    public void whenFrom0To10Then30() {
        assertThat(new Counter().add(0, 10), is(30));
    }

    @Test
    public void whenFrom0To0Then0() {
        assertThat(new Counter().add(0, 0), is(0));
    }
}