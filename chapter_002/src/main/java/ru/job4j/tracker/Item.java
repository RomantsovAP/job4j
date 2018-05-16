package ru.job4j.tracker;

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

    @Override
    public String toString() {
        return "Item{" +  "id='" + id + '\'' + ", name='" + name + '\'' + ", desk='" + desk + '\'' + '}';
    }
}
