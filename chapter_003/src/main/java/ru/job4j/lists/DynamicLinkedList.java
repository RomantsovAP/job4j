package ru.job4j.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedList<E> implements Iterable<E> {
    private int modCount;
    private Element root;
    private Element last;

    private class Element {
        E value;
        Element next;

        public Element(E value) {
            this.value = value;
        }
    }

    public void add(E value) {
        Element newElement = new Element(value);
        if (last != null) {
            last.next = newElement;
        }
        last = newElement;
        if (root == null) {
            root = last;
        }
        modCount++;
    }

    public E get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        Element curElement = root;
        while (index != 0) {
            if (curElement.next != null) {
                curElement = curElement.next;
                index--;
            } else {
                throw new NoSuchElementException();
            }
        }
        return curElement.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            Element nextElement = root;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return (nextElement != null);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = nextElement.value;
                nextElement = nextElement.next;
                return value;
            }
        };
    }
}
