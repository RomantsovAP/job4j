package ru.job4j.search;

/**
 * Задача
 * @author AlekseyRomantsov
 * @since 21.05.2018
 * @version 1.0.0.0
 */
public class Task {
    private String desc;
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
