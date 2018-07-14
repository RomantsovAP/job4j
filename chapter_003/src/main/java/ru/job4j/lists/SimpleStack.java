package ru.job4j.lists;

/**
 * Простой стэк, стопка
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 14.07.2018
 * @param <T> - тип хранимых значений
 */
public class SimpleStack<T> {
    private DynamicLinkedList<T> list = new DynamicLinkedList<>();

    /**
     * Получить верхний элемент стэка
     * @return - значение верхнего элемента
     */
    public T poll() {
        T value =  list.getLast();
        list.removeLast();
        return value;
    }

    /**
     * Добавить наверх еще один элемент стэка
     * @param value - добавляемое значение
     */
    public void push(T value) {
        list.add(value);
    }
}
