package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleArrayTest {

    @Test
    public void whenAddSomeItWorks() {
        SimpleArray<String> list = new SimpleArray<>(10);
        list.add("123");
        assertThat(list.get(0), is("123"));
    }

    @Test
    public void whenDeleteSomeItWorks() {
        SimpleArray<String> list = new SimpleArray<>(10);
        list.add("123");
        list.delete(0);
        assertThat(null, is(list.get(0)));
    }

    @Test
    public void whenInsertSomeItWorks() {
        SimpleArray<String> list = new SimpleArray<>(10);
        list.add("123");
        list.set(1, "124");
        assertThat("124", is(list.get(1)));
    }

    @Test
    public void whenForeachLoopItWorks() {
        SimpleArray<String> list = new SimpleArray<>(10);
        list.add("123");
        list.add("124");
        list.add("125");
        StringBuilder result = new StringBuilder();
        for (String str : list) {
            result.append(str);
        }
        assertThat(result.toString(), is("123124125"));
    }


}
