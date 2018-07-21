package ru.job4j.maps;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

}
