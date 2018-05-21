package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class UserConvertTest {

    @Test
    public void whenListWith5USersThenHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(1, "name1", "city1"));
        list.add(new User(2, "name2", "city1"));
        list.add(new User(3, "name3", "city1"));
        list.add(new User(4, "name4", "city1"));
        list.add(new User(5, "name1", "city1"));
        HashMap<Integer, User> map = new HashMap<>();
        map.put(1, list.get(0));
        map.put(2, list.get(1));
        map.put(3, list.get(2));
        map.put(4, list.get(3));
        map.put(5, list.get(4));
        assertThat(map, is(converter.process(list)));
    }

    @Test
    public void whenListWith5NewUsersThenHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(1, "name1", "city1"));
        list.add(new User(2, "name2", "city1"));
        list.add(new User(3, "name3", "city1"));
        list.add(new User(4, "name4", "city1"));
        list.add(new User(5, "name1", "city1"));
        HashMap<Integer, User> map = new HashMap<>();
        map.put(1, new User(1, "name1", "city1"));
        map.put(2, new User(2, "name2", "city1"));
        map.put(3, new User(3, "name3", "city1"));
        map.put(4, new User(4, "name4", "city1"));
        map.put(5, new User(5, "name1", "city1"));
        assertThat(map, is(converter.process(list)));
    }
}
