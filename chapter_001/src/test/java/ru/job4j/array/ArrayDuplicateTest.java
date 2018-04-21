package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesFromArrayWithoutDublicatesThenSameArray() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123", "124", "125"});
        assertThat(new String[] {"123", "124", "125"}, is(result));
    }

    @Test
    public void whenAllDuplicateThenOneLeft() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123", "123", "123"});
        assertThat(new String[] {"123"}, is(result));
    }

    @Test
    public void when2on2Then() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123", "124", "124", "123"});
        assertThat(new String[] {"123", "124"}, is(result));
    }

    @Test
    public void when2on2mixedThen() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123", "124", "123", "124"});
        assertThat(new String[] {"123", "124"}, is(result));
    }

    @Test
    public void when2on2mixed2Then() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123", "123", "124", "124"});
        assertThat(new String[] {"123", "124"}, is(result));
    }

    @Test
    public void whenLength1NoCrushThen() {
        String[] result = new ArrayDuplicate().remove(new String[] {"123"});
        assertThat(new String[] {"123"}, is(result));
    }

    @Test
    public void whenLength0NoCrushThen() {
        String[] result = new ArrayDuplicate().remove(new String[] {});
        assertThat(new String[] {}, is(result));
    }

    @Test
    public void whenjob4jTestThenRightAnswer() {
        String[] result = new ArrayDuplicate().remove(new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"});
        assertThat(new String[] {"Привет", "Мир", "Супер"}, is(result));
    }

}
