package ru.job4j.statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void whenSameListsThenEmptyStatistics() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));
        assertThat(store.diff(list, list).size(), is(0));
    }

    @Test
    public void whenAddSomeThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));

        List<Store.User> list2 = new ArrayList<>(list);
        list2.add(new Store.User(2, "2"));
        list2.add(new Store.User(-3, "-3"));

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Added, 2);
        assertThat(store.diff(list, list2), is(result));
    }

    @Test
    public void whenDeleteSomeThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));

        List<Store.User> list2 = new ArrayList<>(list);
        list2.add(new Store.User(2, "2"));
        list2.add(new Store.User(-3, "-3"));

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Deleted, 2);
        assertThat(store.diff(list2, list), is(result));
    }


    @Test
    public void whenEditSomeThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));

        List<Store.User> list2 = new ArrayList<>(list);
        list2.remove(1);
        list2.add(new Store.User(5, "123"));

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Edited, 1);
        assertThat(store.diff(list, list2), is(result));
    }

    @Test
    public void whenEditAndDeleteAndAddSomeThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));
        list.add(new Store.User(3, "3"));

        List<Store.User> list2 = new ArrayList<>(list);
        list2.remove(1);
        list2.add(new Store.User(5, "123"));
        list2.remove(0);
        list2.add(new Store.User(4, "4"));

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Edited, 1);
        result.put(Type.Deleted, 1);
        result.put(Type.Added, 1);
        assertThat(store.diff(list, list2), is(result));
    }


    @Test
    public void whenDeleteAllThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));
        list.add(new Store.User(3, "3"));

        List<Store.User> list2 = new ArrayList<>();

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Deleted, 4);
        assertThat(store.diff(list, list2), is(result));
    }

    @Test
    public void whenAddAllThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        list.add(new Store.User(1, "1"));
        list.add(new Store.User(5, "5"));
        list.add(new Store.User(-5, "-5"));
        list.add(new Store.User(3, "3"));

        List<Store.User> list2 = new ArrayList<>();

        HashMap<Type, Integer> result = new HashMap<>();
        result.put(Type.Added, 4);
        assertThat(store.diff(list2, list), is(result));
    }

    @Test
    public void whenEmptyListsThenStatisticsIsRight() {
        Store store = new Store();
        List<Store.User> list = new ArrayList<>();
        List<Store.User> list2 = new ArrayList<>();

        HashMap<Type, Integer> result = new HashMap<>();

        assertThat(store.diff(list2, list), is(result));
    }

}
