package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class ArrayDuplicate {
    /**
     * Метод удаляет повторяющиеся строки из массива
     * дубли сначала перекидываются в конец массива, после чего берется часть массива за вычетом хвоста из дублей
     * @param array - исходный массив
     * @return - массив без дублей
     */
    public String[] remove(String[] array) {
        int last = array.length;
        for (int i = 0; i < last; i++) {
            for (int j = i + 1; j < last; j++) {
                if (array[i].equals(array[j])) {
                    String ss = array[j];
                    array[j] = array[last - 1];
                    array[last - 1] = ss;
                    last--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, last);
    }
}
