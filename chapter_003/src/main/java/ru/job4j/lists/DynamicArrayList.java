package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический расширяемый массив
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 13.07.2018
 * @param <E> - тип хранимых значений
 */
public class DynamicArrayList<E> implements Iterable<E> {

    private Object[] container = new Object[1];
    private int modCount;
    private int size;

    private void checkSize() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     * Добавление элемента
     * @param value - новое значение
     */
    public void add(E value) {
        checkSize();
        container[size++] = value;
        modCount++;
    }

    /**
     * Получение элемента по индексу
     * @param index - индекс, если индекс выходит за границы массива
     *             - будет выброшено исключение, ArrayIndexOutOfBoundsException
     * @return - полученное значение
     */
    public E get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    /**
     * Итератор
     * @return - итератор значений динамического массива
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return position < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[position++];
            }
        };
    }
}
