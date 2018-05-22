package ru.job4j.sort;

/**
 * Персональные данные
 * @author AlekseyRomantsov
 * @since 22.05.2018
 * @version 1.0.0.0
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return ((Integer) this.age).compareTo(o.age);
    }
}
