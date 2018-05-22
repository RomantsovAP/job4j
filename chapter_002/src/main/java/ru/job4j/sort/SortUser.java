package ru.job4j.sort;

import java.util.*;

/**
 * Сортируем пользователей
 * @author AlekseyRomantsov
 * @since 22.05.2018
 * @version 1.0.0.0
 */
public class SortUser {
    /**
     * формирует TreeSet по переданному списку пользователей
     * @param users - список пользователей
     * @return - TreeSet
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Сортировка списка по длине имен
     * @param users - список пользователей
     * @return - отсортированный список пользователей
     */
    public List<User> sortNameLength(List<User> users) {
        List<User> result = new ArrayList<>(users);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return result;
    }

    /**
     * Сортировка по имени и возрасту
     * @param users - список пользователей
     * @return - отсортированный по имени и возрасту список пользователей
     */
    public List<User> sortByAllFields(List<User> users) {
        List<User> result = new ArrayList<>(users);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int compare = o1.getName().compareToIgnoreCase(o2.getName());
                compare = (compare != 0) ? compare : Integer.compare(o1.getAge(),o2.getAge());
                return compare;
            }
        });
        return result;
    }
}
