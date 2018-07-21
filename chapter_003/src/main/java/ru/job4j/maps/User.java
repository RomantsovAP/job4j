package ru.job4j.maps;

import java.util.Calendar;

/**
 * Пользователь, сотрудник
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.07.2018
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * @param name - ФИО
     * @param children - количество детей
     * @param birthday - дата рождения
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", birthday=" + birthday.getTime().toString() + '}';
    }
}
