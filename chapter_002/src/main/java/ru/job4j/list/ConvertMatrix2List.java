package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-конвертер для двумерных матриц
 * @author AlekseyRomantsov
 * @since 21.05.2018
 * @version 1.0.0.0
 */
public class ConvertMatrix2List {
    /**
     * Конвертирует двумерную матрицу в список
     * @param array - двумерная матрица
     * @return - список с элементами из двумерной матрицы
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] line : array) {
            for (int i : line) {
                list.add(i);
            }
        }
        return list;
    }
}
