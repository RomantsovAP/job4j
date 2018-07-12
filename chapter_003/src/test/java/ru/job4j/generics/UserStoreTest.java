package ru.job4j.generics;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    @Test
    public void whenAddSomeItWorks() {
        UserStore userStore = new UserStore();
        User store1 = new User("123");
        userStore.add(store1);
        assertThat(store1, is(userStore.findById("123")));
    }

    @Test
    public void whenReplaceSomeItWorks() {
        UserStore roleStore = new UserStore();
        User role1 = new User("123");
        User role2 = new User("321");
        roleStore.add(role1);
        roleStore.replace("123", role2);
        assertThat(role2, is(roleStore.findById("321")));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteSomeItWorks() {
        UserStore roleStore = new UserStore();
        User role1 = new User("123");
        User role2 = new User("321");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(true, is(roleStore.delete("123")));
        assertThat(role2, is(roleStore.findById("321")));
        roleStore.findById("123");
    }
}
