package ru.job4j.generics;

import java.util.NoSuchElementException;

/**
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 12.07.2018
 * @param <T> - тип хранимых данных, только подклассы Base
 */
public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> storage = new SimpleArray<>(100);

    private int indexById(String id) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i) != null && id.equals(storage.get(i).getId())) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Добавление элемента
     * @param model - элемент
     */
    @Override
    public void add(T model) {
        storage.add(model);
    }

    /**
     * Замена элемента
     * @param id - идентификатор
     * @param model - элемент, на который будем заменять
     * @return - true, если замена прошла успешно
     */
    @Override
    public boolean replace(String id, T model) {
        int index = indexById(id);
        if (index != -1) {
            storage.set(index, model);
        }
        return (index != -1);
    }

    /**
     * Удаление элемента
     * @param id - идентификатор
     * @return - истина, если удаление прошло успешно
     */
    @Override
    public boolean delete(String id) {
        int index = indexById(id);
        if (index != -1) {
            storage.delete(index);
        }
        return (index != -1);
    }

    /**
     * Поиск элемента по идентификатору
     * Если элемент с таким идентификатором отсутствует - будет выброшено NoSuchElementException
     * @param id - идентификатор
     * @return - найденный элемент
     */
    @Override
    public T findById(String id) {
        int index = indexById(id);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return storage.get(index);
    }
}
