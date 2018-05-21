package ru.job4j.search;

import java.util.LinkedList;

/**
 * Очередь с приоритетами
 * @author AlekseyRomantsov
 * @since 21.05.2018
 * @version 1.0.0.0
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        for (Task currentTask : tasks) {
            if (currentTask.getPriority() > task.getPriority()) {
                tasks.add(tasks.indexOf(currentTask), task);
                break;
            }
        }
        if (tasks.indexOf(task) == -1) {
            tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}