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

    @Test
    public void whenSomeUsersListSortedByNameLengthThenItNameLengthAscending() {
        List<User> users = new ArrayList<>();
        users.add(new User("5555", 5));
        users.add(new User("22", 2));
        users.add(new User("333", 4));
        users.add(new User("454545", 7));
        users.add(new User("11", 2));
        users.add(new User("6666", 1));
        users.add(new User("7777", 9));
        SortUser utils = new SortUser();
        assertThat(users.get(1), is((utils.sortNameLength(users)).get(0)));
    }

    @Test
    public void whenSomeUsersListSortedByNameAndAgeThenItNormSorted() {
        List<User> users = new ArrayList<>();
        List<User> sorted = new ArrayList<>();
        users.add(new User("5555", 5));
        users.add(new User("22", 2));
        users.add(new User("333", 4));
        users.add(new User("454545", 7));
        users.add(new User("11", 2));
        users.add(new User("5555", 1));
        users.add(new User("5555", 9));
        sorted.add(users.get(4));
        sorted.add(users.get(1));
        sorted.add(users.get(2));
        sorted.add(users.get(3));
        sorted.add(users.get(5));
        sorted.add(users.get(0));
        sorted.add(users.get(6));
        SortUser utils = new SortUser();
        assertThat(sorted, is((utils.sortByAllFields(users))));
    }

}
