package ru.job4j.generics;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {
    @Test
    public void whenAddSomeItWorks() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("123");
        roleStore.add(role1);
        assertThat(role1, is(roleStore.findById("123")));
    }

    @Test
    public void whenReplaceSomeItWorks() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("123");
        Role role2 = new Role("321");
        roleStore.add(role1);
        roleStore.replace("123", role2);
        assertThat(role2, is(roleStore.findById("321")));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteSomeItWorks() {
        RoleStore roleStore = new RoleStore();
        Role role1 = new Role("123");
        Role role2 = new Role("321");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(true, is(roleStore.delete("123")));
        assertThat(role2, is(roleStore.findById("321")));
        roleStore.findById("123");
    }



}
