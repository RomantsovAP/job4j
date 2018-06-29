package ru.job4j.interview;

/**
 * Поворачиваем матрицу по часовой стрелке
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 29.06.2018
 */
public class MatrixRotate {

    /**
     * Возвращает квадратную матрицу, полученную из исходной поворотом на 90 градусов по часовой стрелке
     * @param matrix - квадратная матрица
     * @return - повернутая на 90 градусов матрица
     */
    public int[][] rotate(int[][] matrix) {
        int size = matrix.length;
        int[][] answ = new int[matrix.length][matrix.length];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answ[j][size - i - 1] = matrix[i][j];
            }
        }
        return answ;
    }
}
