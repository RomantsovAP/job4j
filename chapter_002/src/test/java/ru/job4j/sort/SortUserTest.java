package ru.job4j.sort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenSomeUsersLitsSortedThenItAgeAscending() {
        List<User> users = new ArrayList<>();
        users.add(new User("1", 5));
        users.add(new User("2", 2));
        users.add(new User("3", 4));
        users.add(new User("4", 7));
        users.add(new User("5", 2));
        users.add(new User("6", 1));
        users.add(new User("7", 9));
        SortUser utils = new SortUser();
        assertThat(users.get(5), is((User) utils.sort(users).toArray()[0]));
        assertThat(users.get(5), is(((TreeSet<User>) utils.sort(users)).first()));
    }
}
