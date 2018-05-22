package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Сортируем пользователей
 * @author AlekseyRomantsov
 * @since 22.05.2018
 * @version 1.0.0.0
 */
public class SortUser {

    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
