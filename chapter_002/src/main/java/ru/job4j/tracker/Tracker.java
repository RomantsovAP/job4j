package ru.job4j.tracker;

import java.util.*;

/**
 * Задачник, содержит список элементов-задач и основные действия с ним
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.05.2018
  */
public class Tracker implements ITracker {

    public static final Item NULL_ITEM = new Item();
    private static final Random RND = new Random();

    /**
     * Список для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();


    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return "ID" + System.currentTimeMillis() + RND.nextInt();
    }

    /**
     * Метод заменяет элемент с указанным идентификатором на переданный в параметрах
     * @param id - элемент с таким ID будет заменен
     * @param item - новый элемент, вместо прежнего
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
                this.items.add(item);
                item.setId(id);
                break;
            }
        }
    }

    /**
     * Удаляет элемент из перечня по ID
     * @param id - ID удаляемого элемента
     */
    public void delete(String id) {
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                this.items.remove(item);
                break;
            }
        }
    }

    /**
     * Находит все не пустые элементы и возвращает их в виде массива без null-ов
     * @return - массив задач без пустых ссылок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Ищет все элементы с указаным именем
     * @param key - наименование задачи
     * @return - массив всех подходящих задач
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item: this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Ищет задачу по идентификатору, может возвращать null если не нашлась
     * @param id - идентификатор
     * @return - найденная задача
     */
    public Item findById(String id) {
        Item result = NULL_ITEM;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
