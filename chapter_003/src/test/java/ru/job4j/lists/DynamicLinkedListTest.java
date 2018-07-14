package ru.job4j.lists;


import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(2), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetForthElementFormThreeElementListItThrowsExc() {
        list.get(3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddElementWhileIterateItThrowsExc() {
        for (Integer i : list) {
            if (i == 1) {
                list.add(4);
            }
        }
    }

    @Test
    public void whenIteratethenItWorks() {
        Iterator<Integer> it = list.iterator();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        int i = it.next();
        assertThat(1, is(i));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenItearteAfterTheEndItThrowsExc() {
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }
    @Test
    public void whenForeachLoopItWorks() {
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        assertThat(6, is(sum));
    }

    @Test
    public void whenRemoveFirstItRemoves() {
        list.removeFirst();
        assertThat(2, is(list.getFirst()));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveFirstWhileNoElementsLeftThenListIsEmpty() {
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.getLast();
    }

    @Test
    public void whenRemoveLastItRemoves() {
        list.removeLast();
        assertThat(2, is(list.getLast()));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveLasttWhileNoElementsLeftThenListIsEmpty() {
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.get(0);
    }

}