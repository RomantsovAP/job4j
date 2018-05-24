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
     * Находит первую задачу с меньшим приоритетом
     * приоритет 1 - наивысший, 2, 3, 4 ... по убыванию приоритета
     * @param priority - приоритет нашей задачи
     * @return - индекс первой задачи с меньшим приоритетом, чем укаазан в параметрах
     */
    private int findTaskWithLessPriority(int priority) {
        int index = tasks.size(); // по-умолчанию в самый конец, после всех задач
        for (Task currentTask : tasks) {
            if (currentTask.getPriority() > priority) {
                index = tasks.indexOf(currentTask);
                break;
            }
        }
        return index;
    }

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определяется по полю приоритет, т.е. вставка идет после всех позиций, имеющих более высокий приоритет
     * @param task задача
     */
    public void put(Task task) {
        int index = findTaskWithLessPriority(task.getPriority());
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}