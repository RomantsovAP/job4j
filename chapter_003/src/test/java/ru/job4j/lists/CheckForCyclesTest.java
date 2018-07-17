package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CheckForCyclesTest {
    @Test
    public void whenWeHaveCycleItFoundIt() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> first  = check.new Node<>(1);
        CheckForCycles.Node<Integer> two    = check.new Node<>(2);
        CheckForCycles.Node<Integer> third  = check.new Node<>(3);
        CheckForCycles.Node<Integer> four   = check.new Node<>(4);

        first.next  = two;
        two.next    = third;
        third.next  = four;
        four.next   = first;

        assertThat(true, is(check.hasCycle(first)));
    }

    @Test
    public void whenWeHaveCycleItFoundItMK2() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> first  = check.new Node<>(1);
        CheckForCycles.Node<Integer> two    = check.new Node<>(2);
        CheckForCycles.Node<Integer> third  = check.new Node<>(3);
        CheckForCycles.Node<Integer> four   = check.new Node<>(4);

        first.next  = two;
        two.next    = third;
        third.next  = four;
        four.next   = first;

        assertThat(true, is(check.hasCycleMk2(first)));
    }

    @Test
    public void whenWeHaveNoCyclesItNotFoundThem() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> first  = check.new Node<>(1);
        CheckForCycles.Node<Integer> two    = check.new Node<>(2);
        CheckForCycles.Node<Integer> third  = check.new Node<>(3);
        CheckForCycles.Node<Integer> four   = check.new Node<>(4);

        first.next  = two;
        two.next    = third;
        third.next  = four;
        four.next   = null;

        assertThat(false, is(check.hasCycle(first)));
    }

    @Test
    public void whenWeHaveNoCyclesItNotFoundThemMK2() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> first  = check.new Node<>(1);
        CheckForCycles.Node<Integer> two    = check.new Node<>(2);
        CheckForCycles.Node<Integer> third  = check.new Node<>(3);
        CheckForCycles.Node<Integer> four   = check.new Node<>(4);

        first.next  = two;
        two.next    = third;
        third.next  = four;
        four.next   = null;

        assertThat(false, is(check.hasCycleMk2(first)));
    }

    @Test
    public void whenWeHaveLoopItFoundIt() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> first  = check.new Node<>(1);
        first.next  = first;
        assertThat(true, is(check.hasCycle(first)));
    }

    @Test
    public void whenWeHaveLoopItFoundItMK2() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node first  = check.new Node<>(1);
        first.next  = first;
        assertThat(true, is(check.hasCycleMk2(first)));
    }

    @Test
    public void whenWeHaveMoreElementsAndCycleItFoundItMK2() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> n1  = check.new Node<>(1);
        CheckForCycles.Node<Integer> n2  = check.new Node<>(2);
        CheckForCycles.Node<Integer> n3  = check.new Node<>(3);
        CheckForCycles.Node<Integer> n4  = check.new Node<>(4);
        CheckForCycles.Node<Integer> n5  = check.new Node<>(5);
        CheckForCycles.Node<Integer> n6  = check.new Node<>(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        n6.next = n1;
        assertThat(true, is(check.hasCycleMk2(n1)));

        n6.next = n2;
        assertThat(true, is(check.hasCycleMk2(n1)));

        n6.next = n3;
        assertThat(true, is(check.hasCycleMk2(n1)));

        n6.next = n4;
        assertThat(true, is(check.hasCycleMk2(n1)));

        n6.next = n5;
        assertThat(true, is(check.hasCycleMk2(n1)));

        n6.next = n6;
        assertThat(true, is(check.hasCycleMk2(n1)));

    }

    @Test
    public void whenWeHaveMoreElementsAndCycleItFoundIt() {
        CheckForCycles check = new CheckForCycles();
        CheckForCycles.Node<Integer> n1  = check.new Node<>(1);
        CheckForCycles.Node<Integer> n2  = check.new Node<>(2);
        CheckForCycles.Node<Integer> n3  = check.new Node<>(3);
        CheckForCycles.Node<Integer> n4  = check.new Node<>(4);
        CheckForCycles.Node<Integer> n5  = check.new Node<>(5);
        CheckForCycles.Node<Integer> n6  = check.new Node<>(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        n6.next = n1;
        assertThat(true, is(check.hasCycle(n1)));

        n6.next = n2;
        assertThat(true, is(check.hasCycle(n1)));

        n6.next = n3;
        assertThat(true, is(check.hasCycle(n1)));

        n6.next = n4;
        assertThat(true, is(check.hasCycle(n1)));

        n6.next = n5;
        assertThat(true, is(check.hasCycle(n1)));

        n6.next = n6;
        assertThat(true, is(check.hasCycle(n1)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoElementsInListThenNoCuclesinIt() {
        CheckForCycles check = new CheckForCycles();
        check.hasCycle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoElementsInListThenNoCuclesinItMK2() {
        CheckForCycles check = new CheckForCycles();
        check.hasCycleMk2(null);
    }
}
