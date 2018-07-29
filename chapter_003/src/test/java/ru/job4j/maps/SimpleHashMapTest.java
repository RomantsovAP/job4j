package ru.job4j.maps;

import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    private SimpleHashMap<String, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleHashMap<>();
        map.insert("1", "11");
        map.insert("2", "22");
        map.insert("3", "33");
        map.insert("4", "44");
        map.insert("5", "55");
    }

    @Test
    public void whenAddSomePairsThenItWorks() {

        StringBuilder sb = new StringBuilder();

        for (SimpleHashMap<String, String>.Entry<String, String> entry: map.getEntrySet()) {
            sb.append(entry.getKey());
        }
        assertThat(sb.toString(), is("23451"));
    }

    @Test
    public void whenGetSomeValueByTheKeyItWorks() {

        assertThat(map.get("1"), is("11"));
        assertThat(map.get("2"), is("22"));
        assertThat(map.get("3"), is("33"));
        assertThat(map.get("4"), is("44"));
        assertThat(map.get("5"), is("55"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenDeleteSomePairsByTheKeyItDeletes() {
        map.delete("5");
        map.get("5");
    }

    @Test
    public void whenDeleteSomePairsByTheKeyThenAllRestWorks() {
        map.delete("5");
        assertThat(map.get("1"), is("11"));
        assertThat(map.get("4"), is("44"));

        StringBuilder sb = new StringBuilder();

        for (SimpleHashMap<String, String>.Entry<String, String> entry: map.getEntrySet()) {
            sb.append(entry.getKey());
        }
        assertThat(sb.toString(), is("2341"));
    }

    @Test
    public void whenToMuchPairsAddedThenItResizesCorrectly() {
        HashMap<String, Boolean> insResult = new HashMap<>();

        for (int i = 1; i < 25; i++) {
            insResult.put("" + i, map.insert("" + i, i + "" + i));
        }

        for (int i = 1; i < 25; i++) {
            if (insResult.get("" + i)) {
                assertThat(map.get("" + i), is(i + "" + i));
            }
        }


    }
}
