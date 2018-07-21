package ru.job4j.maps;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class UserTest1 {

    @Test
    public void whenNoEqualsNoHashCodeThenSameUsersInMapIsDifferentEntrys() {
        Map<User, String> userMap = new HashMap<>();

        User user1 = new User("Иванов Иван Иванович", 1, new GregorianCalendar(1972, 7, 17));
        System.out.println(System.identityHashCode(user1));
        User user2 = new User("Иванов Иван Иванович", 1, new GregorianCalendar(1972, 7, 17));
        System.out.println(System.identityHashCode(user2));
        userMap.put(user1, "Biography of user1");
        userMap.put(user2, "Biography if user2");

        for (Map.Entry<User, String> entry : userMap.entrySet()) {
            System.out.println(entry.getKey().toString() + " --  " + entry.getValue());
            System.out.println(System.identityHashCode(entry.getKey()));
        }

        assertThat(userMap.get(user1), is("Biography if user2"));


    }
}
