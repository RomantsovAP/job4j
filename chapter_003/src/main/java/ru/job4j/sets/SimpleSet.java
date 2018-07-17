package ru.job4j.sets;

import ru.job4j.lists.DynamicArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private DynamicArrayList<T> list = new DynamicArrayList<>();

    public void add(T newElem) {
        boolean found = false;
        for (T elem : list) {
            if (elem.equals(newElem)) {
                found = true;
                break;
            }
        }
        if (!found) {
            list.add(newElem);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
