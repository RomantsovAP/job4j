package ru.job4j.statistics;

import java.util.*;

class Store {

    static class User implements Comparable<User> {
        int id;
        String name;

        public void setName(String name) {
            this.name = name;
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public int compareTo(User o) {
            return Integer.compare(this.id, o.id);
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

    private void addStatistic(HashMap<Type, Integer> result, Type type) {
        if (!result.containsKey(type)) {
            result.put(type, 1);
        } else {
            result.put(type, result.get(type) + 1);
        }
    }

    HashMap<Type, Integer> diff(List<User> prev, List<User> curr) {
        HashMap<Type, Integer> result = new HashMap<>();
        prev.sort(null);
        curr.sort(null);
        Iterator<User> prevIt = prev.iterator();
        Iterator<User> currIt = curr.iterator();
        User prevUser = null;
        User currUser = null;
        boolean takeFromPrev = true;
        boolean takeFromCurr = true;
        do {
            prevUser = (prevIt.hasNext() && takeFromPrev) ? prevIt.next() : prevUser;
            currUser = (currIt.hasNext() && takeFromCurr) ? currIt.next() : currUser;
            if (currUser == null && prevUser == null)  {
                break;
            }
            if (currUser != null && prevUser == null)  {
                addStatistic(result, Type.Added);
                takeFromCurr = true;
                takeFromPrev = false;
                continue;
            }
            if (currUser == null) {
                addStatistic(result, Type.Deleted);
                takeFromCurr = false;
                takeFromPrev = true;
                continue;
            }
            if (currUser.id == prevUser.id) {
                if (!(currUser.equals(prevUser))) {
                    addStatistic(result, Type.Edited);
                }
                takeFromPrev = true;
                takeFromCurr = true;
                continue;
            }
            if (currUser.id < prevUser.id) {
                takeFromCurr = true;
                takeFromPrev = false;
                addStatistic(result, Type.Added);
                continue;
            }
            takeFromCurr = false;
            takeFromPrev = true;
            addStatistic(result, Type.Deleted);

        } while (currIt.hasNext() || prevIt.hasNext());
        return result;
    }

}
