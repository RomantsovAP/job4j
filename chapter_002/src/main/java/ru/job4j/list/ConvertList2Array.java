package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-конвертер для списков
 * @author AlekseyRomantsov
 * @since 21.05.2018
 * @version 1.0.0.0
 */
public class ConvertList2Array {
    /**
     * Конвертирует список в двумерную матрицу
     * @param list - исходный список
     * @param rows - желаемое число строк матрицы
     * @return - полученная матрица, недостающие числа заменяются нулям
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows > 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int count = 0;
        for (Integer i : list) {
            array[count / cells][count % cells] = i;
            count++;
        }
        return array;
    }

    /**
     * Конвертирует список массивов разной длины в один список элементов
     * @param list - список массивов
     * @return - результирующий список элементов
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] line : list) {
            for (int i = 0; i < line.length; i++) {
                result.add(line[i]);
            }
        }
        return result;
    }
}