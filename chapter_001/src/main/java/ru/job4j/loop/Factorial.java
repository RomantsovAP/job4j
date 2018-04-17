package ru.job4j.loop;

/**
 * Тестовый класс для циклов, посчитаем факториал.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.04.2018
 */
public class Factorial {
    /**
     * метод вычисляет факториал числа
     * @param n - не слишком большое целое число
     * @return - факториал числа
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
