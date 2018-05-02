package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {

    public static final Item NULL_ITEM = new Item();
    private static final Random RND = new Random();

    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;


    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                items[i] = item;
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
        int deletePosition = -1;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                deletePosition = i;
                break;
            }
        }
        if (deletePosition >= 0) {
            System.arraycopy(this.items, deletePosition + 1, this.items, deletePosition, position - deletePosition - 1);
            this.items[position--] = null;
        }
    }

    /**
     * Находит все не пустые элементы и возвращает их в виде массива без null-ов
     * @return - массив задач без пустых ссылок.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }

    /**
     * Ищет все элементы с указаным именем
     * @param key - наименование задачи
     * @return - массив всех подходящих задач
     */
    public Item[] findByName(String key) {
        int count = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                count++;
            }
        }
        Item[] result = new Item[count];
        int index = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[index++] = this.items[i];
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
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = items[i];
                break;
            }
        }
        return result;
    }
}
