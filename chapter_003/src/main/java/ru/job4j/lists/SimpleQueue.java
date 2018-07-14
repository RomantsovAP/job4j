package ru.job4j.lists;

/**
 * Простая очередь
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 14.07.2018
 * @param <T> - тип хранимых значений
 */
public class SimpleQueue<T> {
    private DynamicLinkedList<T> list = new DynamicLinkedList<>();

    /**
     * Получить следующий по очереди элемент
     * @return - полученное значение, если очередь пуста, будет выброшено исключение
     */
    public T poll() {
        T value = list.getFirst();
        list.removeFirst();
        return value;
    }

    /**
     * Добавить в конец очереди
     * @param value - добавляемое значение
     */
    public void push(T value) {
        list.add(value);
    }

}
