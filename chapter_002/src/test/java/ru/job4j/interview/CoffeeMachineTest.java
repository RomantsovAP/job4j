package ru.job4j.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CoffeeMachineTest {
    @Test
    public void when100and35ThenTensAndFive() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] answ = coffeeMachine.changes(100, 35);
        assertThat(new int[] {10, 10, 10, 10, 10, 10, 5}, is(answ));
    }

    @Test
    public void when99and35Then22onTheEnd() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] answ = coffeeMachine.changes(99, 35);
        assertThat(new int[] {10, 10, 10, 10, 10, 10, 2, 2}, is(answ));
    }

    @Test
    public void when35and35ThenEmpty() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] answ = coffeeMachine.changes(35, 35);
        assertThat(new int[]{}, is(answ));
    }
}
