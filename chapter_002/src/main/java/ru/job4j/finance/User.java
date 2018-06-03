package ru.job4j.finance;

import java.util.Objects;

/**
 * Клиент банка
 * @author AlekseyRomantsov
 * @since 01.06.2018
 * @version 1.0.0.0
 */
public class User implements Comparable<User> {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, passport);
    }

    @Override
    public int compareTo(User o) {
        return this.passport.compareTo(o.passport);
    }
}
