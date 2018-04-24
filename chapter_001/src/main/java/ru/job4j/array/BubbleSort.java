package ru.job4j.array;

/**
 * Класс для пузырьковой сортировки
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */

public class BubbleSort {
    /**
     * Метод сортирует переданный массив методом пузырьковой сортировки (без оптимизаций)
     * @param array - массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
