package ru.job4j.lists;

import org.junit.Test;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    @Test
    public void whenPushSomeAndPollSomeThenItWorks() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(3, is(stack.poll()));
        assertThat(2, is(stack.poll()));
        assertThat(1, is(stack.poll()));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNothingToPollItThrowsExc() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }
}
