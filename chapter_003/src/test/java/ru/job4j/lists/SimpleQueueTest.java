package ru.job4j.lists;

import org.junit.Test;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void whenPushSomeAndPollSomeThenItWorks() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(1, is(queue.poll()));
        assertThat(2, is(queue.poll()));
        assertThat(3, is(queue.poll()));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNothingToPollItThrowsExc() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

}
