package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация простого параметризованного списка для хранения данных
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 08.07.2018
 * @param <T> - тип хранимых данных
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int count = 0;

    /**
     * Добавить значение в список
     * @param model - значение
     */
    public void add(T model) {
        array[this.count++] = model;
    }

    /**
     * Установить значение в список
     * @param index - позиция
     * @param model - значение
     */
    public void set(int index, T model) {
        array[index] = model;
    }

    /**
     * удалить значение из списка
     * @param index - позиция
     */
    public void delete(int index) {
        array[index] = null;
    }

    /**
     * Получить значение из списка
     * @param index - позиция
     * @return - значение
     */
    public T get(int index) {
        return (T) array[index];
    }

    /**
     * @param size - размер списка
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Итератор списка, операции добавления\удаления недоступны
     * @return - итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position = -1;

            @Override
            public boolean hasNext() {
                return count > position + 1;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[++position];
            }
        };
    }

    public int size() {
        return count;
    }
}

