package ru.job4j.maps;

import org.junit.Test;

import java.util.*;


public class UserTest1 {

    @Test
    public void whenNoEqualsNoHashCodeThenSameUsersInMapIsDifferentEntrys() {
        Map<User, String> userMap = new HashMap<>();

        User user1 = new User("Иванов Иван Иванович", 1, new GregorianCalendar(1972, 7, 17));
        User user2 = new User("Иванов Иван Иванович", 1, new GregorianCalendar(1972, 7, 17));
        userMap.put(user1, "Biography of user1");
        userMap.put(user2, "Biography if user2");

        for (Map.Entry<User, String> entry : userMap.entrySet()) {
            System.out.println(entry.getKey().toString() + " --  " + entry.getValue());
        }

    }
}
