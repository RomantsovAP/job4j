package ru.job4j.tracker;

import java.util.Objects;

/**
 * Пункт меню
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.05.2018
 */
public class Item {
    private String id;
    private String name;
    private String desk;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item(String id, String name, String desk) {
        this.id = id;
        this.name = name;
        this.desk = desk;
    }

    public Item(String name, String desk) {
        this.name = name;
        this.desk = desk;
    }

    public Item() {
    }

    public String getDesk() {
        return desk;
    }

    @Override
    public String toString() {
        return "Item{" +  "id='" + id + '\'' + ", name='" + name + '\'' + ", desk='" + desk + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
