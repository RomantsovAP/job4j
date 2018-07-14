package ru.job4j.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Лднонаправленный связный список, но с указателями на начало и конец.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 14.07.2018
 * @param <E>
 */
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

    /**
     * Добавление элемента в список
     * @param value - добавляемое значение
     */
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

    /**
     * Получение значение из списка по индексу
     * @param index  - индекс, целое неотрицательное число,
     * @return - значение из списка
     */
    public E get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            throw new NoSuchElementException();
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

    /**
     * Удаление последнего элемента списка
     */
    public void removeLast() {
        if (root != null) {

            if (root == last) {
                root = null;
                last = null;
            } else {
                Element curElement = root;
                while (curElement.next.next != null) {
                    curElement = curElement.next;
                }
                curElement.next = null;
                last = curElement;
            }

        }
    }

    /**
     * Удаление первого элемента списка
     */
    public void  removeFirst() {
        if (root != null) {
            if (root == last) {
                root = null;
                last = null;
            } else {
                root = root.next;
            }
        }
    }

    /**
     * Получение последнего элемента списка
     * @return - значеие последнего элемента списка
     */
    public E getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.value;
    }

    /**
     * Получение первого элемента списка
     * @return - значение первого элемента списка
     */
    public E getFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return root.value;
    }

    /**
     * Итератор списка
     * @return - возвращает пригодный к использованию итератор
     */
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
