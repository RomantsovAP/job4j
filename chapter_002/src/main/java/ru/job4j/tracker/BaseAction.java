package ru.job4j.tracker;

/**
 * Класс-шаблон для любого будущего действия пользователя
 * @author AlekseyRomantsov
 * @since 19.05.2018
 * @version 1.0.0.0
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Возвращает ключ - целое число, порядковый номер пункта меняю
     * @return - число, порядковый номер пункта меню
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Показать описание пункта меню
     * @return - строковое описание пункта меню
     */
    @Override
    public String showInfo() {
        return String.format("%s. %s", this.key, this.name);
    }
}
