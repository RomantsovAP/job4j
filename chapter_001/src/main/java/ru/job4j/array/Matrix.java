package ru.job4j.array;

/**
 * Класс для ссздания думерного массива с таблицей умножаения
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class Matrix {
    /**
     * Метод создает и заполняет таблицей умножения матрицу заданного размера
     * @param size - размер матрицы АхА
     * @return - матрица с таблицей умножения
     */
    int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                matrix[i - 1][j - 1] = i * j;
            }
        }
        return matrix;
    }
}
