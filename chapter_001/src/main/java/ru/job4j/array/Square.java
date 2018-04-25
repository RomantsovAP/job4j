package ru.job4j.array;

/**
 * Класс для тренировки работы с массивами, заполняем массив квадратами чисел
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class Square {
    /**
     * Метод создает и заполняем массив квадратами чисел, начиная с 1
     * @param bound - количество элементов, не больше 40 000
     * @return - полученный массив
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 1; i <= bound; i++) {
            result[i - 1] = i * i;
        }
        return result;
    }
}
